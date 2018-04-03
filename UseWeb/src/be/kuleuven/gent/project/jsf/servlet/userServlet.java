package be.kuleuven.gent.project.jsf.servlet;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.jsf.controller.UserController;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@Stateless
public class userServlet extends HttpServlet{

    @Inject
    private UserController userJSF;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        User user = userJSF.getUser();

        String name = request.getParameter("j_name");
        user.setName(name);

    }
}
