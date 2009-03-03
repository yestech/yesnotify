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

import org.yestech.notify.objectmodel.INotificationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class MemoryQueueNotificationProducer implements INotificationProducer {
    final private static Logger logger = LoggerFactory.getLogger(MemoryQueueNotificationProducer.class);
    
    private LinkedBlockingQueue<INotificationJob> queue;

    public LinkedBlockingQueue<INotificationJob> getQueue() {
        return queue;
    }

    public void setQueue(LinkedBlockingQueue<INotificationJob> queue) {
        this.queue = queue;
    }

    public void send(INotificationJob notificationJob) {
        if (notificationJob != null) {
            try {
                queue.put(notificationJob);
            } catch (InterruptedException e) {
                logger.error("error enqueue", e);
            }
        }
    }
}