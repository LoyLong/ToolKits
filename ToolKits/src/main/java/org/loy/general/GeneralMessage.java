package org.loy.general;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The Class GeneralMessage.
 */
public class GeneralMessage implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The message id. */
    private String messageId = null;

    /** The segment list. */
    private List segmentList = null;

    /**
     * public methods *.
     * 
     * @return the string
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("messageId", messageId).append("occurrence", getOccurrence())
            .append("segmentList", segmentList).toString();
    }

    /**
     * Gets the occurrence.
     * 
     * @return the occurrence
     */
    public String getOccurrence() {
        if (null == segmentList) {
            return "0";
        }
        return String.valueOf(segmentList.size());
    }

    /**
     * Adds the segment.
     * 
     * @param segment
     *            the segment
     */
    public void addSegment(Segment segment) {
        if (null == this.segmentList) {
            this.segmentList = new LinkedList();
        }
        this.segmentList.add(segment);
    }

    /**
     * getter and setter *.
     * 
     * @return the message id
     */
    /**
     * Gets the message id.
     * 
     * @return the message id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the message id.
     * 
     * @param messageId
     *            the new message id
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets the segment list.
     * 
     * @return the segment list
     */
    public List getSegmentList() {
        return segmentList;
    }

}
