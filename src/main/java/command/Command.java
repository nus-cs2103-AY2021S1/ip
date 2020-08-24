package command;

import java.util.Objects;

/**
 * A class representing a CDuke command
 */
public abstract class Command {

    /**
     * Does the command modify the taskList
     * @return true if yes, otherwise no
     */
    public boolean hasUndo() {
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

    @Override
    public int hashCode() {
        return Objects.hash(this.getClass().toGenericString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return this.hashCode() == obj.hashCode();
    }
}
