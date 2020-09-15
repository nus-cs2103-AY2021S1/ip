package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tag.Tag;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.IndexDescriptionPair;

/**
 * TagCommand add a tag to the task specified in the tasklist
 */
public class TagCommand extends Command {
    public TagCommand(String commandString) {
        super(CommandType.TAG, commandString);
    }


    public IndexDescriptionPair getIndexDescriptionPair() throws DukeException {
        return Parser.getTaskTargetIndexDescription(super.getCommandType(), super.getCommandString());
    }

    /**
     * Adds a tag to the task specified in the tasklist
     *
     * @param tasks a list of tasks
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        IndexDescriptionPair indexDescriptionPair = getIndexDescriptionPair();
        Task task = tasks.get(indexDescriptionPair.getIndex());
        Tag tag = new Tag(indexDescriptionPair.getDescription());
        task.addTag(tag);
        Ui.showTagged(task);
    }
}
