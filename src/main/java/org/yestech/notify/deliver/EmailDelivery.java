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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public abstract class EmailDelivery implements INotificationDelivery {
    final private static Logger logger = LoggerFactory.getLogger(EmailDelivery.class);

    private String emailHost;

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }
}
