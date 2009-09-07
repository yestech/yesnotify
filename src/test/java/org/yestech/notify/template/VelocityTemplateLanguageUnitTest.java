/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.template;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.yestech.notify.objectmodel.IMessage;
import org.jmock.Mockery;

import java.util.HashMap;

import static com.google.common.collect.Maps.newHashMap;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public class VelocityTemplateLanguageUnitTest {
    private Mockery context = new Mockery();

    private VelocityTemplateLanguage language;

    @Before
    public void setUp() throws Exception {
        language = new VelocityTemplateLanguage();
    }

    @Test
    public void testApply() {
        IMessage message = context.mock(IMessage.class, "message");
        HashMap<String, Object> templateData = newHashMap();
        templateData.put("test1", "values");
        templateData.put("second", "another");
        VelocityTemplateLanguagePersistence internal = new VelocityTemplateLanguagePersistence();
        internal.setData(templateData);
        internal.setFilePath("/velocity/test.vm");
        language.setPersistence(internal);
        String result = language.apply(message);
        assertNotNull(result);
        assertEquals("Testing....", result);
    }
}
