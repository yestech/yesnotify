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

import org.yestech.notify.objectmodel.INotificationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Does a NoOp.  Ususally used for testing.
 * 
 * @author Artie Copeland
 * @version $Revision: $
 */
public class NoOpNotificationProcessor implements INotificationProcessor {
    final private static Logger logger = LoggerFactory.getLogger(NoOpNotificationProcessor.class);

    @Override
    public void process(INotificationJob notificationJob) {
        if (logger.isInfoEnabled()) {
            logger.info("Called with job: " + notificationJob);
        }
    }
}
