package com.adobe.aem.guides.wknd.core.service.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Sample - Service Configuration", description="Sample Service Configuration Description")
public @interface MyFirstSampleOSGIConfig {

    @AttributeDefinition(name="FirstName", defaultValue="Mario", description="Endpoint One")
    String endpointOne();

    @AttributeDefinition(name="LastName", defaultValue="Rossi", description="Endpoint Two")
    String endpointTwo();
}
