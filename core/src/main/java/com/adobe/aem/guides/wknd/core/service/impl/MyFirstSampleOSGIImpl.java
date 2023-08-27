package com.adobe.aem.guides.wknd.core.service.impl;

import com.adobe.aem.guides.wknd.core.service.SampleService;
import com.adobe.aem.guides.wknd.core.service.configuration.MyFirstSampleOSGIConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service= SampleService.class, immediate = true)
@Designate(ocd= MyFirstSampleOSGIConfig.class)
public class MyFirstSampleOSGIImpl implements SampleService{

    private String endpointOne;
    private String endpointTwo;

    @Activate
    @Modified
    protected void activate(final MyFirstSampleOSGIConfig config){
        endpointOne=config.endpointOne();
        endpointTwo=config.endpointTwo();
    }

    @Override
    public String getEndpointOne(){return endpointOne;}

    @Override
    public String getEndpointTwo(){return endpointTwo;}
}
