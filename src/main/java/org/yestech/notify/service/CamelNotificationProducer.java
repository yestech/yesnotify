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
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.camel.impl.DefaultMessage;
import org.springframework.beans.factory.annotation.Required;

/**
 * A producer that publishes to a Camel EndPoint.   It sends all notifications asynchronously to the startEndPoint Supplied
 *
 * @author $Author: $
 * @version $Revision: $
 */
public class CamelNotificationProducer implements INotificationProducer {
    private CamelContext context;
    private String startEndPoint;

    public CamelContext getContext() {
        return context;
    }

    @Required
    public void setContext(CamelContext context) {
        this.context = context;
    }

    public String getStartEndPoint() {
        return startEndPoint;
    }

    @Required
    public void setStartEndPoint(String startEndPoint) {
        this.startEndPoint = startEndPoint;
    }

    @Override
    public void send(INotificationJob notificationJob) {
        final DefaultProducerTemplate template = (DefaultProducerTemplate) context.createProducerTemplate();
        Endpoint endpoint = context.getEndpoint(startEndPoint);
        template.setDefaultEndpoint(endpoint);
        Exchange exchange = endpoint.createExchange();
        Message message = new DefaultMessage();
        message.setBody(notificationJob);
        exchange.setIn(message);
        template.asyncSend(endpoint, exchange);
    }
}