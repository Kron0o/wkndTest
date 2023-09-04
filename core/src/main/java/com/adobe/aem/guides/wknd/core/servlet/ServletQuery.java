package com.adobe.aem.guides.wknd.core.servlet;

import com.adobe.aem.guides.wknd.core.utils.MyQueryXpathUtility;
import com.google.gson.JsonObject;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.List;

@Component(service= {Servlet.class}, property = {"sling.servlet.methods="+ HttpConstants.METHOD_POST,"sling.servlet.extensions=json",
        "sling.servlet.selectors=servletQuery", "sling.servlet.resourceTypes=sling/servlet/default"})
public class ServletQuery extends SlingAllMethodsServlet {



    private static final Logger log = LoggerFactory.getLogger(ServletQuery.class);



    @Override
    protected void doPost(org.apache.sling.api.SlingHttpServletRequest request, org.apache.sling.api.SlingHttpServletResponse response) throws IOException {
        String rootPath = request.getParameter("path");
        String customProperty = request.getParameter("customProperty");
        String keywords = request.getParameter("keywords");
        String type= request.getParameter("type");

        MyQueryXpathUtility queryUtility = new MyQueryXpathUtility();
        List<Resource> results = queryUtility.getResults(request.getResourceResolver(), rootPath, customProperty, keywords, type);

        final JsonObject jsonResult = new JsonObject();
        for(Resource res:results){
            jsonResult.addProperty(res.getPath(),res.getValueMap().toString());
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(jsonResult.toString());
        response.getWriter().close();
    }
}
