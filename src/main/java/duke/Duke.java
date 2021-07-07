package duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.exception.DukeDateException;
import duke.task.TaskList;
import javafx.application.Platform;


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
    public Duke() throws IOException {
        ui = new Ui();
        Ui.intro();
        storage = new Storage();
        try {
            list = new TaskList(Storage.readFile());
        } catch (IOException | DukeDateException e) {
            System.out.println("You have no saved tasks");
        }
    }

    /**
     * Function to generate a response to user input.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(list, ui, storage);
        String response = c.executeChat(list, ui, storage);
        if(input.equals("bye")) {
            exit();
        }
        return response;
    }

    public static void exit() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 2000);
    }


    /**
     * The main method that will instantiate a Duke object.
     * @param args the arguments
     */
    public static void main(String[] args) throws IOException {
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
