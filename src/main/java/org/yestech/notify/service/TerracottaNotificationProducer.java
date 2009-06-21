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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.terracotta.message.pipe.Pipe;
import org.terracotta.modules.annotations.Root;
import org.yestech.lib.xml.XmlUtils;
import org.yestech.notify.objectmodel.INotificationJob;

/**
 * A producer that publishes to a Terracotta Pipe.
 * 
 * @author Artie Copeland
 * @version $Revision: $
 */
@SuppressWarnings("unchecked")
public class TerracottaNotificationProducer implements INotificationProducer {
    final private static Logger logger = LoggerFactory.getLogger(TerracottaNotificationProducer.class);

    @Root
    private Pipe pipe;

    public Pipe getPipe() {
        return pipe;
    }

    @Required
    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    public void send(INotificationJob notificationJob) {
        final String notificationXml = XmlUtils.toXml(notificationJob);
        try {
            pipe.put(notificationXml);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
