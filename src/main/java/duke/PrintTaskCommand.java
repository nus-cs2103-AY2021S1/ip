package duke;

public abstract class PrintTaskCommand extends Command {
    public PrintTaskCommand(int commandType, TaskList tasklist) {
        super(commandType, tasklist);
    }

    public PrintTaskCommand(int commandType, TaskList taskList, String userInput) {
        super(commandType, taskList, userInput);
    }

    protected String outputTasksInTaskList(TaskList tasklist, boolean isFind) {
        String output = String.format("Here are the %stasks in your list:", isFind ? "matching " : "");
        for (int i = 0; i < tasklist.size(); i++) {
            Task currentTask = tasklist.get(i);
            String num = Integer.toString(i + 1);
            output += "\n" + num + "." + currentTask;
        }
        return output;
    }
}
