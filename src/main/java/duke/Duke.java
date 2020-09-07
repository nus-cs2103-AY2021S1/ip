package duke;

import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to initialise Moco with specific filepath
     * If expected error is met, MocoException is thrown and
     * user is prompted.
     *
     * @param /filepath file path of saved tasklist.
     */
    public Duke(String filePath) throws MocoException {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui(tasks);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String parsed;
        try {
            parsed = Parser.parse(input, tasks, ui, storage);
        } catch (MocoException e) {
            parsed = e.getMessage();
        }
        return parsed;
    }

    /**
     * Starts Moco
     * If the user inputs "bye", Moco closes, saving task list
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            try {
                String input = sc.nextLine();
                command = Parser.parse(input, tasks, ui, storage);
            } catch (MocoException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws MocoException {
        new Duke("tasklist.txt").run();
    }
}