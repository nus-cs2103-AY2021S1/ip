package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents the overarching Duke chat-bot.
 * Duke is a chat application which helps to keep track of tasks in an interactive manner.
 * @author David Liew
 */

public class Duke {

    private TaskList list;
    private Storage storage;
    private Ui ui;

    /**
     * The constructor for the class Duke.
     */
    public Duke() {
        ui = new Ui();
        ui.intro();
        storage = new Storage();
        try {
            list = new TaskList(storage.readFile());
        } catch (IOException e) {
            System.out.println("You have no save tasks");
        }
    }

    /**
     * The main method that will instantiate a Duke object.
     * @param args the arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }

    /**
     * Starts the Duke chat-bot, by taking in user input and sending it to be parsed.
     */
    public void run() {
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext() && !isExit) {
            String input = sc.nextLine();
            Command c = Parser.parse(input);
            c.execute(list, ui, storage);
            isExit = c.isExit;
            if (isExit) {
                break;
            }
        }
    }


}
