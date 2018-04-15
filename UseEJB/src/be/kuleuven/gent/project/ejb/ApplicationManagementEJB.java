package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.Data;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.ArrayList;

@Stateless
public class ApplicationManagementEJB implements ApplicationManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Override
    public void addMeaserment(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z, int ProjectId, String loginName,
                              String description, ArrayList<ArrayList<Float>> result){

        ProfessionalMeasurement pm = new ProfessionalMeasurement();



        pm.setDescription(description);
        //pm.setIdProject(ProjectId);
        //pm.setLoginUser(loginName);

        em.persist(pm);

    }

    @Override
    public byte[] toByteArray(ArrayList<Float> dataList) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (float dataElement : dataList) {
            try {
                out.writeUTF(String.valueOf(dataElement));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    @Override
    public ArrayList<ArrayList<Float>> processData(ArrayList<Float> list) throws IOException {

        //TODO
        //Uitfilteren van de data, debuggen en alles controleren ! 

        String path = maakScript(list);

        //Process aanmaken waarin we
        ProcessBuilder builder = new ProcessBuilder("octave",path);
        builder.redirectErrorStream(true);
        Process p = builder.start();

        //Reader die de output van het command zal uitlezen
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            if(!line.contains("Columns"))
                sb.append(line);
        }

        String s = sb.toString();

        String[] onderdelen = s.split("fase");

        float max1 = Float.parseFloat(onderdelen[0]);
        String[] data = onderdelen[1].split("\\s+");
        String[] freq = onderdelen[2].split("\\s+");
        float max2 = Float.parseFloat(onderdelen[3]);
        String[] data2 = onderdelen[4].split("\\s+");

        ArrayList<Float> res_sample = new ArrayList<>();
        ArrayList<Float> freq_list = new ArrayList<>();
        ArrayList<Float> res_trans = new ArrayList<>();

        res_sample.add(max1);
        res_trans.add(max2);
        freq_list.add((float) 0);

        for(int i= 1; i<data.length;i++)
            res_sample.add(Float.parseFloat(data[i]));


        for(int i= 1; i<freq.length;i++)
            freq_list.add(Float.parseFloat(freq[i]));


        for (int i = 1; i < data2.length ; i++)
            res_trans.add(Float.parseFloat(data2[i]));

        ArrayList<ArrayList<Float>> returntje = new ArrayList<>();

        returntje.add(res_sample);
        returntje.add(res_trans);
        returntje.add(freq_list);


        return returntje;
    }

    @Override
    public String maakScript(ArrayList<Float> list) throws IOException {

        /*
        1) Opening van het script
        2) Data uit DB halen en deze invoegen in script
        3) Verwerking toevoegen
        4) Output laten uitkomen
        5) Output verwerken
         */


        //Inhoud van de file schrijven
        StringBuilder sb= new StringBuilder();

        sb.append("pkg load signal \n");


        sb.append("data = [").append(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            sb.append(", ").append(list.get(i));
        }



        sb.append("]; \n");

/*        sb.append("%% Generate data as for example measured by smartphone \n");
        sb.append("% time vector - 10 seconds data \n");
        sb.append("t = 0:0.01:20; %[s] \n");
        sb.append("% turn it into a vector with non fixed time step as in smartphones \n");
        sb.append("a = -1; b = 1; \n");
        sb.append("t = t + (a + (b-a).*rand(1,length(t))).*1e-3; \n");
        sb.append("f1 = 2; f2 = 8; % two frequencies within the signal \n");
        sb.append("data = sin(2*pi*f1.*t)+0.5.*sin(2*pi*f2.*t); \n");*/

        sb.append("endData = length(data) -1 ; \n ");
        sb.append("endData = endData*0.01; \n ");
        sb.append("t = 0:0.01:endData; \n ");

        sb.append("%% Step 1: resample at fixed time step \n");
        sb.append("Fs = 100.; % desired (fixed) sample rate \n");
        sb.append("%[data_resampled,t_resampled] = resample(data,t,Fs); \n");
        sb.append("t_resampled = t(1):1/Fs:t(end); \n");
        sb.append("data_resampled = interp1(t, data, t_resampled, 'spline'); \n");
        sb.append("% to make sure time starts at t = 0s: \n");
        sb.append("t_resampled = t_resampled - t_resampled(1); \n");

        sb.append("%% Step 2: Compute amplitude spectrum of the signal \n");
        sb.append("L = length(data_resampled); \n");
        sb.append("f = Fs*(0:(L/2))/L; \n");
        sb.append("A2_data = fft(data_resampled); A2 = abs(A2_data/L); \n");
        sb.append("A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1); \n");

        sb.append("maxi = max(A_data); \n");
        sb.append("k = find(A_data==maxi); \n");
        sb.append("disp(f(k)); \n");
        sb.append("disp('fase'); \n");

        sb.append("disp(A_data); \n");
        sb.append("disp('fase'); \n");
        sb.append("disp(f); \n");
        sb.append("disp('fase'); \n");

        sb.append("%% Step 3: Apply bandbass filter \n");
        sb.append("% Lowerbound and upperbound cutoff bandpass filter (relative to Nyquist frequency) \n");

        sb.append("f1 = 1/Fs*2; f2 = 4/Fs*2; \n");
        sb.append("% [b,a] = butter(n,Wn,ftype) \n");
        sb.append("filter_order = 4; \n");
        sb.append("[b,a] = butter(filter_order,[f1 f2]); \n");
        sb.append("data_filtered = filtfilt(b,a,data_resampled); \n");
        sb.append("A2_data = fft(data_filtered); A2 = abs(A2_data/L); \n");
        sb.append("A_data = A2(1:L/2+1); A_data(2:end-1) = 2*A_data(2:end-1); \n");

        sb.append("maxi = max(A_data); \n");
        sb.append("k = find(A_data==maxi); \n");
        sb.append("disp(f(k)); \n");

        sb.append("disp('fase'); \n");
        sb.append("disp(A_data); \n");


        //Path bepalen naar waar we gaan schrijven, zo wordt die niet computer afhankelijk. Wel nog steeds pc afhankelijk
        StringBuilder sb_path = new StringBuilder();
        sb_path.append(System.getProperties().getProperty("user.home"))
                .append("/Box Sync/IdeaProjects/ProjectUnicorn/UseEJB/verwerking.m");

        //File aanmaken
        File file = new File(sb_path.toString());

        //File schrijven
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb_path.toString();

    }

    @Override
    public Data makeDataObject(ArrayList<ArrayList<Float>> input, ArrayList<ArrayList<Float>> results) {

        Data data = new Data();

        data.setxData(toByteArray(input.get(0)));
        data.setyData(toByteArray(input.get(1)));
        data.setzData(toByteArray(input.get(2)));

        data.setResult1x(toByteArray(results.get(0)));
        data.setResult2x(toByteArray(results.get(1)));
        data.setFreqx(toByteArray(results.get(2)));
        data.setResult1y(toByteArray(results.get(3)));
        data.setResult2y(toByteArray(results.get(4)));
        data.setFreqy(toByteArray(results.get(5)));
        data.setResult1z(toByteArray(results.get(6)));
        data.setResult2z(toByteArray(results.get(7)));
        data.setFreqz(toByteArray(results.get(8)));

        em.persist(data);

        return data;
    }

}
