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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.objectmodel.INotificationJob;

import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.JMSException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class JmsQueueNotificationConsumer implements INotificationConsumer, MessageListener {
    final private static Logger logger = LoggerFactory.getLogger(JmsQueueNotificationConsumer.class);

    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            ObjectMessage objMessage = (ObjectMessage) message;
            INotificationJob job = null;
            try {
                job = (INotificationJob) objMessage.getObject();
                recieve(job);
            } catch (JMSException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void recieve(INotificationJob notificationJob) {
        System.out.println("job: " + notificationJob);
    }
}