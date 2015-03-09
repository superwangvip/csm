package Modules;

/**
 *
 * @author Jiang Youquan
 */
public class JDBFHeader {

    public byte bFileType[] = new byte[1];//FoxBase/dBaseIII plus,with no memo,0
    public byte[] bLastUpdate = new byte[3];//YYMMDD,YY=Date-1900           ,1-3
    private String LastUpdate = "YYMMDD";
    public byte[] bNumberOfRecords = new byte[4];//                         ,4-7
    public int NumberOfRecords = 0;
    public byte[] bFirstRecPos = new byte[2];//                              ,8-9
    private int firstRecPos = 0;
    private int fieldCount = 0;
    public byte[] bRecordSize = new byte[2];//                              ,10-11
    private int recordLenth = 0;
    private byte[] bReserved = new byte[20];//                                      ,12-31
    //------------- methods for analyzing DBF Header--------------------

    public void setNumberOfRecords() {
        int n0 = bNumberOfRecords[0];
        int n1 = bNumberOfRecords[1];
        int n2 = bNumberOfRecords[2];
        int n3 = bNumberOfRecords[3];
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
        NumberOfRecords = n0 + n1 * 256 + n2 * (256 * 256) + n3 * (256 * 256 * 256);
    }

    public int getNumberOfRecords() {
        return NumberOfRecords;
    }

    public void setFieldCount() {
        int n0 = bFirstRecPos[0];
        int n1 = bFirstRecPos[1];
        //System.out.println("setFieldCount():" + bFirstRecPos[0] + " " + bFirstRecPos[1]);
        if (n0 < 0) {
            n0 = 256 + n0;
        }
        if (n1 < 0) {
            n1 = 256 + n1;
        }

        int n = n0 + n1 * 256;
        firstRecPos = n;
        //fieldCount = (n - 296) / 32;
        fieldCount = (n - 1) / 32 - 1;
    }

    public int getFirstREcoPos() {
        return this.firstRecPos;
    }

    public int getFieldCount() {
        return this.fieldCount;
    }

    public void setRecordLength() {
        int n0 = bRecordSize[0];
        int n1 = bRecordSize[1];
        if (n0 < 0) {
            n0 = n0 + 256;
        }
        if (n1 < 0) {
            n1 = n1 + 256;
        }
        recordLenth = n0 + n1 * 256;
    }

    public int getRecordLength() {
        return recordLenth;
    }
}
