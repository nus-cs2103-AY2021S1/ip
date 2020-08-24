package duke.command;

import java.util.Objects;

/**
 * A class representing a CDuke duke.command
 */
public abstract class Command {

    /**
     * Does the duke.command implements undo
     * Most duke.command that modifies the taskList will implement undo
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
     * Executes the duke.command
     */
    public abstract void execute();

    /**
     * Undo the execution of the duke.command (If applicable)
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
