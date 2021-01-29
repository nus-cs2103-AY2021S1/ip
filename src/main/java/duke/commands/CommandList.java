package duke.commands;

import duke.tasks.TaskList;

public class CommandList extends Command {

    public static final String COMMAND_STRING = "list";

    public CommandList(TaskList taskList) {
        super(taskList);
    }

    @Override
    public String execute() {
        String output = "";
        output += "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        int remainingTasks = taskList.getRemainingTaskCount();
        output += String.format("You have %d undone tasks! %s\n",
                remainingTasks,
                remainingTasks > 0 ? "Buck up!!" : "Well done I guess.");
        return output;
    }
}
