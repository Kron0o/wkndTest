package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;


@Model(adaptables = { SlingHttpServletRequest.class, Resource.class })
public class MyHeaderModel {

    @ScriptVariable
    private Page currentPage;

    private Page languagePage;

    @PostConstruct
    protected void init() {
        if (currentPage != null) {
            languagePage = currentPage.getAbsoluteParent(3);
        }
    }

    public Page getLanguagePage() {
        return languagePage;
    }
}
