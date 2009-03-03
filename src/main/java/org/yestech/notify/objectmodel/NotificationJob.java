/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.objectmodel;


import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;


/**
 * An Aggragate Class the contains {@link INotification}.
 *
 */
public class NotificationJob implements INotificationJob
{

    static final long serialVersionUID = 8897680828148707131L;

    protected UUID identifier;
    protected Collection<INotification> messages;


    public NotificationJob(UUID identifier) {
        this.identifier = identifier;
        messages = newArrayList();
    }

    /**
     * Return the Immutable Identifier of the Job.
     * @return
     */
    public UUID getIdentifier() {
        return identifier;
    }

    /**
     * Add a {@link INotification} to the Job.  Message is only added if it is not null.
     *
     * @param message Message to add
     */
    public void addNotification(INotification message) {
        if (message != null) {
            messages.add(message);
        }
    }

    /**
     * Add a Collection of {@link INotification}s to the Job.  Message is only added if
     * it is not null.
     *
     * @param messages Messages to add
     */
    public void addNotifications(Collection<INotification> messages) {
        if (messages != null) {
            this.messages.addAll(messages);
        }
    }

    /**
     * Removes a {@link INotification} from the Job
     *
     * @param message Message tp remove
     */
    public void removeNotification(INotification message) {
        messages.remove(message);
    }

    /**
     * Removes a {@link Collection} of {@link INotification} from the Job
     *
     * @param messages Message tp remove
     */
    public void removeNotifications(Collection<INotification> messages) {
        this.messages.removeAll(messages);
    }

    /**
     * Returns an Immutable collection of {@link INotification}s.
     *
     * @return Immutable collection
     */
    public Iterator<INotification> getNotifications() {
        return Collections.unmodifiableCollection(messages).iterator();
    }

    /**
     * Clears all the contained messages from the Job
     */
    public void clearNotifications() {
        messages.clear();
    }

    /**
     * Return the number of {@link INotification}s contained.
     *
     * @return the count
     */
    public int size() {
        return messages.size();
    }

    /**
     * Return whether or not there are no {@link INotification} in the job.
     *
     * @return true if no message are contained, else false
     */
    public boolean isEmpty() {
        return messages.isEmpty();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof INotificationJob)) return false;

        NotificationJob that = (NotificationJob) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        if (messages != null ? !messages.equals(that.messages) : that.messages != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + (messages != null ? messages.hashCode() : 0);
        return result;
    }

    public int compareTo(INotificationJob o)
    {
        return identifier.compareTo(o.getIdentifier());
    }
}
