package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.tasks.Task;


/**
 * The command class is used to handle and execute commands
 * after input by user is processed by the parser.
 */
public class Command {
    private String task;
    private String action;

    /**
     * Initializes a Command object
     *
     * @param task The input task given by the user
     * @param action The type of action take as given by the task.
     */
    public Command(String task, String action) {
        assert task != null && action != null : "task and action cannot be null!";
        this.task = task;
        this.action = action;
    }
    /**
     * Executes the action on task given.
     *
     * @param taskList the current task list in use.
     * @param ui the user interface object.
     */
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        assert taskList != null : "taskList cannot be null";
        assert ui != null : "ui should not be null";
        System.out.println(taskList);
        System.out.println("task refer to: " + task);
        switch (action) {
        case "help":
            return ui.showWelcomeMessage();
        case "bye":
            return ui.showEnd();
        case "list":
            System.out.println("in list debug");
            System.out.println(taskList.toString());
            return ui.showList(taskList.getList());
        case "done":
            String[] split = task.split("done ");
            try {
                Integer taskNumber = Integer.parseInt(split[1]);
                return taskList.checkOff(taskNumber);
            } catch (NumberFormatException err) {
                return "Please input a valid number";
            }
        case "delete":
            return taskList.delete(task);
        case "todo":
            return taskList.addToDo(task) + ui.showAdded();
        case "deadline":
            return taskList.addDeadline(task) + ui.showAdded();
        case "event":
            return taskList.addEvent(task) + ui.showAdded();
        case "find":
            String[] splits = task.split("find ");
            if (splits.length < 2) {
                return ui.showInvalidCommand();
            }
            String toFind = task.split("find ")[1];
            ArrayList<Task> list = taskList.getList();
            ArrayList<Task> filtered = new ArrayList<>();
            return getSize(toFind, ui, list, filtered);
        default:
            return ui.showInvalidCommand();
        }
    }

    private String getSize(String toFind, Ui ui, ArrayList<Task> list, ArrayList<Task> filtered) {
        if (list.size() == 0) {
            return "You do not have any tasks yet";
        } else {
            String toReturn = "Here are the tasks that matches '" + toFind + "'";
            for (int i = 0; i < list.size(); i++) {
                String task = list.get(i).toString();
                if (task.contains(toFind)) {
                    filtered.add(list.get(i));
                }
            }
            toReturn += ui.showList(filtered);
            return toReturn;
        }
    }
}
