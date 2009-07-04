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
public class CamelXmlNotificationConsumerUnitTest {

    private Mockery context = new Mockery();
    private CamelXmlNotificationConsumer consumer;

    @Before
    public void setUp() {
        consumer = new CamelXmlNotificationConsumer();
    }

    @Test
    public void testNonTerracottaArtifactProcess() {
        final Exchange exchange = context.mock(Exchange.class, "Exchange");
        final Message message = context.mock(Message.class, "Message");
        final INotificationProcessor publisher = context.mock(INotificationProcessor.class, "Notification");


        context.checking(new Expectations() {
            {
                oneOf(exchange).getException();
                will(returnValue(null));
                oneOf(exchange).getIn();
                will(returnValue(message));
                oneOf(message).getBody(with(String.class));
                will(returnValue(XmlUtils.toXml(new TestNotificationJob())));
                oneOf(message).setBody(with(any(INotificationJob.class)));
                oneOf(publisher).process(with(any(INotificationJob.class)));
            }
        });
        consumer.setProcessor(publisher);
        consumer.process(exchange);
    }

    private class TestNotificationJob implements INotificationJob {
        @Override
        public UUID getIdentifier() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void addNotification(INotification notification) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void addNotifications(Collection<INotification> messages) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void removeNotification(INotification message) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void removeNotifications(Collection<INotification> messages) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Iterator<INotification> getNotifications() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void clearNotifications() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int size() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isEmpty() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int compareTo(INotificationJob o) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
