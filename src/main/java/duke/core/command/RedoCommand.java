package duke.core.command;

import java.util.logging.Logger;

import duke.designpattern.command.CommandException;
import duke.designpattern.command.Executable;
import duke.designpattern.command.UndoRedoList;

/**
 * Redo the last command in the history (UndoRedoList)
 */
public class RedoCommand implements Executable {

    private static final Logger logger = Logger.getLogger(RedoCommand.class.getName());

    private final UndoRedoList history;

    /**
     * Construct this UndoCommand to redo items in the
     * given history (UndoRedoList)
     * @param history The UndoRedoList containing items to be redone
     */
    public RedoCommand(UndoRedoList history) {
        this.history = history;
        assert this.history != null;
    }

    /**
     * Redo the latest item in the history (UndoRedoList)
     */
    @Override
    public void execute() {
        boolean success = this.history.redo();
        if (!success) {
            logger.info(RedoCommand.class.getSimpleName() + ": Already at latest change");
            throw new CommandException("Redo: Already at latest change");
        }
    }
}
