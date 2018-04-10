package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.ejb.ApplicationManagementEJBLocal;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@Stateless
public class ProcessController implements Serializable {

    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private ApplicationManagementEJBLocal ejb;

    private String output;

    public void verwerk() throws IOException {
        output=ejb.processData();
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
