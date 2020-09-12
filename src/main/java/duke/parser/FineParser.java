package duke.parser;

import duke.commands.Command;
import duke.commands.FindCommand;
import duke.exceptions.IncompleteMessageException;

public class FineParser extends Parser {
    public static Command parseFindCommand(String[] parseArray) throws IncompleteMessageException {
        if (isOneWordCommand(parseArray)) {
            throw new IncompleteMessageException("Please specify keyword. (´∀`)");
        } else {
            String keyword = parseArray[1];
            return new FindCommand(keyword);
        }
    }
}
