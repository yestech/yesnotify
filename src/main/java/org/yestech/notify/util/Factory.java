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
package org.yestech.notify.util;

import org.yestech.notify.objectmodel.ISender;
import org.yestech.notify.objectmodel.Sender;
import org.yestech.notify.objectmodel.IRecipient;
import org.yestech.notify.objectmodel.Recipient;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public class Factory {
    private Factory() {

    }

    public static ISender createSender() {
        return new Sender();
    }

    public static ISender createSender(String emailAddress) {
        ISender sender = createSender();
        sender.setEmailAddress(emailAddress);
        return sender;
    }

    public static IRecipient createRecipient() {
        return new Recipient();
    }

    public static IRecipient createRecipient(String emailAddress) {
        IRecipient recipient = createRecipient();
        recipient.setEmailAddress(emailAddress);
        return recipient;
    }
}
