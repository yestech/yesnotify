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

import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultMessage;
import org.yestech.notify.objectmodel.INotificationJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;

/**
 * A camel based processor that assumes the body of a {@link Message} is of type
 * {@link org.yestech.notify.objectmodel.INotificationJob}.
 * 
 * @author Artie Copeland
 * @version $Revision: $
 */
public class CamelNotificationConsumer implements INotificationConsumer {

    final private static Logger logger = LoggerFactory.getLogger(CamelNotificationConsumer.class);

    private Map<String, Object> headerParameters;
    private INotificationProcessor processor;

    public INotificationProcessor getProcessor() {
        return processor;
    }

    public Map<String, Object> getHeaderParameters() {
        return headerParameters;
    }

    public void setHeaderParameters(Map<String, Object> headerParameters) {
        this.headerParameters = headerParameters;
    }

    @Required
    public void setProcessor(INotificationProcessor processor) {
        this.processor = processor;
    }

    public void process(Exchange exchange) {
        final Throwable throwable = exchange.getException();
        if (throwable == null) {
            final Message message = exchange.getIn();
            try {
                final INotificationJob notificationJob = message.getBody(INotificationJob.class);
                recieve(notificationJob);
                if (headerParameters != null) {
                    message.setHeaders(headerParameters);
                }
            } catch (Exception e) {
                logger.error("error retrieving notification job from exchange...", e);
                exchange.setException(e);
            }
        } else {
            logger.error("error in the exchange", throwable);
        }
    }

    @Override
    public void recieve(INotificationJob notificationJob) {
        processor.process(notificationJob);
    }
}
