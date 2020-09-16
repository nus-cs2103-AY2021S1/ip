
package duke;

import java.util.Arrays;

import duke.task.Task;
import graphic_interface.DialogBox;
/**
 * Responsible for interpreting the input and interacting with the User.
 */
public class Duke {
    private final Ui ui;
    private TaskList tasks;
    private Storage storage;
    private final Parser parser;

    /**
     * Initialised duke.Duke with a designated location to read and save the data.
     * @param filePath File location to read and save data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage(filePath);
        } catch (DukeException e) {
            this.storage = new Storage();
        }

        try {
            this.tasks = new TaskList(this.storage.loadDefaultFile());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

    private String createEvent(String[] commandParts) throws DukeException {
        if (commandParts.length != 3) {
            String errorMessage = "Error: Please key in as: \n"
                    + "event [title] /at YYYY-MM-DD [startTime] [endTime]"
                    + "where start and end time is in HH:MM ";
            return ui.showError(errorMessage);
        }
        Task addedEvent = tasks.addEvent(commandParts[1], commandParts[2]);
        return ui.showTaskAdded(addedEvent.toString(), tasks.getTotalTask());
    }

    private String createDeadline(String[] commandParts) throws DukeException {
        if (commandParts.length != 3) {
            String errorMessage = ui.showError("Error: Please key in as: \n "
                    + "event [title] /by YYYY-MM-DD HH:MM");
            return ui.showError(errorMessage);
        }
        Task addedDeadline = tasks.addDeadLine(commandParts[1], commandParts[2]);
        return ui.showTaskAdded(addedDeadline.toString(), tasks.getTotalTask());
    }

    private String createTodo(String[] commandParts) {
        if (commandParts.length != 2) {
            String errorMessage = "Error: Please key in as: \n "
                    + "event [title]";
            return ui.showError(errorMessage);
        }
        Task addedToDo = tasks.addTodo(commandParts[1]);
        return ui.showTaskAdded(addedToDo.toString(), tasks.getTotalTask());
    }

    private String deleteTask(String[] commandParts) throws DukeException {
        try {
            int index = Integer.parseInt(commandParts[1]);
            Task deletedTask = tasks.deleteTask(index);
            return ui.showDeletedTasks(deletedTask.toString());
        } catch (NumberFormatException err) {
            throw new DukeException("Error. Please key in an integer after \"done\"");
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Key in \"delete [x]\" to delete x^th item");
        }
    }

    private void loadFile(String filePath) throws DukeException {
        this.tasks = new TaskList(storage.loadCustomFile(filePath));
    }

    /**
     ** Take in user input and carry out the respective command based on the input command.
     * @param input The user input.
     * @return Either Success Message or Error Message due to bad formatting.
     */
    public String processInput(String input, Runnable terminationFunction) throws DukeException {
        int index;
        String response = "";
        String[] inputComponents = parser.splitIntoComponents(input);
        String command = inputComponents[0];
        switch (command) {
        //Common functions
        case "bye":
            storage.saveToFile(tasks.toSaveFormat());
            response = ui.showBye();
            terminationFunction.run();
            break;
        case "done":
            index = Integer.parseInt(inputComponents[1]);
            tasks.doTask(index);
            response = ui.showTaskDone(tasks.getTaskStatus(index));
            break;
        case "list":
            if (tasks.getTotalTask() == 0) {
                response = ui.show("Currently, you have no tasks on hand");
            } else {
                response = ui.showTasks(tasks.toString());
            }
            break;
        //Dealing with memory
        case "archive":
            String[] filePaths = Arrays.copyOfRange(inputComponents, 1, inputComponents.length);
            this.storage.saveToFile(tasks.toSaveFormat(), filePaths);
            response = ui.show("You have successfully archive current progress to the file(s) indicated");
            break;
        case "load":
            if (inputComponents.length != 2) {
                response = ui.show("Invalid Input. 'Load' should be followed by the filePath");
                break;
            }
            this.loadFile(inputComponents[1]);
            response = ui.show("Successful");
            break;
        case "save":
            storage.saveToFile(tasks.toSaveFormat());
            response = ui.show("Current Progress is saved. You can proceed with next command as per normal");
            break;

        //3 different types of task
        case "event":
            response = this.createEvent(inputComponents);
            break;
        case "todo":
            response = this.createTodo(inputComponents);
            break;
        case "deadline":
            response = this.createDeadline(inputComponents);
            break;

        //Delete Task
        case "delete":
            response = this.deleteTask(inputComponents);
            break;

        //Find task by keyword
        case "find":
            String result = tasks.find(inputComponents[1]);
            if (result.equals("")) {
                response = ui.show("No match found");
            } else {
                response = ui.show("These following tasks match the keyword you entered: \n" + result);
            }
            break;

        //When command does not match any of those above
        default:
            throw new DukeException(ui.showError("OOPS!!! I don't know what does it mean by: \"" + input + "\""));
        }
        assert !response.equals("") : "Error, none of the case catch the command";
        return response;
    }
}