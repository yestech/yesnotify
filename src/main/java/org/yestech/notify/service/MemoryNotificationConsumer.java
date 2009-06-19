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
import org.terracotta.modules.annotations.InstrumentedClass;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author $Author: $
 * @version $Revision: $
 */
@InstrumentedClass
public class MemoryNotificationConsumer implements INotificationConsumer {
    final private static Logger logger = LoggerFactory.getLogger(MemoryNotificationConsumer.class);

    private INotificationProcessor processor;

    public INotificationProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(INotificationProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void recieve(INotificationJob notificationJob) {
        processor.process(notificationJob);
    }
}