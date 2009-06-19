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

import commonj.work.Work;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.lib.xml.XmlUtils;
import org.terracotta.modules.annotations.InstrumentedClass;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
@InstrumentedClass
public class DefaultNotificationWork implements Work {

    private String notificationXml;
    private MemoryNotificationConsumer consumer = new MemoryNotificationConsumer();

    public MemoryNotificationConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(MemoryNotificationConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public boolean isDaemon() {
        return false;
    }

    @Override
    public void release() {
    }

    @Override
    public void run() {
        final INotificationProcessor processor = consumer.getProcessor();
        INotificationJob notificationJob = XmlUtils.fromXml(notificationXml);
        processor.process(notificationJob);
    }

    public void setNotificationXml(String notificationXml) {
        this.notificationXml = notificationXml;
    }
}