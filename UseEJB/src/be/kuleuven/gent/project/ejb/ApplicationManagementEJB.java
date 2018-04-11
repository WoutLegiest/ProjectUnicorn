package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

@Stateless
public class ApplicationManagementEJB implements ApplicationManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Override
    public void addMeaserment(ProfessionalMeasurement pf){

        //TODO

        em.persist(pf);

    }

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

        /*
        1) Opening van het script
        2) Data uit DB halen en deze invoegen in script
        3) Verwerking toevoegen
        4) Output laten uitkomen
        5) Output verwerken
         */



        //Inhoud van de file schrijven
        StringBuilder sb= new StringBuilder();

        sb.append("#! octave-interpreter-name -qf \n");
        sb.append("# a sample Octave program \n");
        sb.append("disp(\"HelloWorld ;D Halleluja Anyway \")");

        //Path bepalen naar waar we gaan schrijven, zo wordt die niet computer afhankelijk. Wel nog steeds pc afhankelijk
        StringBuilder sb_path = new StringBuilder();
        sb_path.append(System.getProperties().getProperty("user.home"))
                .append("/Box Sync/IdeaProjects/ProjectUnicorn/UseEJB/HelloWorld.m");

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
    public ProfessionalMeasurement makePM() {
        return new ProfessionalMeasurement();
    }


}
