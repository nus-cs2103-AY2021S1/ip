import java.util.Scanner;

public class Ui {
    private Parser parser;

    Ui() {
        this.parser = new Parser();
    }

    /**
     * Greets the user with a hard-coded message.
     */
    public void greet() {
        System.out.println("Hello! I am Duke\n" + "What can I do for you?");
    }

    /**
     * Gets input from the user which is then parsed and executed.
     */
    public void getUserInput() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        while (true) {
            this.parser.parseCommands(command, sc);
            command = sc.next();
        }
    }
}
