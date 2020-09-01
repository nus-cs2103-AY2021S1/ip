package command;

import enums.TaskEnum;
import executor.AddCommandExecutor;
import parser.CommandParser;

public class AddCommand extends Command {
    private final TaskEnum taskType;

    private final String argument;

    public AddCommand(String input, TaskEnum taskType) {
        super(input, new AddCommandExecutor());
        this.taskType = taskType;
        this.argument = CommandParser.getTitle(input);
    }

    public TaskEnum getTaskType() {
        return taskType;
    }

    public String getArgument() {
        return argument;
    }
}
