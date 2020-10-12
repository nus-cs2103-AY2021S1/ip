package duke.ui;

import duke.parser.Parser;
import duke.storage.Storage;

import java.util.Scanner;

/**
 * User Interface
 */
public class Ui {
    public static String logo = "   _____  \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    private final Scanner input;
    private static final String GREETING = "Hello!\n" +
            "I'm Aqua, the (useless baka) Goddess of water!\n" +
            "Welcome to this parallel world where you (have to do debugging 7/24)" +
            "can go on an adventure and battle monsters!\n" +
            "I'll help you track your missions until you defeat Devil King(CS2103T)!";

    /**
     * Constructs an User interface.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Prints out the greetings.
     */
    public static void greet() {
        System.out.println(GREETING);
    }

    public static String getGreeting() {

        return GREETING;
    }

    /**
     * Prints out the goodbye words.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the command received.
     *
     * @param command Take in a command.
     */
    public static void echo(String command) {
        System.out.println(command);
    }


    /**
     * Runs the programme.
     */
    public void run() {

        greet();

        Scanner input = new Scanner(System.in);

        String command;

        while (input.hasNext()) {

            try {
                command = input.nextLine();

                if (command.equals("bye")) {

                    exit();

                    input.close();

                    break;

                } else {

                    System.out.println(Parser.parseCommand(command));

                    Storage.saveDataToFile(Parser.taskList);

                }

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }
    }
}
