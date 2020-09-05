package duke.core.command;

import duke.designpattern.command.Executable;
import duke.designpattern.command.UndoRedoList;

/**
 * Redo the last command in the history (UndoRedoList)
 */
public class RedoCommand implements Executable {

    private final UndoRedoList history;

    /**
     * Construct this UndoCommand to redo items in the
     * given history (UndoRedoList)
     * @param history The UndoRedoList containing items to be redone
     */
    public RedoCommand(UndoRedoList history) {
        this.history = history;
    }

    /**
     * Redo the latest item in the history (UndoRedoList)
     */
    @Override
    public void execute() {
        boolean success = this.history.redo();
        if (!success) {
            System.out.println("Redo: Already at latest change");
        }
    }
}
