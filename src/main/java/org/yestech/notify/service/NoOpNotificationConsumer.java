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
public class NoOpNotificationConsumer implements INotificationConsumer {
    final private static Logger logger = LoggerFactory.getLogger(NoOpNotificationConsumer.class);

    public void recieve(INotificationJob notificationJob) {
    }
}