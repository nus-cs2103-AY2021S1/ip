package duke.core.command;

import duke.designpattern.command.Executable;
import duke.designpattern.command.UndoRedoList;

/**
 * Undo the last command in the history (UndoRedoList)
 */
public class UndoCommand implements Executable {

    private final UndoRedoList history;

    /**
     * Construct this UndoCommand to undo items in the
     * given history (UndoRedoList)
     * @param history The UndoRedoList containing items to be undone
     */
    public UndoCommand(UndoRedoList history) {
        this.history = history;
    }

    /**
     * Undo the latest item in the history (UndoRedoList)
     */
    @Override
    public void execute() {
        boolean success = this.history.undo();
        if (!success) {
            System.out.println("Undo: Already at earliest change");
        }
    }
}
