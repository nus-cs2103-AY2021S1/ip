package duke;

import duke.commands.Command;
import duke.commands.CommandHandler;
import duke.tasks.TaskManager;
import java.util.Scanner;

/**
 * Main class that will oversee the running of the program.
 */
public class Duke {
//    Ui ui;
//    TaskManager taskManager;
//    CommandHandler commandHandler;
//
//    Duke() {
//        this.ui = new Ui();
//        this.commandHandler = new CommandHandler();
//        try {
//            this.taskManager = new TaskManager(new Storage().load());
//        } catch (DukeException e) {
//            System.out.println(e);
//        }
//    }

    public void start() {
//        ui.showStartScreen();
//        boolean running = true;
//        Scanner sc = new Scanner(System.in);
//        while (running) {
//            ui.askForCommand();
//            String s = sc.nextLine();
//            Command cmd = CommandHandler.parseCommand(s);
//            cmd.setUtility(taskManager, ui, sc);
//            try {
//                boolean result = cmd.execute();
//                running = result;
//            } catch (DukeException e) {
//                System.out.println(e);
//            }
//        }
//        ui.showExitScreen();
//        sc.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
