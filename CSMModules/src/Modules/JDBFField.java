package Modules;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jiang Youquan
 */
public class JDBFField {

    byte[] bFieldName = new byte[11];//                                   ,0-10
    private String fieldName = "";
    byte[] bfieldType = new byte[1];//C-Character,N-Numeric,D-date,       ,11
    private char fieldType = 'C';
    byte[] bFieldOffset = new byte[4];//                                  ,12-15
    private int fieldOffset = 0;
    byte bFieldSize[] = new byte[1]; //                                   ,16
    private int fieldLength = 0;
    byte bDecimalPlace[] = new byte[1];//                                 ,17
    private int decimalPlace = 0;
    private byte[] reserved = new byte[14];//                              ,18-31

    public void setFeildName() {
        char c[] = new char[11];
        for (int i = 0; i < 11; i++) {
            c[i] = (char) bFieldName[i];
        }
        this.fieldName = String.valueOf(c);
    }

    public JDBFField() {
    }

    public JDBFField(String name, char type, int len, int decimal)
            throws JDBFException {
        if (name.length() > 10) {
            throw new JDBFException("The field name is more than 10 characters long: " + name);
        }
        if (type != 'C' && type != 'N' && type != 'L' && type != 'D' && type != 'F') {
            throw new JDBFException("The field type is not a valid. Got: " + type);
        }
        if (len < 1) {
            throw new JDBFException("The field length should be a positive integer. Got: " + len);
        }
        if (type == 'C' && len >= 254) {
            throw new JDBFException("The field length should be less than 254 characters for character fields. Got: " + len);
        }
        if (type == 'N' && len > 26) {
            throw new JDBFException("The field length should be less than 27 digits for numeric fields. Got: " + len);
        }
        if (type == 'L' && len != 1) {
            throw new JDBFException("The field length should be 1 characater for logical fields. Got: " + len);
        }
        if (type == 'D' && len != 8) {
            throw new JDBFException("The field length should be 8 characaters for date fields. Got: " + len);
        }
        if (type == 'F' && len >= 21) {
            throw new JDBFException("The field length should be less than 21 digits for floating point fields. Got: " + len);
        }
        if (decimal < 0) {
            throw new JDBFException("The field decimal count should not be a negative integer. Got: " + decimal);
        }
        if ((type == 'C' || type == 'L' || type == 'D') && decimal != 0) {
            throw new JDBFException("The field decimal count should be 0 for character, logical, and date fields. Got: " + decimal);
        }
        if (decimal > len - 1) {
            throw new JDBFException("The field decimal count should be less than the length - 1. Got: " + decimal);
        } else {
            fieldName = name;
            bFieldName =name.getBytes();
            fieldType = type;
            fieldLength = len;
            decimalPlace = decimal;
            return;
        }
    }

    public String getFieldName() {
        return this.fieldName.trim();
    }

    public void setfieldType() {
        this.fieldType = (char) bfieldType[0];
    }

    public char getFieldType() {
        return this.fieldType;
    }

    public void setFieldOffset() {
        int n0 = bFieldOffset[0];
        int n1 = bFieldOffset[1];
        int n2 = bFieldOffset[2];
        int n3 = bFieldOffset[3];
        if (n0 < 0) {
            n0 = n0 + 256;
        }
        if (n1 < 0) {
            n1 = n1 + 256;
        }
        if (n2 < 0) {
            n2 = n2 + 256;
        }
        if (n3 < 0) {
            n3 = n3 + 256;
        }
        fieldOffset = n0 + n1 * 256 + n2 * (256 * 256) + n3 * (256 * 256 * 256);
    }

    public int getFieldOffset() {
        return fieldOffset;
    }

    public void setFieldLength() {
        int n = bFieldSize[0];
        if (n < 0) {
            n = n + 256;
        }
        this.fieldLength = n;
    }

    public int getFieldLength() {
        return this.fieldLength;
    }

    public void setDecimalPlace() {
        int n = bDecimalPlace[0];
        if (n < 0) {
            n = n + 256;
        }
        this.decimalPlace = n;
    }

