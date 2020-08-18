package commands;

import exceptions.InvalidCommandException;
import parser.TaskParser;
import service.DukeResponse;
import service.DukeService;
import service.Task;
import utils.TokenUtils;

public class AddCommand extends Command {
    public static final String commandWord = "add";
    private static TaskParser taskParser = null;
    private Task toAdd;

    public static void setTaskParser(TaskParser parser) {
        AddCommand.taskParser = parser;
    }

    public AddCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) throws Exception {
        if (!super.isParse) {
            throw new Exception("Command has not been parsed");
        }
        return service.addTask(this.toAdd);
    }

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
