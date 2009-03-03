package org.yestech.notify.objectmodel;


/**
 * A {@link Message} attachment that can be added.
 *
 */
public class Attachment implements IAttachment
{

    private String fileName;

    public Attachment(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public int hashCode()
    {
        return fileName != null ? fileName.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Attachment)) return false;

        Attachment that = (Attachment) o;

        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;

        return true;
    }

    public int compareTo(IAttachment o)
    {
        return fileName.compareTo(o.getFileName());
    }
}
