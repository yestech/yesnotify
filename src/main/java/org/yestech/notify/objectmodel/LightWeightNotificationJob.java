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
 * An LigthWeight Class that just contains {@link #getIdentifier()}.
 *
 */
public class LightWeightNotificationJob implements INotificationJob
{

    static final long serialVersionUID = 8897680828148707131L;

    protected UUID identifier;


    public LightWeightNotificationJob(UUID identifier) {
        this.identifier = identifier;
    }

    /**
     * Return the Immutable Identifier of the Job.
     * @return
     */
    public UUID getIdentifier() {
        return identifier;
    }

    /**
     * Add a {@link org.yestech.notify.objectmodel.INotification} to the Job.  Message is only added if it is not null.
     *
     * @param message Message to add
     */
    public void addNotification(INotification message) {
    }

    /**
     * Add a Collection of {@link org.yestech.notify.objectmodel.INotification}s to the Job.  Message is only added if
     * it is not null.
     *
     * @param messages Messages to add
     */
    public void addNotifications(Collection<INotification> messages) {
    }

    /**
     * Removes a {@link org.yestech.notify.objectmodel.INotification} from the Job
     *
     * @param message Message tp remove
     */
    public void removeNotification(INotification message) {
    }

    /**
     * Removes a {@link java.util.Collection} of {@link org.yestech.notify.objectmodel.INotification} from the Job
     *
     * @param messages Message tp remove
     */
    public void removeNotifications(Collection<INotification> messages) {
    }

    /**
     * Returns an Immutable collection of {@link org.yestech.notify.objectmodel.INotification}s.
     *
     * @return Immutable collection
     */
    public Iterator<INotification> getNotifications() {
        throw new UnsupportedOperationException("Not implemented in LightWeight");
    }

    /**
     * Clears all the contained messages from the Job
     */
    public void clearNotifications() {
        throw new UnsupportedOperationException("Not implemented in LightWeight");
    }

    /**
     * Return the number of {@link org.yestech.notify.objectmodel.INotification}s contained.
     *
     * @return the count
     */
    public int size() {
        throw new UnsupportedOperationException("Not implemented in LightWeight");
    }

    /**
     * Return whether or not there are no {@link org.yestech.notify.objectmodel.INotification} in the job.
     *
     * @return true if no message are contained, else false
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not implemented in LightWeight");
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof INotificationJob)) return false;

        LightWeightNotificationJob that = (LightWeightNotificationJob) o;

        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = identifier != null ? identifier.hashCode() : 0;
        return result;
    }

    public int compareTo(INotificationJob o)
    {
        return identifier.compareTo(o.getIdentifier());
    }
}