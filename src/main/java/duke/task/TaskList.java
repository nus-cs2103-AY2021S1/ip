package duke.task;

import duke.command.AddTask;
import duke.command.Command;
import duke.command.Exit;
import duke.command.FindTask;
import duke.command.FindTaskByDate;
import duke.command.Help;
import duke.command.ManageTask;
import duke.command.ShowTasks;
import duke.io.Storage;

import java.util.ArrayList;

/**
 * Represents a current task list manager.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;
    
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }
    
    /**
     * Read commands by user and return associated command.
     *
     * @param arr Array of words in user input.
     */
    public Command getCommands(String[] arr) throws DukeException {
        try {
            switch (arr[0]) {
            case "bye":
                return new Exit(storage);
            case "list":
                return new ShowTasks();
            case "done":
                return new ManageTask(ManageTask.Action.DONE, arr[1]);
            case "delete":
                return new ManageTask(ManageTask.Action.DELETE, arr[1]);
            case "help":
                return new Help();
            case "deadline":
                return new AddTask(AddTask.Type.DEADLINE, arr);
            case "event":
                return new AddTask(AddTask.Type.EVENT, arr);
            case "todo":
                return new AddTask(AddTask.Type.TODO, arr);
            case "find":
                return new FindTask(arr);
            case "date":
                return new FindTaskByDate(arr);
            default:
                DukeException e = new DukeException("I do not understand your command");
                throw e;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException d = new DukeException("Please specify task number");
            throw d;
        }
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }

}
