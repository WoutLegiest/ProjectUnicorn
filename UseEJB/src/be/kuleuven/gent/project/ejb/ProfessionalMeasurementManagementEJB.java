package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProfessionalMeasurementManagementEJB implements ProfessionalMeasurementManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    public ProfessionalMeasurementManagementEJB() {
        //default constructor
    }

    @Override
    public boolean processExperiment(List<LinkedList<Double>> input) {

        return false;
    }

    @Override
    public List<ProfessionalMeasurement> findAllMeasurementsByProject(ProfessionalProject professionalProject) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM ProfessionalMeasurement m WHERE m.idProject = ");
        query.append(professionalProject.getId());
        TypedQuery<ProfessionalMeasurement> q =
                em.createQuery(query.toString() , ProfessionalMeasurement.class);
        return q.getResultList();
    }

    @Override
    public ProfessionalMeasurement makeMeasurement(int idProject, String idUser, String description, ArrayList<Float>xData, ArrayList<Float>yData, ArrayList<Float>zData) {
        byte[] xDataByte= toByteArray(xData);
        byte[] yDataByte= toByteArray(yData);
        byte[] zDataByte= toByteArray(zData);

        ProfessionalMeasurement professionalMeasurement = new ProfessionalMeasurement(idProject, idUser, description, xDataByte, yDataByte, zDataByte);
        return null;
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




}
