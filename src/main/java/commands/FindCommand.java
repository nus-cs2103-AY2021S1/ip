package commands;

import java.util.function.Predicate;

import exceptions.InvalidCommandException;
import exceptions.ServiceException;
import service.DukeResponse;
import service.DukeService;
import service.Task;
import utils.TokenUtils;


public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private Predicate<Task> predicate;

    public FindCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) throws ServiceException {
        if (!super.isParsed) {
            throw new ServiceException("This command has not been parsed");
        }
        return service.findTasks(this.predicate);
    }

    @Override
    public void parse() throws InvalidCommandException {
        String[] tokens = super.raw.split(" ");

        if (tokens.length <= 1) {
            throw new InvalidCommandException("Find command need more arguments!");
        }

        String[] finalTokens = TokenUtils.dropFirst(tokens);
        assert finalTokens.length >= 1;
        this.predicate = S -> TokenUtils.isSubsequence(TokenUtils.tokensToString(finalTokens), S.getDescription());
        super.isParsed = true;
    }
}
