/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.service;

import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.lib.xml.XmlUtils;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.JmsTemplate;


/**
 * @author $Author: $
 * @version $Revision: $
 */
public class JmsQueueNotificationProducer implements INotificationProducer {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void send(final INotificationJob notificationJob) {
        jmsTemplate.send(queue, new MessageCreator()
        {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(notificationJob);
            }
        });

    }
}
