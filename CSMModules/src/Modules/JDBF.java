/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

/**
 *
 * @author Jiang Youquan
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class JDBF {

    private String filename = null;//dbf文件名
    public JDBFHeader header = null;//dbf文件头
    public JDBFField fields[] = null;//用于描述字段结构的数组
    public RandomAccessFile dbf = null;//用于读写dbf
    private byte recBuff[] = null;//记录缓冲区
    private int recCount = 0;//记录总数
    private boolean REC_ADDED = false;//是否追加记录
    private boolean IS_NEW = false;//是否新建dbf
    private String dbfEncoding = null;//"gb2312";//dbf数据编码方式
    //------------------------------------for lock --------
    FileChannel fc = null;//用于加锁的文件通道
    FileLock fl = null;//文件锁
    boolean SHARE = true;//共享锁标准
    boolean EXCLUSIVE = false;//独占锁标准
    final long LOCK_BASE = 0x40000000L; //文件琐起始位置
    final long LOCK_OFFSET = 0x3FFFFFFFL; //文件逻辑锁长度
    final int TRY_TIMES = 10;//加锁尝试次数
    //--------------------------------------

    //JDBF类的构造方法：参数fields是新建dbf的字段类数组，描述要新建dbf的字段结构
    //当 fields为null时，表示已经存在名为filename的dbf
    public JDBF(String filename, JDBFField fields[]) throws FileNotFoundException, IOException {
        if (fields != null) {//创建dbf
            try {
                create(filename, fields);
                this.filename = filename;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JDBF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JDBFException ex) {
                Logger.getLogger(JDBF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {//直接打开已存在的dbf
            use(filename);
        }
    }

    //打开已存在的dbf:以随机读写方式打开dbf，读取并解析dbf结构
    private void use(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename);
        dbf = new RandomAccessFile(f, "rw");
        readHeader(dbf);
        this.filename = filename;
        recCount = header.NumberOfRecords;
    }

    //读取dbf头
    private void readHeader(RandomAccessFile rf) throws IOException {
        header = new JDBFHeader();
        //读出文件头结构前5项值,并解析关键三项信息：numberOfRecords，fieldCount，recordLength
        rf.read(header.bFileType);
        rf.read(header.bLastUpdate);
        rf.read(header.bNumberOfRecords);
        rf.read(header.bFirstRecPos);
        rf.read(header.bRecordSize);
        header.setNumberOfRecords();
        header.setFieldCount();
        header.setRecordLength();
        //读出并解析字段结构信息
        int n = header.getFieldCount();
        fields = new JDBFField[n];
        for (int i = 0; i < n; i++) {
            //读出字段结构信息
            fields[i] = new JDBFField();
            rf.seek(i * 32 + 32);
            rf.read(fields[i].bFieldName);
            rf.read(fields[i].bfieldType);
            rf.read(fields[i].bFieldOffset);
            rf.read(fields[i].bFieldSize);
            rf.read(fields[i].bDecimalPlace);
            //解析字段结构信息
            fields[i].setFeildName();
            fields[i].setfieldType();
            fields[i].setFieldLength();
            fields[i].setDecimalPlace();
        }
    }

    //1.读取第n条记录(0<=n<maxRecordNumber)
    //返回对象数组，每个元素为一个字段,类型与dbf结构的对应关系如下：
    //'C',dbf 的字符型->java的String类；
    //'N',dbf的整型->java的Long；
    //'N',dbf的浮点型->java的Double；
    //'L',dbf的逻辑型->java的Boolean;(y,'Y'-->true,'N','n'-->false)；
    //'D',dbf的日期型->java的SimpleDateFormat(yyyyMMdd)
    public Object[] readRecord(int n) throws JDBFException, IOException, ParseException, ParseException {
        int maxRec = header.getNumberOfRecords();
        if (n >= maxRec) {
            throw new JDBFException("readRecord(int n)异常:记录号n=" + n + ",超出最大记录数:" + maxRec);
        }
        //定位读取的起始记录位置
        int recPos = header.getFirstREcoPos() + header.getRecordLength() * n;
        this.dbf.seek(recPos);
        //读取记录
        recBuff = new byte[header.getRecordLength()];//创建记录缓冲区
        dbf.readFully(recBuff);//读取记录数据到记录缓冲区
        //解析记录内的各字段
        Object aobj[] = new Object[fields.length];//创建记录对象
        //依次解析每一个字段
        int i = 1;
        for (int j = 0; j < aobj.length; j++) {
            int k = fields[j].getFieldLength();
            String fieldStr = new String(recBuff, i, k);
            aobj[j] = fields[j].parse(fieldStr);
            i += fields[j].getFieldLength();
        }
        return aobj;
    }

    //2.读取第n条记录(0<=n<maxRecordNumber)到字节数组
    public byte[] readRecBuff(int n) throws JDBFException, IOException {
        int maxRec = header.getNumberOfRecords();
        if (n > maxRec) {
            throw new JDBFException(" getRecBuff(int n)异常:记录号n=" + n + ",超出最大记录数:" + maxRec);
        }
        //定位读取的起始记录位置
        int recPos = header.getFirstREcoPos() + header.getRecordLength() * n;
        this.dbf.seek(recPos);
        byte recBuffer[] = new byte[header.getRecordLength()];//创建记录缓冲区
        dbf.readFully(recBuffer);//读取记录数据到记录缓冲区
        //String s = new String(recBuffer);
        // System.out.println(s);
        return recBuffer;
    }

    //3.从某条记录开始一次读取多条记录
    public Object[][] readBlock(int startRecNo, int span) throws JDBFException, IOException, ParseException, ParseException {
        int maxRec = header.getNumberOfRecords();
        if (startRecNo + span > maxRec) {
            throw new JDBFException("readBlock(int startRecNo,int span)异常: 要读取的记录超出文件范围");
        }
        Object objBlock[][] = new Object[span][];
        int recPos = header.getFirstREcoPos() + header.getRecordLength() * startRecNo;
        this.dbf.seek(recPos);//定位到要读取的起始位置
        recBuff = new byte[header.getRecordLength() * span];
        dbf.readFully(recBuff);//读取数据块到块缓冲区
        //解析各条记录内的各字段值
        for (int m = 0; m < span; m++) {
            Object obj[] = new Object[fields.length];//创建记录对象
            byte recBuff1[] = new byte[header.getRecordLength()];//创建记录缓冲区
            for (int n = 0; n < header.getRecordLength(); n++) {
                //从块缓冲区取一条记录数据到记录缓冲区
                recBuff1[n] = recBuff[n + m * header.getRecordLength()];
            }
            //依次解析每一个字段
            int i = 1;
            for (int j = 0; j < obj.length; j++) {
                int k = fields[j].getFieldLength();
                String fieldStr = new String(recBuff1, i, k);
                obj[j] = fields[j].parse(fieldStr);//解析字段内容
                i += fields[j].getFieldLength();
            }
            objBlock[m] = obj;
        }
        return objBlock;
    }

    //创建dbf:按照JDBFField类的数组描述的字段结构，创建dbf文件
    public void create(String filename, JDBFField fields[]) throws FileNotFoundException, JDBFException {
        dbf = new RandomAccessFile(new File(filename), "rw");
        try {
            writeHeader(fields);//写dbf文件头
            for (int i = 0; i < fields.length; i++) {
                writeFieldHeader(fields[i]);//写dbf文件头的字段结构
            }
            dbf.write(13);//头记录结标志
        } catch (Exception exception) {
            throw new JDBFException(exception);
        }
        this.filename = filename;
        this.fields = fields;
        recCount = 0;
        IS_NEW = true;
    }

    //写dbf文件头
    private void writeHeader(JDBFField jdbfield[])
            throws IOException {
        byte abyte0[] = new byte[16];
        abyte0[0] = 3;
        Calendar calendar = Calendar.getInstance();
        abyte0[1] = (byte) (calendar.get(1) - 1900);
        abyte0[2] = (byte) calendar.get(2);
        abyte0[3] = (byte) calendar.get(5);
        abyte0[4] = 0;
        abyte0[5] = 0;
        abyte0[6] = 0;
        abyte0[7] = 0;
        int i = (jdbfield.length + 1) * 32 + 1;
        abyte0[8] = (byte) (i % 256);
        abyte0[9] = (byte) (i / 256);
        int j = 1;
        for (int k = 0; k < jdbfield.length; k++) {
            j += jdbfield[k].getFieldLength();
        }

        abyte0[10] = (byte) (j % 256);
        abyte0[11] = (byte) (j / 256);
        abyte0[12] = 0;
        abyte0[13] = 0;
        abyte0[14] = 0;
        abyte0[15] = 0;
        dbf.write(abyte0, 0, abyte0.length);
        for (int l = 0; l < 16; l++) {
            abyte0[l] = 0;
        }

        dbf.write(abyte0, 0, abyte0.length);
    }

    //写dbf文件头的字段结构
    private void writeFieldHeader(JDBFField jdbfield)
            throws IOException {

        byte abyte0[] = new byte[16];
        for (int i = 0; i < jdbfield.bFieldName.length && i < 11; i++) {
            abyte0[i] = jdbfield.bFieldName[i];
        }
        
        abyte0[11] = (byte) jdbfield.getFieldType();
        abyte0[12] = 0;
        abyte0[13] = 0;
        abyte0[14] = 0;
        abyte0[15] = 0;
        dbf.write(abyte0, 0, abyte0.length);
        for (int l = 0; l < 16; l++) {
            abyte0[l] = 0;
        }

        abyte0[0] = (byte) jdbfield.getFieldLength();
        abyte0[1] = (byte) jdbfield.getDecimalPlace();
        dbf.write(abyte0, 0, abyte0.length);
    }

    //添加记录:把调用readRecord(int n)读出的数据写入到dbf文件尾
    public void addRecord(Object record[])
            throws JDBFException, UnsupportedEncodingException {
        if (record.length != fields.length) {
            throw new JDBFException("Error adding record: Wrong number of values. Expected " + fields.length + ", got " + record.length + ".");
        }
        //把记录的各字段数据转换为字节数组,并写入dbf文件
        byte recBuffer[] = setRecord(record);
        try {
            dbf.write(32);
            dbf.write(recBuffer, 0, recBuffer.length);
        } catch (IOException ioexception) {
            throw new JDBFException(ioexception);
        }
        recCount++;
        REC_ADDED = true;
    }

    //以字节缓冲区追加记录: 由getRecBuff(int n)读出的数据写入dbf文件尾
    public void addRecord(byte recBuff[]) throws IOException {
        //定位到最后一条记录位置
        int recPos = header.getFirstREcoPos() + header.getRecordLength() * (header.getNumberOfRecords());
        dbf.seek(recPos);
        //写数据
        dbf.write(recBuff);
        header.NumberOfRecords++;
        recCount = header.NumberOfRecords;
        REC_ADDED = true;
    }

    //以字节缓冲区更新第n条记录： 由getRecBuff(int n)读出的数据写入dbf文件第n条记录处
    public void updateRecord(byte recBuff[], int n) throws JDBFException, IOException {
        int maxRec = header.getNumberOfRecords();
        if (n > maxRec) {
            throw new JDBFException("updateRecord(Object record[], int n)异常:记录号n=" + n + ",超出最大记录数:" + maxRec);
        }
        //定位要更新的记录起始位置
        int recPos = header.getFirstREcoPos() + header.getRecordLength() * (n - 1);
        dbf.seek(recPos);
        //写数据
        dbf.write(recBuff);
    }

    public void close()
            throws JDBFException, IOException {
        try {
            if (REC_ADDED || IS_NEW) {//如果追加了记录，则修改记录总数
                dbf.write(26);//文件结束标志
                dbf.seek(4L);
                byte abyte0[] = new byte[4];
                abyte0[0] = (byte) (recCount % 256);
                abyte0[1] = (byte) ((recCount / 256) % 256);
                abyte0[2] = (byte) ((recCount / 0x10000) % 256);
                abyte0[3] = (byte) ((recCount / 0x1000000) % 256);
                dbf.write(abyte0, 0, abyte0.length);
            }
            dbf.close();
            dbf = null;
        } catch (IOException ioexception) {
            throw new JDBFException(ioexception);
        }
    }

    public String getFilename() {
        return this.filename;
    }

    //解析字段:解析由 readRecord(int n)读出的记录(对象数组)
    public String[] getFields(Object[] record) throws JDBFException {
        int n = this.header.getFieldCount();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = record[i].toString();
        }
        return s;
    }

    //解析字段;解析由getRecBuff(int n)读出的记录(字节数组)
    public String[] getFields(byte[] record) throws JDBFException {
        String[] s = new String[this.header.getFieldCount()];
        int n = header.getFieldCount();
        //解析字段
        int l = record.length;
        int c = header.getFieldCount();
        int m = 1;
        for (int j = 0; j < c; j++) {
            int flen = fields[j].getFieldLength();
            byte fb[] = new byte[flen];
            for (int k = 0; k < flen; k++) {
                fb[k] = record[m + k];
            }
            String s1 = new String(fb);
            s[j] = s1;
            m = m + flen;
        }
        return s;
    }

    //设置记录：将字符串数组组合为dbf记录,每个数组元素为一个字段
    public byte[] setRecord(String fields[]) throws JDBFException {
        Object record[] = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            record[i] = fields[i];
        }
        byte[] recBuffer = setRecord(record);
        return recBuffer;
    }

    //设置记录：将对象数组组合为dbf记录,每个数组元素为一个字段
    public byte[] setRecord(Object record[]) throws JDBFException {
        //把记录的各字段数据转换为字节数组
        int i = 0;
        for (int j = 0; j < fields.length; j++) {
            i += fields[j].getFieldLength();
        }

        byte recBuffer[] = new byte[i];
        int k = 0;
        for (int l = 0; l < fields.length; l++) {
            String s = fields[l].format(record[l]);
            byte abyte1[];
            try {
                if (dbfEncoding != null) {
                    abyte1 = s.getBytes(dbfEncoding);
                } else {
                    abyte1 = s.getBytes();
                }
            } catch (UnsupportedEncodingException unsupportedencodingexception) {
                throw new JDBFException(unsupportedencodingexception);
            }
            for (int i1 = 0; i1 < fields[l].getFieldLength(); i1++) {
                recBuffer[k + i1] = abyte1[i1];
            }

            k += fields[l].getFieldLength();
        }
        return recBuffer;
    }

    //返回字段序号
    public int getFieldNumber(String fieldName) throws JDBFException {
        int n = -1;
        int l = this.fields.length;
        boolean found = false;
        for (int i = 0; i < l; i++) {
            String s = fields[i].getFieldName();
            if (fields[i].getFieldName().equalsIgnoreCase(fieldName)) {
                n = i;
                found = true;
                i = l;
            }
        }
        if (!found) {
            throw new JDBFException("字段不存在：" + fieldName);
        }
        return n;
    }

    //dbf文件加锁
    public boolean flock() {
        boolean locked = false;
        String msg = filename + "文件加锁失败,需要重试?";
        if (dbf == null) {
            JOptionPane.showMessageDialog(null, filename + ":没有打开，不能加锁!");
            return locked;
        }
        if (fc == null) {
            fc = dbf.getChannel();
        }
        int n = 0;
        while (!locked && n < TRY_TIMES) {
            try {
                fl = fc.tryLock(LOCK_BASE, LOCK_OFFSET, EXCLUSIVE); //非阻塞方式
                Random r = new Random();
                int t = r.nextInt(500);
                Thread.sleep(t);
                n++;
                if (fl != null) {
                    locked = true;
                }
                if (n == TRY_TIMES && !locked) {
                    if (JOptionPane.showConfirmDialog(null, msg) == JOptionPane.YES_OPTION) {
                        n = 0;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, filename + ":" + ex.getLocalizedMessage());
                n = TRY_TIMES;
                fl = null;
                fc = null;
            } catch (OverlappingFileLockException ex) {
                JOptionPane.showMessageDialog(null, filename + ":" + "OverlappingFileLockException:");
                n = TRY_TIMES;
                fl = null;
                fc = null;
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                n = TRY_TIMES;
                fl = null;
                fc = null;
            }
        }
        return locked;
    }
    //dbf记录加锁

    public boolean rlock(int recNum) {
        unlock();
        boolean locked = false;
        String msg = filename + "记录加锁失败,需要重试?";
        if (dbf == null) {
            JOptionPane.showMessageDialog(null, filename + ":没有打开，不能加锁!");
            return locked;
        }
        if (fc == null) {
            fc = dbf.getChannel();
        }
        int n = 0;
        while (!locked && n < TRY_TIMES) {
            try {
                int offset = header.getFirstREcoPos() + header.getRecordLength() * (recNum - 1);
                int rlen = header.getRecordLength();
                fl = fc.tryLock(LOCK_BASE + offset, rlen, EXCLUSIVE); //非阻塞方式
                Random r = new Random();
                int t = r.nextInt(500);
                Thread.sleep(t);
                n++;
                if (fl != null) {
                    locked = true;
                }
                if (n == TRY_TIMES && !locked) {
                    if (JOptionPane.showConfirmDialog(null, msg) == JOptionPane.YES_OPTION) {
                        n = 0;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, filename + ":" + ex.getLocalizedMessage());
                n = TRY_TIMES;
                fl = null;
                fc = null;
            } catch (OverlappingFileLockException ex) {
                JOptionPane.showMessageDialog(null, filename + ":" + "OverlappingFileLockException:");
                n = TRY_TIMES;
                fl = null;
                fc = null;
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                n = TRY_TIMES;
                fl = null;
                fc = null;
            }
        }
        return locked;
    }

    //dbf文件解锁
    void unlock() {
        if (fl != null) {
            try {
                fl.release();
                System.out.println("file lock release:OK!");
            } catch (IOException ex) {
                System.out.println("file lock release:Failed!");
            }
        }
    }
}
