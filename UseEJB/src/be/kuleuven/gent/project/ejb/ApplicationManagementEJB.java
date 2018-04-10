package be.kuleuven.gent.project.ejb;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Stateless
public class ApplicationManagementEJB implements ApplicationManagementEJBLocal {

    @Override
    public String processData() throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "octave","~/Box Sync/IdeaProjects/ProjectUnicorn/UseEJB/HelloWorld.m");
        builder.redirectErrorStream(true);
        Process p = builder.start();
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
}
