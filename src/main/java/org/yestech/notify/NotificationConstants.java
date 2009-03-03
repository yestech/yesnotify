package org.yestech.notify;

/**
 * Constants for the Mail System
 *
 */
public interface NotificationConstants
{

    public static final String UPLOAD_DIR = "mailer.upload.directory";
    public static final String EOL = System.getProperty("line.separator", "\n");
    public static final String INTERNAL_PREVIEW_GROUP = "INTERNAL";
    public static final String DEFAULT_SEPARATOR = ",";

    public static final String MAIL_ROOT = "mail";
    public static final String TEMPLATE_ELEMENT = "template-language";
    public static final String LANGUAGE_ELEMENT = "language";
    public static final String NAME_ELEMENT = "name";
    public static final String HANDLER_ELEMENT = "handler";
}
