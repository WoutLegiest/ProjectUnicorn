package be.kuleuven.gent.project.jsf.validator;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import java.util.List;

/**
 * Deze validator controleert of de opgegeven loginNaam niet als is gebruikt door een andere gebruiker
 */
@FacesValidator("loginValidator")
public class LoginValidator implements Validator {

    @Inject
    private UserManagementEJBLocal userEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {

        String login = value.toString();

        List<User> users = userEJB.findAllUsers();

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                throw new ValidatorException(new FacesMessage(
                        "Username already exists"));
            }
        }
    }

}
