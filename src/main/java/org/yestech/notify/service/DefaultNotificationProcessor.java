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
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.IMessage;
import org.yestech.notify.deliver.INotificationDelivery;
import org.yestech.notify.deliver.DeliverableMessageType;
import org.yestech.notify.constant.MessageTypeEnum;
import org.terracotta.modules.annotations.InstrumentedClass;

import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Iterator;
import java.util.Map;

/**
 * @author $Author: $
 * @version $Revision: $
 */
@InstrumentedClass
public class DefaultNotificationProcessor implements INotificationProcessor {
    private Map<MessageTypeEnum, INotificationDelivery> deliveries = newHashMap();

    public void setDeliveryList(List<INotificationDelivery> deliveryList) {
        for (INotificationDelivery delivery : deliveryList) {
            DeliverableMessageType annotation = delivery.getClass().getAnnotation(DeliverableMessageType.class);
            MessageTypeEnum[] typeEnums = annotation.value();
            for (MessageTypeEnum typeEnum : typeEnums) {
                deliveries.put(typeEnum, delivery);
            }
        }
    }

    public Map<MessageTypeEnum, INotificationDelivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Map<MessageTypeEnum, INotificationDelivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public void process(INotificationJob notificationJob) {
        Iterator<INotification> notificationIterator = notificationJob.getNotifications();
        if (notificationIterator != null) {
            while (notificationIterator.hasNext()) {
                INotification notification = notificationIterator.next();
                IMessage message = notification.getMessage();
                MessageTypeEnum messageType = message.getMessageType();
                INotificationDelivery deliverer = deliveries.get(messageType);
                deliverer.deliver(notification);
            }
        }
    }
}
