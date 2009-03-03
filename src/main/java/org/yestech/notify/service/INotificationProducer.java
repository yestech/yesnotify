/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.service;

import org.yestech.notify.objectmodel.INotificationJob;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface INotificationProducer {
    
    void send(INotificationJob notificationJob);

}
