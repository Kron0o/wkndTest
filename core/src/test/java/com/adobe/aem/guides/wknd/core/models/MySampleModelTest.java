package com.adobe.aem.guides.wknd.core.models;

import com.google.common.collect.ImmutableMap;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.day.cq.wcm.api.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
public class MySampleModelTest {
    private MySampleModel sample;

    private Page page;

    private Resource resource;

    @BeforeEach
    public void setup(AemContext context) throws Exception {

        page = context.create().page("/content/mypage");
        resource = context.create().resource(page, "samplemodel",
                new ValueMapDecorator(ImmutableMap.of(
                        "title", "titolo",
                        "description", "descrizione")));

        // create sling model
        sample = resource.adaptTo(MySampleModel.class);
    }

    @Test
    void testGetTitle() throws Exception {
        // some very basic junit tests
        String msg = sample.getTitle();
        assertNotNull(msg);
        assertEquals("titolo", msg);
    }

    @Test
    void testGetDescription() throws Exception {
        // some very basic junit tests
        String msg = sample.getDescription();
        assertNotNull(msg);
        assertEquals("descrizione", msg);
    }
}
