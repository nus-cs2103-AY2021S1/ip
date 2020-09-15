package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a task manager bot named Duke.
 */
public class Duke {

    private final Scanner sc;
    private Storage storage;
    private TaskList tasks;
    private Gui gui;

    public Duke() {
        sc = new Scanner(System.in);
        gui = new Gui();
        try {
            storage = new Storage("data/tasks.txt");
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
            gui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().runBot();
    }

    public void runBot() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    isExit = true;
                    break;
                }
                GuiParser guiParser = new GuiParser();
                guiParser.interpretGui(input, tasks, storage);
            } catch (DukeException | FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public String getResponseGui(String input) {
        try {
            GuiParser guiParser = new GuiParser();
            return guiParser.interpretGui(input, tasks, storage);
        } catch (DukeException | FileNotFoundException e) {
            return e.getMessage();
        }

    }
}
