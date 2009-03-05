/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.deliver;

import org.yestech.notify.objectmodel.INotification;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface INotificationDelivery {

    public void deliver(INotification notification);
}
