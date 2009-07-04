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
package org.yestech.notify.service;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.yestech.lib.xml.XmlUtils;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.objectmodel.INotification;

import java.util.UUID;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public class CamelNotificationConsumerUnitTest {

    private Mockery context = new Mockery();
    private CamelNotificationConsumer consumer;

    @Before
    public void setUp() {
        consumer = new CamelNotificationConsumer();
    }

    @Test
    public void testNonTerracottaArtifactProcess() {
        final Exchange exchange = context.mock(Exchange.class, "Exchange");
        final Message message = context.mock(Message.class, "Message");
        final INotificationJob job = context.mock(INotificationJob.class, "Job");
        final INotificationProcessor publisher = context.mock(INotificationProcessor.class, "Notification");


        context.checking(new Expectations() {
            {
                oneOf(exchange).getException();
                will(returnValue(null));
                oneOf(exchange).getIn();
                will(returnValue(message));
                oneOf(message).getBody(INotificationJob.class);
                will(returnValue(job));
                oneOf(message).setBody(job);
                oneOf(publisher).process(job);
            }
        });
        consumer.setProcessor(publisher);
        consumer.process(exchange);
    }
}