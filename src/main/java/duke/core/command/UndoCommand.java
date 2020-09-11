package duke.core.command;

import java.util.logging.Logger;

import duke.designpattern.command.CommandException;
import duke.designpattern.command.Executable;
import duke.designpattern.command.UndoRedoList;

/**
 * Undo the last command in the history (UndoRedoList)
 */
public class UndoCommand implements Executable {

    private static final Logger logger = Logger.getLogger(UndoCommand.class.getName());

    private final UndoRedoList history;

    /**
     * Construct this UndoCommand to undo items in the
     * given history (UndoRedoList)
     * @param history The UndoRedoList containing items to be undone
     */
    public UndoCommand(UndoRedoList history) {
        this.history = history;
        assert this.history != null;
    }

    /**
     * Undo the latest item in the history (UndoRedoList)
     */
    @Override
    public void execute() {
        boolean success = this.history.undo();
        if (!success) {
            logger.info(UndoCommand.class.getSimpleName() + ": Already at earliest change");
            throw new CommandException("Undo: Already at earliest change");
        }
    }
}
