package com.adobe.aem.guides.wknd.core.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.apache.sling.api.servlets.HttpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;


import javax.servlet.Servlet;
import java.io.IOException;


@Component(service= {Servlet.class}, property = {"sling.servlet.methods="+ HttpConstants.METHOD_POST,"sling.servlet.extensions=json",
        "sling.servlet.selectors=mysampleservletcomponent", "sling.servlet.resourceTypes=sling/servlet/default"})
public class ResourceToJSONServlet extends SlingAllMethodsServlet {
    private static final Logger logger= LoggerFactory.getLogger(ResourceToJSONServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response){


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");
        final String email = request.getParameter("email");
        final JsonObject jsonResult = new JsonObject();

        String firstNameValid=isValid(firstName);

        String lastNameValid=isValid(lastName);

        String emailValid=emailValid(email);

        try {
            jsonResult.addProperty("firstName",firstNameValid);
            jsonResult.addProperty("lastName",lastNameValid);
            jsonResult.addProperty("email",emailValid);

            response.getWriter().write(jsonResult.toString());
            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.getWriter().close();
        }catch (JsonParseException | IOException e){
            logger.error("Could not get JSON", e.toString());
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
    public String isValid(String valore) {
        return valore != null && valore.length()>3 && !valore.matches(".*\\d.*") ?"is valid":"isn't valid";
    }

    public String emailValid(String email) {
        return email != null && email.matches("^[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}") ?"is valid":"isn't valid";
    }
}
