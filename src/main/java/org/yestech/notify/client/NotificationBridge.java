package org.yestech.notify.client;

import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.objectmodel.MutableNotification;
import org.yestech.notify.objectmodel.NotificationJob;

import java.util.Collection;
import java.util.UUID;

/**
 * The Bridge to send a Nofitication.
 *
 */
public class NotificationBridge
{
    private NotificationBridge() {
        super();
    }

    private static NotificationJob createNotificationJob() {
        UUID uid = UUID.randomUUID();
        return new NotificationJob(uid);
    }

    private static void addJobToNotification(INotificationJob job, INotification notification) {
        MutableNotification dmesg = (MutableNotification) notification;
        dmesg.setJob(job);
    }

    private static void addJobToNotifications(INotificationJob job, Collection<INotification> notifications) {
        for (INotification notification : notifications)
        {
            addJobToNotification(job, notification);
        }
    }

    /**
     * To create a {@link org.yestech.notify.objectmodel.INotification}, use the
     * {@link NotificationBuilder}.
     *
     * @param notification Notification to send
     */
    public static void sendNotification(INotification notification) {
        NotificationJob job = createNotificationJob();
        addJobToNotification(job, notification);
        job.addNotification(notification);
        throw new RuntimeException("Need to Enqueue");
    }

    /**
     * To create a {@link org.yestech.notify.objectmodel.INotification}, use the
     * {@link NotificationBuilder}.
     *
     * @param notifications Notifications to send
     */
    public static void sendNotifications(Collection<INotification> notifications) {
        NotificationJob job = createNotificationJob();
        addJobToNotifications(job, notifications);
        job.addNotifications(notifications);
        throw new RuntimeException("Need to Enqueue");
    }
}

