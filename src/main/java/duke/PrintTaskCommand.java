package duke;

/**
 * PrintTaskCommand is a abstract class containing a common method used by Commands that print tasks.
 */
public abstract class PrintTaskCommand extends Command {
    public PrintTaskCommand(int commandType, TaskList tasklist) {
        super(commandType, tasklist);
    }

    public PrintTaskCommand(int commandType, TaskList taskList, String userInput) {
        super(commandType, taskList, userInput);
    }

    /**
     * Prints all the tasks in the TaskList provided.
     *
     * @param taskList TaskList containing task to print.
     * @param isFind   True if this method is printing tasks related to a keyword and false otherwise.
     * @return All the tasks in TaskList in String.
     */
    public String outputTasksInTaskList(TaskList taskList, boolean isFind) {
        if (taskList.size() == 0) {
            return Ui.informNoTaskInList();
        }
        String output = String.format("Here are the %stasks in your list:", isFind ? "matching " : "");
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String num = Integer.toString(i + 1);
            output += "\n" + num + "." + currentTask;
        }
        return output;
    }
}
