package duke.command;

/**
 * UndoCommand implements execute() and undo() methods
 */
public interface UndoCommand extends Command {

    void undo();

}
