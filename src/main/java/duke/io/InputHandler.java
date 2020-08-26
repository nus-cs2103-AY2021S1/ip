package duke.io;

import java.util.Scanner;

public class InputHandler {

    Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public String input() {
        return scanner.nextLine();
    }
}
