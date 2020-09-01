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
        if (action.equals("invalid")) {
            return ui.showInvalidCommand();
        } else if (action.equals("bye")) {
            return ui.showEnd();
        } else if (action.equals("list")) {
            return ui.showList(taskList.getList());
        } else if (action.indexOf("done") == 0) {
            String[] split = action.split("done ");
            try {
                Integer taskNumber = Integer.parseInt(split[1]);
                return taskList.checkOff(taskNumber);
            } catch (NumberFormatException err) {
                return "Please input a valid number";
            }
        } else if (action.equals("delete")) {
            return taskList.delete(task);
        } else if (action.equals("todo")) {
            String toReturn = taskList.addToDo(task) + ui.showAdded();
            return toReturn;
        } else if (action.equals("deadline")) {
            String toReturn = taskList.addDeadline(task) + ui.showAdded();
            return toReturn;
        } else if (action.equals("event")) {
            String toReturn = taskList.addEvent(task) + ui.showAdded();
            return toReturn;
        } else if (action.equals("find")) {
            String[] split = task.split("find ");
            String toFind = split[1];
            ArrayList<Task> list = taskList.getList();
            ArrayList<Task> filtered = new ArrayList<>();
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
        } else {
            return ui.showInvalidCommand();
        }
    }
}
