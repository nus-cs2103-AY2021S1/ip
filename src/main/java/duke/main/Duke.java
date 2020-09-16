package duke.main;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;


/**
 * Represents the Duke object to start the program.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke object with an Ui object, storage object to the saved task list file in the
     * hard disk and a task list object being created after the saved task list file is saved.
     * If the directory to the saved task list file is not found, an IOException error will be raised
     * and caught.
     *
     * @param fileName Name of the saved task list file.
     */
    public Duke(String fileName) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(fileName);
            this.tasks = storage.formTaskList();
        } catch (IOException error) {
            System.out.println(error.toString());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        String greetingMessage = ui.getGreetingMessage();
        ui.printToScreen(greetingMessage);
        while (true) {
            try {
                Processor.process(tasks, storage, ui);
                break;
            } catch (DukeException dukeException) {
                ui.printError(dukeException);
            }
        }
    }

    /**
     * Gets the Duke's response with with respect to user's input.
     *
     * @param userInput The input from the user.
     * @return Duke's response to be displayed through the GUI.
     */
    public String getResponse(String userInput) {
        StringBuilder response = new StringBuilder();
        try {
            String command = Parser.getCommand(userInput);
            storage.formTaskList();
            if (command.equals("bye")) {
                Processor.handleByeCommand(response, ui);
            } else if (command.equals("list")) {
                Processor.handleListCommand(response, ui, tasks);
            } else if (command.equals("done")) {
                Processor.handleDoneCommand(userInput, response, ui, tasks);
            } else if (command.equals("delete")) {
                Processor.handleDeleteCommand(userInput, response, ui, tasks);
            } else if (command.equals("todo")) {
                Processor.handleTodoCommand(userInput, response, ui, tasks);
            } else if (command.equals("deadline")) {
                Processor.handleDeadlineCommand(userInput, response, ui, tasks);
            } else if (command.equals("event")) {
                Processor.handleEventCommand(userInput, response, ui, tasks);
            } else if (command.equals("find")) {
                Processor.handleFindCommand(userInput, response, ui, tasks);
            } else if (command.equals("sort")) {
                Processor.handleSortCommand(userInput, response, ui, tasks);
            } else {
                throw new InvalidCommandException();
            }
            storage.writeTasks(tasks);
        } catch (DukeException error) {
            String errorMessage = error.toString();
            response.append(errorMessage);
        } catch (IOException err) {
            String errorMessage = err.getMessage();
            response.append(errorMessage);
        } finally {
            return response.toString();
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
