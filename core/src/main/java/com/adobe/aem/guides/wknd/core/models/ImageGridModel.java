package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingScriptHelper;
import org.apache.sling.models.annotations.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = Resource.class)
public class ImageGridModel {

    private static final Logger log= LoggerFactory.getLogger(MySampleModel.class);
    @PostConstruct
    protected void init(){
        log.debug("ImageGrid init");
    }
    @Inject
    private SlingScriptHelper slingScriptHelper;

    private String pagePath="/content/wknd/country/adventures";
    List<Map<String, String>> nodeList = new ArrayList<>();



    public void addData() {
        Session session = slingScriptHelper.getRequest().getResourceResolver().adaptTo(Session.class);

        try {
            Node startNode = session.getNode(pagePath);

            if (startNode.hasNodes()) {
                NodeIterator childNodes = startNode.getNodes();

                while (childNodes.hasNext()) {
                    Node childNode = childNodes.nextNode();
                    String title = childNode.getNode("jcr:content").getProperty("jcr:title").toString();
                    String imagePath = childNode.getNode("jcr:content").getNode("root").getNode("container")
                            .getNode("carousel").getNodes().nextNode().getProperty("fileReference").toString();
                    String name = childNode.getName();

                    Map<String, String> nodeDataMap = new HashMap<>();
                    nodeDataMap.put("title", title);
                    nodeDataMap.put("imagePath", imagePath);
                    nodeDataMap.put("name", name);

                    nodeList.add(nodeDataMap);
                }
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }

    public Map<String, String> getData( int index) {
        return nodeList.get(index);
    }
}
