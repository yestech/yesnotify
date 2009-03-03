package org.yestech.notify.dao;

import org.yestech.notify.constant.StatusEnum;
import org.yestech.notify.objectmodel.IAggregateNotificationRecipient;

/**
 * Represents the Persistence of a {@link org.yestech.notify.objectmodel.AggregateNotificationJob} Recipient.
 *
 */
public interface AggregateNotificationRecipientDao
{

    /**
     * Persists a new {@link org.yestech.notify.objectmodel.AggregateNotificationRecipient}.
     *
     * @param r The Recipient to Persist
     */
	public void create( IAggregateNotificationRecipient r );

    /**
     *
     * @param id
     * @return
     */
	public IAggregateNotificationRecipient load( long id );

    /**
     *
     * @param r
     */
	public void store( IAggregateNotificationRecipient r );

    /**
     *
     * @param id
     */
	public void remove( long id );

    /**
     *
     * @param r
     * @param mailJobId
     * @param status
     */
	public void updateRecipientStatus(IAggregateNotificationRecipient r, long mailJobId, StatusEnum status);

}
