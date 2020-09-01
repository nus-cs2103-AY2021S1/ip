import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.DukeException;

/**
 * Responsible for interpreting the input and interacting with the User.
 */
public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;
    private final Parser parser;

    /**
     * Initialised Duke with a designated location to read and save the data.
     * @param filePath File location to read and save data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Start the program.
     */
    public String processInput(String input) {
        int index;
        try {
            String[] words = parser.splitIntoComponents(input);
            String command = words[0];
            switch (command) {
            //Common functions
            case "bye":
                storage.saveFile(taskList.toSaveFormat());
                return ui.showBye();
            case "done":
                index = Integer.parseInt(words[1]);
                taskList.doTask(index);
                return ui.showTaskDone(taskList.getTaskStatus(index));
            case "list":
                if (taskList.getTotalTask() == 0) {
                    return ui.show("Currently, you have no tasks on hand");
                } else {
                    return ui.showTasks(taskList.toString());
                }

            //3 different types of task
            case "event":
                try {
                    Task addedEvent = taskList.addEvent(words[1], words[2]);
                    return ui.showTaskAdded(addedEvent.toString(), taskList.getTotalTask());
                } catch (IndexOutOfBoundsException err) {
                    return ui.showError("Error: Please key in as: \n" +
                            "event [title] /at YYYY-MM-DD [startTime] [endTime] where start and end time is in HH:MM ");
                }
            case "todo":
                try {
                    Task addedToDo = taskList.addTodo(words[1]);
                    return ui.showTaskAdded(addedToDo.toString(), taskList.getTotalTask());
                } catch (IndexOutOfBoundsException err) {
                    return ui.showError("Error: Please key in as: \n " +
                            "event [title]");
                }
            case "deadline":
                try {
                    Task addedDeadline = taskList.addDeadLine(words[1], words[2]);
                    return ui.showTaskAdded(addedDeadline.toString(), taskList.getTotalTask());
                } catch (IndexOutOfBoundsException err) {
                    return ui.showError("Error: Please key in as: \n " +
                            "event [title] /by YYYY-MM-DD HH:MM");
                }

            //Delete Task
            case "delete":
                try {
                    index = Integer.parseInt(words[1]);
                    Task deletedTask = taskList.deleteTask(index);
                    return ui.showDeletedTasks(deletedTask.toString());
                } catch (NumberFormatException err) {
                    //echo("Error. Please key in an integer after \"done\"");
                } catch (IndexOutOfBoundsException err) {
                    return ui.showError("Key in \"delete [x]\" to delete x^th item");
                }

            //Find task by keyword
            case "find":
                String result = taskList.find(words[1]);
                if (result == ""){
                    return ui.show("No match found");
                } else {
                    return ui.show("These following tasks match the keyword you entered: \n" + result);
                }

            //When command does not match any of those above
            default:
                return ui.showError("OOPS!!! I don't know what does it mean by: \"" + input + "\"");
            }
        } catch (DukeException err) {
            return ui.showError(err.getMessage());
        }
    }
}