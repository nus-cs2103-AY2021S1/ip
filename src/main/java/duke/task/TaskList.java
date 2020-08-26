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
     * @param sc Scanner that listens to user input.
     * @param arr Array of words in user input.
     */
    public void readCommands(Scanner sc, String[] arr) {
        switch (arr[0]) {
        case "bye":
            sc.close();
            new Exit(tasks).closeDuke(storage);
            break;
        case "list":
            new ShowTasks(tasks).showTasks();
            break;
        case "done":
            ManageTask doneTaskCommand = new ManageTask(tasks);
            doneTaskCommand.manageTask(ManageTask.Action.DONE, arr[1]);
            break;
        case "delete":
            ManageTask deleteTaskCommand = new ManageTask(tasks);
            deleteTaskCommand.manageTask(ManageTask.Action.DELETE, arr[1]);
            break;
        case "help":
            new Help(tasks).getCommands();
            break;
        case "deadline":
            AddTask addDeadlineCommand = new AddTask(tasks);
            addDeadlineCommand.addTask(AddTask.Type.DEADLINE, arr);
            break;
        case "event":
            AddTask addEventCommand = new AddTask(tasks);
            addEventCommand.addTask(AddTask.Type.EVENT, arr);
            break;
        case "todo":
            AddTask addTodoCommand = new AddTask(tasks);
            addTodoCommand.addTask(AddTask.Type.TODO, arr);
            break;
        case "find":
            new FindTask(tasks).findTask(arr);
            break;
        case "date":
            new FindTaskByDate(tasks).findTaskByDate(arr);
            break;
        default:
            DukeException e = new DukeException("I do not understand your command");
            layout.print(e.getMessage());

        }
    }

}
