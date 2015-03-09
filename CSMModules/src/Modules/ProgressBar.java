package Modules;
/**
 *
 * @author Administrator
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProgressBar implements ActionListener, ChangeListener {

    JFrame frame = null;
    JProgressBar progressbar;
    ProgressValues pValues;
    JLabel label;
    Timer timer;
    JButton b;

    public ProgressBar(String title, ProgressValues pValues) {
        frame = new JFrame(title);
        frame.setBounds(380, 400, 600, 100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        this.pValues = pValues;

        Container contentPanel = frame.getContentPane();
        label = new JLabel("", JLabel.CENTER);
        progressbar = new JProgressBar();
        progressbar.setOrientation(JProgressBar.HORIZONTAL);
        progressbar.setMinimum((int) pValues.getStratValue());
        progressbar.setMaximum((int) pValues.getEndtValue());
        progressbar.setValue(0);
        progressbar.setStringPainted(true);
        progressbar.addChangeListener(this);
        // progressbar.setPreferredSize(new Dimension(300,20));
        progressbar.setBorderPainted(true);
        progressbar.setBackground(Color.pink);


        JPanel panel = new JPanel();
        /*
        b = new JButton("安装");
        b.setForeground(Color.blue);
        b.addActionListener(this);
        panel.add(b);*/
        timer = new Timer(200, this);
        contentPanel.add(panel, BorderLayout.NORTH);
        contentPanel.add(progressbar, BorderLayout.CENTER);
        contentPanel.add(label, BorderLayout.SOUTH);

        //frame.pack();
        frame.setVisible(true);
        timer.start();


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            timer.start();
        }
        if (e.getSource() == timer) {
            int value = (int) pValues.getCurrentValue();
            if (value < pValues.getEndtValue()) {
                progressbar.setValue(value);
            } else {
                timer.stop();
                frame.dispose();
            }
        }

    }

    public void stateChanged(ChangeEvent e1) {
        int value = progressbar.getValue();
        int endValue = pValues.getEndtValue();
        if (e1.getSource() == progressbar) {
            label.setText("目前已完成进度：" + Integer.toString(value * 100 / endValue) + "%");
            label.setForeground(Color.blue);
        }

    }

    public static void main(String[] args) {
       /* ProgressValues pv = new ProgressValues(0, 1000);
        ProgressBar app = new ProgressBar("test", pv);
        for (int i = 0; i <= pv.getEndtValue(); i++) {
            pv.setCurrentValue(i);
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } */
        progressBarTest test=new progressBarTest();
        Thread t=new Thread(test);
        t.start();
    }
}
class progressBarTest extends Thread {

    ProgressValues pv = new ProgressValues(0, 1000);

    public void run() {
        ProgressBar app = new ProgressBar("test", pv);
        for (int i = 0; i <= pv.getEndtValue(); i++) {
            pv.setCurrentValue(i);
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}