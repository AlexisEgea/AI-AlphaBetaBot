package MVC.View;

import MVC.Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayBotActionListener implements ActionListener {

    private final Controller controller;

    public PlayBotActionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controller != null) {
            controller.playBotAction();
        }
    }
}
