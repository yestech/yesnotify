/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
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
