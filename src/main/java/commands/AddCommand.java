package commands;

import exceptions.InvalidCommandException;
import parser.TaskParser;
import service.DukeResponse;
import service.DukeService;
import service.Task;
import utils.TokenUtils;

/**
 * This class represents AddCommand, which adds task into the system.
 * Syntax: add + task_type + decs + tags...
 */
public class AddCommand extends Command {
    public static final String commandWord = "add";
    private static TaskParser taskParser = null;
    private Task toAdd;

    /**
     * Constructor
     * @param parser: a task parser that has registered all tasks.
     */
    public static void setTaskParser(TaskParser parser) {
        AddCommand.taskParser = parser;
    }

    /**
     * Constructor.
     * @param raw: raw command input by users
     */
    public AddCommand(String raw) {
        super(raw);
    }

    /**
     * Overriden method, to execute the command given the service
     * @param service: duke service
     * @return a duke response
     * @throws Exception if execution fails
     */
    @Override
    public DukeResponse execute(DukeService service) throws Exception {
        if (!super.isParse) {
            throw new Exception("Command has not been parsed");
        }
        return service.addTask(this.toAdd);
    }

    /**
     * Overriden method, to parse the command.
     * @throws InvalidCommandException when syntax is wrong, and report to users.
     */
    @Override
    public void parse() throws InvalidCommandException {
        if (taskParser == null) {
            throw new InvalidCommandException("Haven't set task parser");
        }
        String[] tokens = super.raw.split(" ");

        if (tokens.length <= 1) {
            throw new InvalidCommandException("Not enough arguments");
        }

        String[] taskTokens = TokenUtils.dropFirst(tokens);
        this.toAdd = taskParser.parse(taskTokens);
        this.toAdd.parse();
        super.isParse = true;
    }

}
