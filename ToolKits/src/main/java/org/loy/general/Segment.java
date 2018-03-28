package org.loy.general;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class Segment.
 */
public class Segment implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The field key. */
    private String fieldKey;

    /** The field value. */
    private String fieldValue;

    /** The field type. */
    private String fieldType;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("fieldKey", fieldKey).append("fieldValue", fieldValue)
            .append("fieldType", fieldType).append("fieldLength", getFieldLength()).toString();
    }

    /**
     * Gets the field length.
     * 
     * @return the field length
     */
    public String getFieldLength() {
        if (null == fieldValue) {
            return "0";
        }
        return String.valueOf(fieldValue.length());
    }

    /**
     * Gets the field key.
     * 
     * @return the field key
     */
    public String getFieldKey() {
        return fieldKey;
    }

    /**
     * Sets the field key.
     * 
     * @param fieldKey
     *            the new field key
     */
    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    /**
     * Gets the field value.
     * 
     * @return the field value
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * Sets the field value.
     * 
     * @param fieldValue
     *            the new field value
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    /**
     * Gets the field type.
     * 
     * @return the field type
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Sets the field type.
     * 
     * @param fieldType
     *            the new field type
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

}
