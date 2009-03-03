package org.yestech.notify.objectmodel;

/**
 * A mutable {@link INotification}.
 *
 */
public interface MutableNotification extends INotification
{

    /**
     * Allows a {@link INotification} to
     * change {@link NotificationJob}.
     *
     * @param job MailJob
     */
    public void setJob(INotificationJob job);

}
