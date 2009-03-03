/*
 *
 * Original Author:  Artie Copeland
 * Last Modified Date: $DateTime: $
 */
package org.yestech.notify.objectmodel;

import org.yestech.notify.constant.FormatEnum;
import org.yestech.notify.constant.MimeTypeEnum;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public interface IMessage extends Serializable
{
    void addAttachments(Collection attachments);

    void addAttachment(Attachment attachment);

    void clearAttachment();

    boolean isAttachmentEmpty();

    int attachementSize();

    Collection getAttachments();

    String getSubject();

    void setSubject(String subject);

    String getText();

    void setText(String text);

    FormatEnum getMessageType();

    void setMessageType(FormatEnum messageType);

    MimeTypeEnum getMimeType();

    void setMimeType(MimeTypeEnum mimeType);
}
