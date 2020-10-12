package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.parser.FindParser;

public class FindCommand implements Command {
    private TaskList lines;
    private FindParser findParser;

    /**
     * The constructor for the FindCommand object.
     * @param lines The TaskList to be manipulated
     * @param findParser The FindParser that parses the find command
     */
    public FindCommand(TaskList lines, FindParser findParser) {
        this.lines = lines;
        this.findParser = findParser;
    }

    /**
     * Executes the find command and returns the string representing the output message.
     * @return the output message
     */
    public String execute() {
        String message;
        try {
            String keyword = findParser.keywordIfValid();
            ArrayList<String> matchingTasks = lines.find(keyword);
            message = Ui.listMatchingTasks(matchingTasks);
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}
