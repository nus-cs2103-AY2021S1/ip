package duke.io;

import java.util.Scanner;

public class IO {
    private Scanner scanner;

    public IO() {
        this.scanner = new Scanner(System.in);
    }

    public String readUserInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "Bye";
        }
    }
}
