package duke.core;

import java.util.Scanner;

public class Ui {

    private boolean active;
    private Scanner userInput = new Scanner(System.in);

    public Ui() {
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public String receiveInput() {
        String input = userInput.nextLine();
        return input;
    }

    public void deactivate() {
        active = false;
    }
}
