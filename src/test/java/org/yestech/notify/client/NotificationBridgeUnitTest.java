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
package org.yestech.notify.client;

import static com.google.common.collect.Lists.newArrayList;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.service.INotificationProducer;

import java.util.List;

/**
 * @author $Author: $
 * @version $Revision: $
 */
@RunWith(JMock.class)
public class NotificationBridgeUnitTest {
    Mockery context = new JUnit4Mockery();
    NotificationBridge bridge;

    @Before
    public void setUp() {
        bridge = new NotificationBridge();
    }

    @Test
    public void testSendNotification() {
        final INotificationProducer producer = context.mock(INotificationProducer.class, "producer");
        final INotification notification = context.mock(INotification.class, "notification");
        bridge.setProducer(producer);
        context.checking(new Expectations() {{
            oneOf(notification).setJob(with(any(INotificationJob.class)));
            oneOf(producer).send(with(any(INotificationJob.class)));
        }});
        bridge.sendNotification(notification);
    }

    @Test
    public void testSendNotifications() {
        final INotificationProducer producer = context.mock(INotificationProducer.class, "producer");
        final INotification notification = context.mock(INotification.class, "notification");
        List<INotification> notifications = newArrayList(notification);
        bridge.setProducer(producer);
        context.checking(new Expectations() {{
            oneOf(notification).setJob(with(any(INotificationJob.class)));
            oneOf(producer).send(with(any(INotificationJob.class)));
        }});
        bridge.sendNotifications(notifications);
    }
}
