package com.adobe.aem.guides.wknd.core.servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.regex.Pattern;

@Component(
        service = { Servlet.class },
        property = {
                "sling.servlet.methods=POST",
                "sling.servlet.paths=/bin/validation"
        }
)
public class ServletContactForm extends SlingAllMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletContactForm.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        JSONObject jsonResponse = new JSONObject();

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String subject = request.getParameter("subject");


        boolean isNomeValid = validateCampo(nome, 50);
        boolean isCognomeValid = validateCampo(cognome, 50);
        boolean isEmailValid = isValidEmail(email);
        boolean isCountryValid = isValid(country);
        boolean isSubjectValid = isValid(subject);

        try {
            jsonResponse.put("nome", isNomeValid);
            jsonResponse.put("cognome", isCognomeValid);
            jsonResponse.put("email", isEmailValid);
            jsonResponse.put("country", isCountryValid);
            jsonResponse.put("subject", isSubjectValid);
        } catch (JSONException e) {
            LOGGER.error("Error creating JSON response", e);
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }

    private boolean validateCampo(String value, int maxLength) {
        return StringUtils.isNotBlank(value) && value.length() <= maxLength;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

    private boolean isValid(String value) {
        return StringUtils.isNotBlank(value);
    }
}
