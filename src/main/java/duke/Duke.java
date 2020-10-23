package duke;
import duke.commands.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Duke is the main class in this todo app.
 */
public class Duke {
    public TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor of Duke
     *
     * @param filePath path where saved data of todo is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // need empty tasks to load properly
            tasks = new TaskList();
            tasks = new TaskList(storage.load(this));
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Loads tasks from saved text file.
     * Takes user input and execute until program terminates.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                /*
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                 */
                handleUserInput(fullCommand, false);
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * run todo app
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Return response and take action according to the user input.
     *
     * @param userInput input from user, run tasks according to this.
     * @param isLoading indicates whether input is from saved data or user.
     */
    public String handleUserInput(String userInput, boolean isLoading) throws DukeException, IOException {
        String instructionType = Parser.parseInstruction(userInput);
        String response = "";
        assert instructionType != "" : "instructionType cannot be empty string";

        if (instructionType.equals("bye")) {
            // quit
            Command exitCommand = new ExitCommand();
            response = exitCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("list")) {
            // list task
            Command listCommand = new ListCommand();
            response = listCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("done")) {
            // mark done
            Command doneCommand = new DoneCommand();
            response = doneCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("delete")) {
            // delete task
            Command deleteCommand = new DeleteCommand();
            response = deleteCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("find")) {
            // find task
            Command findCommand = new DeleteCommand();
            response = findCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("todo")) {
            // make todo
            Command todoCommand = new TodoCommand();
            todoCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("deadline")) {
            // make deadline
            Command deadlineCommand = new DeadlineCommand();
            deadlineCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("event")) {
            // make event
            Command eventCommand = new EventCommand();
            eventCommand.execute(userInput, ui, tasks, isLoading);

        } else if (instructionType.equals("priority")) {
            // add priority to the task
            Command priorityCommand = new PriorityCommand();
            priorityCommand.execute(userInput, ui, tasks, isLoading);

        } else {
            // invalid input
            if (!isLoading) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        // save Data for every user input
        Storage.save(tasks.taskList);
        return response;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Duke duke = new Duke("data/duke.txt");
        String response = "";
        try {
            response = duke.handleUserInput(input, false);
        } catch (DukeException | IOException e) {
            response = duke.ui.showError(e.getMessage());
        }
        assert response != "" : "response should not be empty";
        return "Duke heard: \n"
                + response;
    }

    public Ui getUi() {
        return this.ui;
    }

}
