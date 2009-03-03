/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.objectmodel;

import java.io.Serializable;
import java.util.UUID;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface INotificationJob extends Serializable, Comparable<INotificationJob>
{
    UUID getIdentifier();

    void addNotification(INotification notification);

    void addNotifications(Collection<INotification> messages);

    void removeNotification(INotification message);

    void removeNotifications(Collection<INotification> messages);

    Iterator<INotification> getNotifications();

    void clearNotifications();

    int size();

    boolean isEmpty();
}
