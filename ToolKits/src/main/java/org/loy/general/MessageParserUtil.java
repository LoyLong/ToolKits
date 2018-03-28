package org.loy.general;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

/**
 * The Class MessageParserUtil.
 */
public final class MessageParserUtil {

    /**
     * Instantiates a new message parser util.
     */
    private MessageParserUtil() {}

    /**
     * Parses the msg obj 2 str.
     * 
     * @param msgObj
     *            the msg obj
     * @return the string
     */
    public static String parseMsgObj2Str(GeneralMessage msgObj) {

        if (null == msgObj) {
            return null;
        }

        StringBuilder msgStrBdr = new StringBuilder();
        msgStrBdr.append(StringUtils.rightPad(msgObj.getMessageId(), 30, " "));
        msgStrBdr.append(StringUtils.leftPad(msgObj.getOccurrence(), 4, "0"));

        if (null != msgObj.getSegmentList()) {
            Iterator sgmItr = msgObj.getSegmentList().iterator();
            while (sgmItr.hasNext()) {
                Segment sgmItm = (Segment) sgmItr.next();
                msgStrBdr.append(StringUtils.rightPad(sgmItm.getFieldKey(), 30, " "));
                msgStrBdr.append(StringUtils.rightPad(sgmItm.getFieldType(), 10, " "));
                msgStrBdr.append(StringUtils.leftPad(sgmItm.getFieldLength(), 4, "0"));
                msgStrBdr.append(sgmItm.getFieldValue());
            }
        }

        return msgStrBdr.toString();
    }

    public static GeneralMessage parseMsgStr2Obj(String msgStr) throws Exception {
        if (StringUtils.isBlank(msgStr)) {
            return null;
        }

        GeneralMessage msgObj = new GeneralMessage();

        if (msgStr.length() < 30) {
            throw new Exception("Message '" + msgStr + "' doesn't contain legal : " + Constants.MESSAGE_ID);
        }

        msgObj.setMessageId(msgStr.substring(0, 30).trim());
        msgStr = msgStr.substring(30);

        if (msgStr.length() < 4) {
            throw new Exception("Message '" + msgStr + "' doesn't contain legal : " + Constants.OCCURRENCE);
        }

        int count = Integer.parseInt(msgStr.substring(0, 4));
        msgStr = msgStr.substring(4);

        for (int i = 0; i < count; i++) {
            Segment sgmItm = new Segment();

            if (msgStr.length() < 30) {
                throw new Exception("Message '" + msgStr + "' doesn't contain legal : " + Constants.FIELD_KEY);
            }
            sgmItm.setFieldKey(msgStr.substring(0, 30).trim());
            msgStr = msgStr.substring(30);

            if (msgStr.length() < 10) {
                throw new Exception("Message '" + msgStr + "' doesn't contain legal : " + Constants.FIELD_TYPE);
            }
            sgmItm.setFieldType(msgStr.substring(0, 10).trim());
            msgStr = msgStr.substring(10);

            if (msgStr.length() < 4) {
                throw new Exception("Message '" + msgStr + "' doesn't contain legal : " + Constants.FIELD_LENGTH);
            }

            int fieldLength = Integer.parseInt(msgStr.substring(0, 4));
            msgStr = msgStr.substring(4);

            if (msgStr.length() < fieldLength) {
                throw new Exception("Message '" + msgStr + "' doesn't contain legal : " + Constants.FIELD_VALUE + " with specified length : " + fieldLength);
            }
            sgmItm.setFieldValue(msgStr.substring(0, fieldLength).trim());
            msgStr = msgStr.substring(fieldLength);

            msgObj.addSegment(sgmItm);
        }

        return msgObj;
    }
    
}
