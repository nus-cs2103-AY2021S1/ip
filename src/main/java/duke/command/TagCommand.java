package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.parser.TagParser;

public class TagCommand implements Command {
    private TaskList lines;
    private TagParser tagParser;

    /**
     * The constructor for the TagCommand object.
     * @param lines the TaskList to be manipulated.
     * @param tagParser the TagParser that parses the tag command.
     */
    public TagCommand(TaskList lines, TagParser tagParser) {
        this.lines = lines;
        this.tagParser = tagParser;
    }

    /**
     * Executes the tag command and returns the output string for the user.
     * @return the output message for the user.
     */
    public String execute() {
        String message;
        try {
            int itemNumber = tagParser.checkIfValid();
            String description = tagParser.getDescription(itemNumber);
            boolean tagged = lines.tagItem(itemNumber - 1, description);
            message = Ui.taggedTask(lines.getTask(itemNumber - 1), tagged);
        } catch (DukeException e) {
            message = Ui.handleDukeException(e);
        }
        return message;
    }
}
