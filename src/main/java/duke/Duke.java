package duke;

import duke.exception.DukeException;
import duke.exception.MissingDescriptionException;
import duke.exception.UnknownCommandException;
import duke.tool.Parser;
import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Duke chat bot that helps the user to keep track of their tasks, such as todos,
 * events and deadlines.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.showLoadError();
        }
    }

    public String getResponse(String userInput) {
        String[] parsedInput = Parser.parse(userInput);
        try {
            switch (parsedInput[0]) {
            case "hello":
                return ui.greet();
            case "list":
                return ui.list(tasks.formattedList());
            case "bye":
                storage.saveFile(tasks);
                return ui.exit();
            case "done":
                return ui.completeTask(tasks.completeTask(Integer.valueOf(parsedInput[1])));
            case "delete":
                return ui.deleteTask(tasks.deleteTask(Integer.valueOf(parsedInput[1])), tasks.getLength());
            case "find":
                return ui.find(tasks.findTasks(parsedInput[1]));
            case "todo":
                // Fallthrough
            case "event":
                // Fallthrough
            case "deadline":
                if (parsedInput.length == 1) {
                    throw new MissingDescriptionException(parsedInput[0]);
                } else {
                    return ui.addTask(tasks.addTask(parsedInput[0], parsedInput[1]), tasks.getLength());
                }
            default:
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            return ui.showDukeError(e);
        } catch (IOException e) {
            return ui.showSaveError();
        }
    }
    public String greet() {
        return ui.greet();
    }
    /**
     * Runs the chat bot by accepting user inputs and handling it through
     * other classes like TaskList and Ui.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            getResponse(sc.nextLine());
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

