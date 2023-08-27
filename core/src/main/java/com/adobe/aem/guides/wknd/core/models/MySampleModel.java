package com.adobe.aem.guides.wknd.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;



@Model(adaptables = Resource.class)
public class MySampleModel {

    private static final Logger log= LoggerFactory.getLogger(MySampleModel.class);

    @ValueMapValue(name="title", injectionStrategy= InjectionStrategy.OPTIONAL)
    private String title;

    @ValueMapValue(name="description", injectionStrategy= InjectionStrategy.OPTIONAL)
    private String description;



    @ValueMapValue(name="fileReferenceDialog", injectionStrategy= InjectionStrategy.OPTIONAL)
    private String fileReferenceDialog;


    @PostConstruct
    protected void init(){
        log.debug("MySampleModel init");
    }

    public String getTitle(){return title;}

    public String getDescription(){return description;}

    public String getFileReferenceDialog(){return fileReferenceDialog;}
}
