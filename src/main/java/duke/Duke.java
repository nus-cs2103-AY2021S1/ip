package duke;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcome();
        Scanner sc = new Scanner(System.in);

        TaskList t = new TaskList();

        String input = "";

        while (!input.equals("bye")) {
            input = sc.nextLine();
            Parser.allocate(input, t);
        }

        Ui.printGoodbye();
    }
}
