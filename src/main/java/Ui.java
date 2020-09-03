import java.util.Scanner;
import java.util.List;

/**
 * Represents a user interface and deals with interactions
 * with the user
 */
public class Ui {
    private static String SPACE = "     ";
    private final String messageHello = Parser.format("Hello! I'm Duke\n" + SPACE + " " + "What can I do for you?");
    private Scanner scanner = new Scanner(System.in);

    public void run(List<Task> lst) throws Exception {
        // set up greetings and old list from file
        System.out.println(messageHello);
        System.out.println(new Parser("list").getRespond(lst));

        // hand over to Parser class to handle all commands
        while (scanner.hasNextLine()) {
            String currentCommand = scanner.nextLine();
            System.out.println(new Parser(currentCommand).getRespond(lst));
            if (currentCommand.equals("bye")) {
                scanner.close();
                return;
            }
        }
    }

}
