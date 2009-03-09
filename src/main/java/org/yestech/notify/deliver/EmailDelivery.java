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
import org.yestech.notify.objectmodel.INotification;
import org.yestech.notify.objectmodel.IRecipient;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.Email;
import org.apache.commons.lang.StringUtils;

/**
 * @author $Author: $
 * @version $Revision: $
 */
public abstract class EmailDelivery implements INotificationDelivery {
    final private static Logger logger = LoggerFactory.getLogger(EmailDelivery.class);

    private String emailHost;
    private boolean ssl;
    private boolean tls;
    private String authenticatorUserName;
    private String authenticatorPassword;

    protected EmailDelivery() {
        ssl = false;
        tls = false;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isTls() {
        return tls;
    }

    public void setTls(boolean tls) {
        this.tls = tls;
    }

    public String getAuthenticatorUserName() {
        return authenticatorUserName;
    }

    public void setAuthenticatorUserName(String authenticatorUserName) {
        this.authenticatorUserName = authenticatorUserName;
    }

    public String getAuthenticatorPassword() {
        return authenticatorPassword;
    }

    public void setAuthenticatorPassword(String authenticatorPassword) {
        this.authenticatorPassword = authenticatorPassword;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public void deliver(INotification notification) {
        try {
            sendToReciepients(notification);
            sendToCcReciepients(notification);
            sendToBccReciepients(notification);
        } catch (EmailException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected void sendToReciepients(INotification notification) throws EmailException {
        for (IRecipient recipient : notification.getRecipients()) {
            sendMessage(notification, recipient);
        }
    }

    protected void sendToCcReciepients(INotification notification) throws EmailException {
        for (IRecipient recipient : notification.getCopyRecipients()) {
            sendMessage(notification, recipient);
        }
    }

    protected void sendToBccReciepients(INotification notification) throws EmailException {
        for (IRecipient recipient : notification.getBlindCopyRecipients()) {
            sendMessage(notification, recipient);
        }
    }

    protected abstract void sendMessage(INotification notification, IRecipient recipient) throws EmailException;

    protected void enableAuthenticator(Email email) {
        if (isSsl()) {
            email.setSSL(true);
        }
        if (isTls()) {
            email.setTLS(true);
        }
        if (StringUtils.isNotBlank(getAuthenticatorUserName()) && StringUtils.isNotBlank(getAuthenticatorPassword())) {
            email.setAuthentication(getAuthenticatorUserName(), getAuthenticatorPassword());
        }
    }
}
