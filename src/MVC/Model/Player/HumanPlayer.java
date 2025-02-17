package MVC.Model.Player;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer() {
        super();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int decide() {
        System.out.print("Your action: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Error Wrong Action: Column integer needed.");
            System.out.print("Your action: ");
            scanner.next();
        }

        return scanner.nextInt();
    }
}
