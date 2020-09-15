package duke.parser;

import duke.commands.Command;
import duke.commands.FindCommand;
import duke.exceptions.IncompleteMessageException;

public class FindParser extends Parser {
    /**
     * Returns a findCommands from an array of info extracted from user input.
     *
     * @param parseArray An array of info: ["find", keyword]. .
     * @return A fineCommand.
     * @throws IncompleteMessageException If no keyword is provided.
     */
    public static Command parseFindCommand(String[] parseArray) throws IncompleteMessageException {
        if (isOneWordCommand(parseArray)) {
            throw new IncompleteMessageException("Please specify keyword. (´∀`)");
        } else {
            String keyword = parseArray[1];
            return new FindCommand(keyword);
        }
    }
}
