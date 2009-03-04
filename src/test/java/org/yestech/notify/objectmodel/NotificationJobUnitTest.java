/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.objectmodel;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.yestech.lib.xml.XmlUtils;

import java.util.UUID;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class NotificationJobUnitTest {

    @Test
    @Ignore
    public void testSerialization() {
        NotificationJob job = new NotificationJob(UUID.fromString("1e0c336e-c929-4c29-963d-a5b963d6c2d1"));
        job.addNotification(new DefaultNotification());
        job.addNotification(new DefaultNotification());
        job.addNotification(new DefaultNotification());
        job.addNotification(new DefaultNotification());
        String json = XmlUtils.toJSon(job);
        XmlUtils.fromJSon(json);
        String xml = XmlUtils.toXml(job);
        assertNotNull(xml);
        assertEquals("<org.yestech.notify.objectmodel.NotificationJob>\n" +
                "  <identifier>1e0c336e-c929-4c29-963d-a5b963d6c2d1</identifier>\n" +
                "  <messages class=\"list\">\n" +
                "    <org.yestech.notify.objectmodel.DefaultNotification serialization=\"custom\">\n" +
                "      <org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "        <default>\n" +
                "          <blindRecipients class=\"set\"/>\n" +
                "          <copyRecipients class=\"set\"/>\n" +
                "          <recipients class=\"set\"/>\n" +
                "        </default>\n" +
                "        <string>org.yestech.notify.template.NullTemplateLanguage</string>\n" +
                "        <string></string>\n" +
                "      </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    <org.yestech.notify.objectmodel.DefaultNotification serialization=\"custom\">\n" +
                "      <org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "        <default>\n" +
                "          <blindRecipients class=\"set\"/>\n" +
                "          <copyRecipients class=\"set\"/>\n" +
                "          <recipients class=\"set\"/>\n" +
                "        </default>\n" +
                "        <string>org.yestech.notify.template.NullTemplateLanguage</string>\n" +
                "        <string></string>\n" +
                "      </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    <org.yestech.notify.objectmodel.DefaultNotification serialization=\"custom\">\n" +
                "      <org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "        <default>\n" +
                "          <blindRecipients class=\"set\"/>\n" +
                "          <copyRecipients class=\"set\"/>\n" +
                "          <recipients class=\"set\"/>\n" +
                "        </default>\n" +
                "        <string>org.yestech.notify.template.NullTemplateLanguage</string>\n" +
                "        <string></string>\n" +
                "      </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    <org.yestech.notify.objectmodel.DefaultNotification serialization=\"custom\">\n" +
                "      <org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "        <default>\n" +
                "          <blindRecipients class=\"set\"/>\n" +
                "          <copyRecipients class=\"set\"/>\n" +
                "          <recipients class=\"set\"/>\n" +
                "        </default>\n" +
                "        <string>org.yestech.notify.template.NullTemplateLanguage</string>\n" +
                "        <string></string>\n" +
                "      </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "    </org.yestech.notify.objectmodel.DefaultNotification>\n" +
                "  </messages>\n" +
                "</org.yestech.notify.objectmodel.NotificationJob>", xml);

        System.out.println(xml);
        NotificationJob deserialized = XmlUtils.fromXml(xml);
        assertEquals(job, deserialized);
    }


}
