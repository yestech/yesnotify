/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.client;

import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.objectmodel.NotificationJob;
import org.yestech.notify.service.INotificationProducer;
import org.yestech.notify.service.NoOpNotificationProducer;

import java.util.Collection;
import java.util.UUID;

/**
 * The Bridge to send a Nofitication.
 */
public class NotificationBridge {

    private INotificationProducer producer;

    public NotificationBridge() {
        super();
        producer = new NoOpNotificationProducer();
    }

    private NotificationJob createNotificationJob() {
        UUID uid = UUID.randomUUID();
        return new NotificationJob(uid);
    }

    private void addJobToNotification(INotificationJob job, INotification notification) {
        notification.setJob(job);
    }

    private void addJobToNotifications(INotificationJob job, Collection<INotification> notifications) {
        for (INotification notification : notifications) {
            addJobToNotification(job, notification);
        }
    }

    public INotificationProducer getProducer() {
        return producer;
    }

    public void setProducer(INotificationProducer producer) {
        this.producer = producer;
    }

    /**
     * To create a {@link org.yestech.notify.objectmodel.INotification}, use the
     * {@link NotificationBuilder}.
     *
     * @param notification Notification to send
     */
    public void sendNotification(INotification notification) {
        NotificationJob job = createNotificationJob();
        addJobToNotification(job, notification);
        job.addNotification(notification);
        producer.send(job);
    }

    /**
     * To create a {@link org.yestech.notify.objectmodel.INotification}, use the
     * {@link NotificationBuilder}.
     *
     * @param notifications Notifications to send
     */
    public void sendNotifications(Collection<INotification> notifications) {
        NotificationJob job = createNotificationJob();
        addJobToNotifications(job, notifications);
        job.addNotifications(notifications);
        producer.send(job);
    }
}