    public int getDecimalPlace() {
        return this.decimalPlace;
    }

    public String format(Object obj)
            throws JDBFException {
        if (fieldType == 'N' || fieldType == 'F') {
            if (obj == null) {
                obj = new Double(0.0D);
            }
            if (obj instanceof Number) {
                Number number = (Number) obj;
                StringBuffer stringbuffer = new StringBuffer(getFieldLength());
                for (int i = 0; i < getFieldLength(); i++) {
                    stringbuffer.append("#");
                }

                if (getDecimalPlace() > 0) {
                    stringbuffer.setCharAt(getFieldLength() - getDecimalPlace() - 1, '.');
                }
                DecimalFormat decimalformat = new DecimalFormat(stringbuffer.toString());
                String s1 = decimalformat.format(number);
                int k = getFieldLength() - s1.length();
                if (k < 0) {
                    throw new JDBFException("Value " + number + " cannot fit in pattern: '" + stringbuffer + "'.");
                }
                StringBuffer stringbuffer2 = new StringBuffer(k);
                for (int l = 0; l < k; l++) {
                    stringbuffer2.append(" ");
                }

                return stringbuffer2 + s1;
            } else {
                throw new JDBFException("Expected a Number, got " + obj.getClass() + ".");
            }
        }
        if (fieldType == 'C') {
            if (obj == null) {
                obj = "";
            }
            if (obj instanceof String) {
                String s = (String) obj;
                if (s.length() > getFieldLength()) {
                    throw new JDBFException("'" + obj + "' is longer than " + getFieldLength() + " characters.");
                }
                StringBuffer stringbuffer1 = new StringBuffer(getFieldLength() - s.length());
                for (int j = 0; j < getFieldLength() - s.length(); j++) {
                    stringbuffer1.append(' ');
                }

                return s + stringbuffer1;
            } else {
                throw new JDBFException("Expected a String, got " + obj.getClass() + ".");
            }
        }
        if (fieldType == 'L') {
            if (obj == null) {
                obj = new Boolean(false);
            }
            if (obj instanceof Boolean) {
                Boolean boolean1 = (Boolean) obj;
                return boolean1.booleanValue() ? "Y" : "N";
            } else {
                throw new JDBFException("Expected a Boolean, got " + obj.getClass() + ".");
            }
        }
        if (fieldType == 'D') {
            if (obj == null) {
                obj = new Date();
            }
            if (obj instanceof Date) {
                Date date = (Date) obj;
                SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
                return simpledateformat.format(date);
            } else {
                throw new JDBFException("Expected a Date, got " + obj.getClass() + ".");
            }
        } else {
            throw new JDBFException("Unrecognized JDBFField type: " + fieldType);
        }
    }

    public Object parse(String s)
            throws JDBFException, ParseException {
        s = s.trim();
        if (fieldType == 'N' || fieldType == 'F') {
            if (s != null && (s.trim().equals("-") || s.contains("-."))) {
                s = "0";
            }
            if (s.equals("")) {
                s = "0";
            }
            try {
                if (getDecimalPlace() == 0) {
                    return new Long(s);
                } else {
                    return new Double(s);
                }
            } catch (NumberFormatException numberformatexception) {
                throw new JDBFException(numberformatexception);
            }
        }
        if (fieldType == 'C') {
            return s;
        }
        if (fieldType == 'L') {
            if (s.equals("Y") || s.equals("y") || s.equals("T") || s.equals("t")) {
                return new Boolean(true);
            }
            if (s.equals("N") || s.equals("n") || s.equals("F") || s.equals("f")) {
                return new Boolean(false);
            } else {
                throw new JDBFException("Unrecognized value for logical field: " + s);
            }
        }
        if (fieldType == 'D') {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
            try {
                if ("".equals(s)) {
                    return null;
                } else {
                    return simpledateformat.parse(s);
                }
            } catch (ParseException parseexception) {
                throw new JDBFException(parseexception);
            }
        } else {
            throw new JDBFException("Unrecognized JDBFField type: " + fieldType);
        }
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
