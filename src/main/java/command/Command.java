package command;

/**
 * A class representing a CDuke command
 */
public abstract class Command {

    /**
     * Does the command modify the taskList
     * @return true if yes, otherwise no
     */
    public boolean isModifying() {
        return true;
    }

    /**
     * Should the program exit
     * @return true if yes, otherwise no
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command
     */
    public abstract void execute();

    /**
     * Undo the execution of the command (If applicable)
     */
    public abstract void undo();

}
