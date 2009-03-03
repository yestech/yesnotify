package org.yestech.notify.dao;

import org.yestech.notify.objectmodel.IAggregateNotificationJob;

import java.util.Collection;

/**
 * Represents the Persistence of a {@link org.yestech.notify.objectmodel.AggregateNotificationJob}.
 *
 */
public interface AggregateNotificationJobDao
{

	public long create( IAggregateNotificationJob job );
    
	public IAggregateNotificationJob load( long id );
    
	public void store( IAggregateNotificationJob job );
    
	public void remove( long id );
    
	public long findByPrimaryKey( long id );
    
	public Collection findByBlastRecipient( long id );
}
