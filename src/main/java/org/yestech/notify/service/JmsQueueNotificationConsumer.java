/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yestech.notify.objectmodel.INotificationJob;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class JmsQueueNotificationConsumer implements INotificationConsumer {
    final private static Logger logger = LoggerFactory.getLogger(JmsQueueNotificationConsumer.class);

    private LinkedBlockingQueue<INotificationJob> queue;

    public LinkedBlockingQueue<INotificationJob> getQueue() {
        return queue;
    }

    public void setQueue(LinkedBlockingQueue<INotificationJob> queue) {
        this.queue = queue;
    }

    public void recieve(INotificationJob notificationJob) {
        while (true) {
            try {
                INotificationJob job = queue.take();
            } catch (InterruptedException e) {
                logger.error("error taking from queue", e);
            }
        }
    }
}