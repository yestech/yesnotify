/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.service;

import org.yestech.notify.objectmodel.INotificationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class MemoryQueueNotificationConsumer implements INotificationConsumer {
    final private static Logger logger = LoggerFactory.getLogger(MemoryQueueNotificationConsumer.class);
    
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
    }}
