import java.util.Scanner;

public class Ui {
    private Parser parser;

    Ui() {
        this.parser = new Parser();
    }

    public void greet() {
        System.out.println("Hello! I am Wish\n" + "What can I do for you?");
    }

    public void getUserInput() throws WishException {
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        while (true) {
            this.parser.parseCommands(command, sc);
            command = sc.next();
        }
    }
}
