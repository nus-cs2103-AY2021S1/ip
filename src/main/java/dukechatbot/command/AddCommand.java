package dukechatbot.command;

import java.util.Objects;

import dukechatbot.enums.TaskEnum;
import dukechatbot.executor.AddCommandExecutor;
import dukechatbot.parser.CommandParser;

/**
 * Represents the command for add.
 * Stores the tasktype of the task to be added.
 * Stores the argument that contains the task to be added.
 */
public class AddCommand extends Command {
    private final TaskEnum taskType;

    private final String argument;

    public AddCommand(String input, TaskEnum taskType) throws IndexOutOfBoundsException {
        super(input, new AddCommandExecutor());
        this.taskType = taskType;
        assert(!Objects.isNull(this.taskType));
        this.argument = CommandParser.getTitle(input);
    }

    /**
     * Returns the TaskEnum taskType attribute of this object.
     *
     * @return taskType attribute.
     */
    public TaskEnum getTaskType() {
        return taskType;
    }

    /**
     * Returns the String argument attribute of this object.
     *
     * @return argument attribute.
     */
    public String getArgument() {
        return argument;
    }
}
