package command;

import converter.Parser;
import duke.Duke;
import exception.DukeException;
import java.io.IOException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class CommandHandler {

    public static String execute(String input, Duke duke) {
        TaskList taskList = duke.getTaskList();
        Storage storage = duke.getStorage();
        StringBuilder output = new StringBuilder();
        try {
            Mode mode = Parser.mode(input);
            if (mode == Mode.TODO || mode == Mode.DEADLINE ||
                    mode == Mode.EVENT) {
                // Create and add task to task list
                Task task = Parser.task(input);
                taskList.add(task);

                // Write data
                storage.addData(task);

                // System output
                output.append(Ui.showAddTask(task) + "\n")
                        .append(Ui.showTotalTasks(taskList.getSize()));

            } else if (mode == Mode.DONE) {
                // Mark Task as Done
                int order = Parser.order(input);
                Task task = taskList.markDone(order);
                storage.markDoneData(order);
                output.append(Ui.showDoneTask(task));

            } else if (mode == Mode.DELETE) {
                // Delete Task
                int order = Parser.order(input);
                Task task = taskList.remove(order);
                storage.deleteData(order);
                output.append(Ui.showRemovedTask(task)).append("\n")
                        .append(Ui.showTotalTasks(taskList.getSize()));

            } else if (mode == Mode.LIST) {
                // List all tasks
                output.append(taskList.listToString());

            } else if (mode == Mode.BYE) {
                // Exit now
                duke.setExit(true);
                output.append(Ui.goodbye());
            } else if (mode == Mode.FIND) {
                // Find tasks
                String subName = Parser.name(input);
                String result = taskList.find(subName);
                output.append(Ui.showFindTask(result));
            }
        } catch (DukeException | IOException e) {

            output.append(Ui.showError(e));
        }
        return output.toString();
    }
}