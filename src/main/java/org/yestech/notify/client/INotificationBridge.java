/*
 *
 * Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.client;

import org.yestech.notify.service.INotificationProducer;
import org.yestech.notify.objectmodel.INotification;

import java.util.Collection;

/**
 * @author Artie Copeland
 * @version $Revision: $
 */
public interface INotificationBridge {
    INotificationProducer getProducer();

    void setProducer(INotificationProducer producer);

    void sendNotification(INotification notification);

    void sendNotifications(Collection<INotification> notifications);
}
