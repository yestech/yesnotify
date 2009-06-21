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
import org.yestech.lib.xml.XmlUtils;
import org.yestech.notify.objectmodel.INotificationJob;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
@SuppressWarnings("unchecked")
public class TerracottaNotificationProducer implements INotificationProducer {
    final private static Logger logger = LoggerFactory.getLogger(TerracottaNotificationProducer.class);
//    private String queueName;
//    private String topologyName;
//    private int queueSize = 1000;

    private Pipe pipe;

//    public int getPipeSize() {
//        return queueSize;
//    }
//
//    public void setPipeSize(int queueSize) {
//        this.queueSize = queueSize;
//    }
//
//    public String getTopologyName() {
//        return topologyName;
//    }
//
//    @Required
//    public void setTopologyName(String topologyName) {
//        this.topologyName = topologyName;
//    }
//
//    public String getPipeName() {
//        return queueName;
//    }
//
//    @Required
//    public void setPipeName(String queueName) {
//        this.queueName = queueName;
//    }

    public Pipe getPipe() {
        return pipe;
    }

    @Required
    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
        System.out.println("TerracottaNotificationProducer pipe: " + pipe);
    }

    @Override
    public void send(INotificationJob notificationJob) {
//...
//...
//String something = pipe.take();

        final String notificationXml = XmlUtils.toXml(notificationJob);
        try {
            pipe.put(notificationXml);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
