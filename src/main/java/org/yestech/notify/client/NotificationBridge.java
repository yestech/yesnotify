package org.yestech.notify.client;

import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.INotificationJob;
import org.yestech.notify.objectmodel.MutableNotification;
import org.yestech.notify.objectmodel.NotificationJob;

import java.util.Collection;
import java.util.Iterator;
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

    private static NotificationJob createMailJob() {
        UUID uid = UUID.randomUUID();
        NotificationJob job = new NotificationJob(uid);
        return job;
    }

    private static void addJobToNotification(INotificationJob job, INotification message) {
        MutableNotification dmesg = (MutableNotification) message;
        dmesg.setJob(job);
    }

    private static void addJobToMessages(INotificationJob job, Collection messages) {
        Iterator mit = messages.iterator();
        while(mit.hasNext()) {
            INotification message = (INotification) mit.next();
            addJobToNotification(job, message);
        }
    }

    /**
     * Sends an Email Using the Event System.  To create a {@link org.yestech.notify.objectmodel.INotification}, use the
     * {@link NotificationBuilder}.
     *
     * @param message Message to send
     */
    public static void sendNotification(INotification message) {
        NotificationJob job = createMailJob();
        addJobToNotification(job, message);
        job.addNotification(message);
        throw new RuntimeException("Need to Enqueue");
    }

    /**
     * Sends an Email Using the Event System.  To create a {@link org.yestech.notify.objectmodel.INotification}, use the
     * {@link NotificationBuilder}.
     *
     * @param messages Messages to send
     */
    public static void sendNotifications(Collection<INotification> messages) {
        NotificationJob job = createMailJob();
        addJobToMessages(job, messages);
        job.addNotifications(messages);
        throw new RuntimeException("Need to Enqueue");
    }
}

