/*
 * Copyright LGPL3
 * YES Technology Association
 * http://yestech.org
 *
 * http://www.opensource.org/licenses/lgpl-3.0.html
 */

package org.yestech.notify.template;

import org.springframework.beans.factory.annotation.Required;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Used to persist the internal of {@link org.yestech.notify.template.VelocityTemplateLanguage}
 */
final public class VelocityTemplateLanguagePersistence implements ITemplateLanguagePersistence<HashMap<String, Object>> {

    private HashMap<String, Object> data;
    private String filePath;

    public VelocityTemplateLanguagePersistence() {
        super();
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    @Required
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}