package duke.ui;

import com.sun.security.jgss.GSSUtil;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;


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

//        System.out.println("Hello from\n" + logo);
//
//        System.out.println("What can I do for you?");

        System.out.println("Hello!\n" +
                "I'm Aqua, the (u̶s̶e̶l̶e̶s̶s̶ ̶b̶a̶k̶a̶) Goddess of water!\n" +
                "Welcome to this parallel world where you (h̶a̶v̶e̶ ̶t̶o̶ ̶d̶o̶ ̶d̶e̶b̶u̶g̶g̶i̶n̶g̶ ̶7̶/̶2̶4̶)" +
                "can go on an adventure and battle monsters!\n" +
                "I'll help you track your missions until you defeat Devil King(C̶S̶2̶1̶0̶3̶T̶)!");
    }
    public static String getGreeting() {

        String greeting = "Hello!\n" +
                "I'm Aqua, the (u̶s̶e̶l̶e̶s̶s̶ ̶b̶a̶k̶a̶) Goddess of water!\n" +
                "Welcome to this parallel world where you (h̶a̶v̶e̶ ̶t̶o̶ ̶d̶o̶ ̶d̶e̶b̶u̶g̶g̶i̶n̶g̶ ̶7̶/̶2̶4̶)" +
                "can go on an adventure and battle monsters!\n" +
                "I'll help you track your missions until you defeat Devil King(C̶S̶2̶1̶0̶3̶T̶)!";
         return greeting;
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

                if (command.contains("bye")) {

                    exit();

                    input.close();

                    break;

                } else {

                    System.out.println(Parser.processCommand(command));

//                    Storage storage = new Storage(Parser.taskList);

                    Storage.saveDataToFile(Parser.taskList);

                }

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }
    }
}
