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
    private final Storage storage;
    private final Ui ui;


    /**
     * The constructor for the class Duke.
     */
    public Duke() {
        ui = new Ui();
        Ui.intro();
        storage = new Storage();
        try {
            list = new TaskList(Storage.readFile());
        } catch (IOException e) {
            System.out.println("You have no save tasks");
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(list, ui, storage);
        String response = c.executeChat(list, ui, storage);
        return response;
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
