package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a user interface and deals with interactions
 * with the user
 */
public class Ui {
    // dummy value 1 for now
    private final String messageHello = Parser.format("Hello! I'm Duke - your personal task manager\n" + "      " +
            "Today is " + java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\n      " + "You have done "
            + TaskList.numberOfDoneTasks + " task(s) in the past week." + "\n      Keep up the good work!!!");
    private Scanner scanner = new Scanner(System.in);

    public void run(TaskList lst) throws Exception {
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
