package command;

import java.io.IOException;

import converter.Parser;
import duke.Duke;
import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class CommandHandler {

    /**
     * Give instructions to Bot based on the command given and return UI response. Contains the logic of the bot.
     *
     * @param input Raw user input in string.
     * @param duke  Duke object.
     * @return UI response as a string based on the command given.
     */
    public static String execute(String input, Duke duke) {
        TaskList taskList = duke.getTaskList();
        Storage storage = duke.getStorage();
        StringBuilder output = new StringBuilder();
        try {
            Mode mode = Parser.mode(input);
            if (mode == Mode.TODO || mode == Mode.DEADLINE
                    || mode == Mode.EVENT) {
                // Create and add task to task list
                Task task = Parser.task(input);
                taskList.add(task);

                // Write data
                storage.addData(task);

                // System output
                output.append(Ui.showAddTask(task, taskList.getSize()));

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
                output.append(Ui.showRemovedTask(task, taskList.getSize()));

            } else if (mode == Mode.LIST) {
                // List all tasks
                output.append(Ui.showListTask(taskList));

            } else if (mode == Mode.BYE) {
                // Exit now
                duke.setExit(true);
                output.append(Ui.goodbye());
            } else if (mode == Mode.FIND) {
                // Find tasks
                String subName = Parser.findName(input);
                TaskList result = taskList.find(subName);
                output.append(Ui.showFindTask(result));
            } else if (mode == Mode.TAG) {
                //Tag task
                int order = Parser.order(input);
                String tagName = Parser.getTag(input);
                Task task = taskList.setTag(order, tagName);
                storage.setTag(order, tagName);
                output.append(Ui.showTaggedTask(task));

            }
        } catch (DukeException | IOException e) {

            output.append(Ui.showError(e));
        }
        return output.toString();
    }
}
