package MVC.View;

import MVC.Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayHumanActionListener implements ActionListener {

    private final Controller controller;

    public PlayHumanActionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controller != null) {
            controller.playHumanAction(Integer.parseInt(e.getActionCommand()));
        }
    }
}
