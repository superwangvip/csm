/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysLock implements ActionListener {

    public void actionPerformed(ActionEvent event) {
        if (MainMenu.NO_ACTION_TIMER >= Main.MAX_IDLE_TIME) {
            new JFrameKeyboardLock().setVisible(true);
        }
        if (JFrameKeyboardLock.sysLocked) {
            MainMenu.NO_ACTION_TIMER = 0;
        } else {
            MainMenu.NO_ACTION_TIMER = MainMenu.NO_ACTION_TIMER + MainMenu.TIMER_STEP;
        }
        System.out.println(MainMenu.NO_ACTION_TIMER);

    }
}
