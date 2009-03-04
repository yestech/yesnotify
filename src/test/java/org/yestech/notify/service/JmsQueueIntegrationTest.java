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
package org.yestech.notify.service;

import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.objectmodel.NotificationJob;
import org.yestech.notify.objectmodel.DefaultNotification;

import java.util.UUID;

/**
 * @author $Author: $
 * @version $Revision: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/org/yestech/notify/service/JmsQueueIntegrationTest-context.xml"})
public class JmsQueueIntegrationTest {
    private Mockery context = new Mockery();

    @Autowired
    private JmsQueueNotificationConsumer consumer;

    @Autowired
    private JmsQueueNotificationProducer producer;

    @Test
    public void setSending100Messages() {
        for (int i = 0; i < 100; i++) {
            INotificationJob job = new NotificationJob(UUID.randomUUID());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            job.addNotification(new DefaultNotification());
            producer.send(job);            
        }
        
//        final INotificationJob notificationJob = context.mock(INotificationJob.class, "notificationJob");
//        context.checking(new Expectations(){{
//            oneOf(notificationJob).
//        }});
//        producer.send(notificationJob);

    }
}
