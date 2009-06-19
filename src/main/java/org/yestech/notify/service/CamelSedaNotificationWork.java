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
import org.springframework.beans.factory.annotation.Required;
import org.yestech.notify.objectmodel.INotificationJob;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public class CamelSedaNotificationWork implements Work {

    private INotificationJob notificationJob;
    private String sedaQueue;
    private CamelContext context;

    public String getSedaQueue() {
        return sedaQueue;
    }

    @Required
    public void setSedaQueue(String sedaQueue) {
        this.sedaQueue = sedaQueue;
    }

    public CamelContext getContext() {
        return context;
    }

    @Required
    public void setContext(CamelContext context) {
        this.context = context;
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
        final ProducerTemplate<Exchange> template = context.createProducerTemplate();
        template.sendBody(sedaQueue, notificationJob);

    }

    public void setNotificationJob(INotificationJob notificationJob) {
        this.notificationJob = notificationJob;
    }
}
