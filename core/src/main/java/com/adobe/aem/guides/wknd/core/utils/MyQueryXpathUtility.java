package com.adobe.aem.guides.wknd.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

public class MyQueryXpathUtility {

    private static final Logger log = LoggerFactory.getLogger(MyQueryXpathUtility.class);

    private static String getQueryXpath(String rootPath, String customProperty, String keywords){
        String containsKeywords = StringUtils.isNotBlank(keywords) ? "[" + "jcr:contains" + "(.,\"" + keywords + "\")]":"";
        String mainQuery = "/jcr:root" +rootPath + "//*";
        String customPropertyKeywords="";
        String finalQuery;

        if (StringUtils.isNotBlank(customProperty) && StringUtils.isNotBlank(keywords)){
            customPropertyKeywords="[@" + customProperty + " = \"" + keywords + "\"]";
        }

        if(StringUtils.isNotBlank(customPropertyKeywords)) {
            finalQuery=mainQuery+customPropertyKeywords;
        }else{
            finalQuery= mainQuery + containsKeywords;
        }
        return finalQuery;
    }

    private static String getQuerySql(String rootPath, String customProperty, String keyword){
        String baseQuery = "SELECT * from [cq:Page]";
        String resourceType = "and [jcr:content/" + customProperty + "] ='" + keyword + "'";
        String whereConditions = "where ISDESCENDANTNODE('" + rootPath + "')" + resourceType;
        return baseQuery + whereConditions;
    }
    public static List<Resource> getResults(ResourceResolver resolver ,String rootPath, String customProperty, String keywords, String type ) {
        List<Resource> allResources = new ArrayList<>();
        String queryLanguage=type;
        if(queryLanguage.equalsIgnoreCase("xpath")){
            String query=getQueryXpath(rootPath,customProperty,keywords);
            log.debug("query: ",query);
            try {
                Session session = resolver.adaptTo(Session.class);
                Workspace workspace = session.getWorkspace();
                QueryManager qm = workspace.getQueryManager();

                Query jcrQuery = qm.createQuery(query, queryLanguage);
                QueryResult result = jcrQuery.execute();
                NodeIterator nodes = result.getNodes();
                while (nodes.hasNext()) {
                    Node node = nodes.nextNode();
                    Resource resource = resolver.getResource(node.getPath());
                    if (resource != null) {
                        allResources.add(resource);
                    }
                }
            } catch (RepositoryException x) {
                log.error("Error finding data roots", x);
            }
        }else {
            String query = getQuerySql(rootPath, customProperty, keywords);
            log.debug("query: ", query);
            try {
                Session session = resolver.adaptTo(Session.class);
                Workspace workspace = session.getWorkspace();
                QueryManager qm = workspace.getQueryManager();

                Query jcrQuery = qm.createQuery(query, queryLanguage);
                QueryResult result = jcrQuery.execute();
                NodeIterator nodes = result.getNodes();
                while (nodes.hasNext()) {
                    Node node = nodes.nextNode();
                    Resource resource = resolver.getResource(node.getPath());
                    if (resource != null) {
                        allResources.add(resource);
                    }
                }
            } catch (RepositoryException x) {
                log.error("Error finding data roots", x);
            }
        }
        return allResources;
    }
}
