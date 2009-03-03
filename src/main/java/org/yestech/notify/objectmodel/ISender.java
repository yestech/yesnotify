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
package org.yestech.notify.objectmodel;

import java.io.Serializable;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface ISender extends Serializable, Comparable<ISender>
{
    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    String getReplyAddress();

    void setReplyAddress(String replyAddress);

    String getDisplayName();

    void setDisplayName(String displayName);
}
