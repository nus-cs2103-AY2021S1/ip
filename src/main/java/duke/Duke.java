package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Driver class for chat bot.
 */
public class Duke {

    private final Scanner sc;
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Class constructor.
     */
    public Duke() {
        sc = new Scanner(System.in);
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
        try {
            storage.loadTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void start() {
        try {
            storage.loadTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(storage, tasks, ui);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * Driver method for chat bot.
     * @param args Starts up the chat bot.
     */
    public static void main(String[] args) {
        new Duke().start();
    }
}
