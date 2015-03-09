package Modules;

/**
 *
 * @author Jiang Youquan
 */
import java.io.PrintWriter;
import java.io.PrintStream;

public class JDBFException extends Exception {

    public JDBFException(String s) {
        this(s, null);
    }

    public JDBFException(Throwable throwable) {
        this(throwable.getMessage(), throwable);
    }

    public JDBFException(String s, Throwable throwable) {
        super(s);
        detail = throwable;
    }

    @Override
    public String getMessage() {
        if (detail == null) {
            return super.getMessage();
        } else {
            return super.getMessage();
        }
    }

    @Override
    public void printStackTrace(PrintStream printstream) {
        if (detail == null) {
            super.printStackTrace(printstream);
            return;
        }
        PrintStream printstream1 = printstream;
        printstream1.println(this);
        detail.printStackTrace(printstream);
        return;
    }

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintWriter printwriter) {
        if (detail == null) {
            super.printStackTrace(printwriter);
            return;
        }
        PrintWriter printwriter1 = printwriter;

        printwriter1.println(this);
        detail.printStackTrace(printwriter);
        return;
    }
    private Throwable detail;
}

