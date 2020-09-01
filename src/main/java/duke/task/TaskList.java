package duke.task;

import duke.command.AddTask;
import duke.command.Exit;
import duke.command.FindTask;
import duke.command.FindTaskByDate;
import duke.command.Help;
import duke.command.ManageTask;
import duke.command.ShowTasks;
import duke.io.Layout;
import duke.io.Storage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a current task list manager.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;
    private final Layout layout;
    
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.layout = new Layout();
    }
    
    /**
     * Read commands by user and execute associated action.
     *
     * @param arr Array of words in user input.
     */
    public String readCommands(String[] arr) {
        try {
            switch (arr[0]) {
            case "bye":
                return new Exit(tasks).closeDuke(storage);
            case "list":
                return new ShowTasks(tasks).showTasks();
            case "done":
                ManageTask doneTaskCommand = new ManageTask(tasks);
                return doneTaskCommand.manageTask(ManageTask.Action.DONE, arr[1]);
            case "delete":
                ManageTask deleteTaskCommand = new ManageTask(tasks);
                return deleteTaskCommand.manageTask(ManageTask.Action.DELETE, arr[1]);
            case "help":
                return new Help(tasks).getCommands();
            case "deadline":
                AddTask addDeadlineCommand = new AddTask(tasks);
                return addDeadlineCommand.addTask(AddTask.Type.DEADLINE, arr);
            case "event":
                AddTask addEventCommand = new AddTask(tasks);
                return addEventCommand.addTask(AddTask.Type.EVENT, arr);
            case "todo":
                AddTask addTodoCommand = new AddTask(tasks);
                return addTodoCommand.addTask(AddTask.Type.TODO, arr);
            case "find":
                return new FindTask(tasks).findTask(arr);
            case "date":
                return new FindTaskByDate(tasks).findTaskByDate(arr);
            default:
                DukeException e = new DukeException("I do not understand your command");
                return layout.print(e.getMessage());

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException d = new DukeException("Please specify task number");
            return layout.print(d.getMessage());
        }
    }

}
