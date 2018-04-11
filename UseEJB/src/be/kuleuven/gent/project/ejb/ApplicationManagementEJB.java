package be.kuleuven.gent.project.ejb;

import javax.ejb.Stateless;
import java.io.*;
import java.util.logging.Logger;

@Stateless
public class ApplicationManagementEJB implements ApplicationManagementEJBLocal {

    @Override
    public String processData() throws IOException {

        String path = maakScript();

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
        return sb.toString();
    }

    @Override
    public String maakScript() throws IOException {

        //Inhoud van de file schrijven
        StringBuilder sb= new StringBuilder();

        sb.append("#! octave-interpreter-name -qf \n");
        sb.append("# a sample Octave program \n");
        sb.append("disp(\"HelloWorld ;D Piemel \")");

        //Path bepalen naar waar we gaan schrijven, zo wordt die niet computer afhankelijk. Wel nog steeds pc afhankelijk
        StringBuilder path = new StringBuilder();
        path.append(System.getProperties().getProperty("user.home")).append("/Box Sync/IdeaProjects/ProjectUnicorn/UseEJB/HelloWorld.m");

        //File aanmaken
        File file = new File(path.toString());

        //File schrijven
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.toString();


    }


}
