package duke.tasks;

/**
 * The Exit command when user want to quit the Duke.
 */
public class Exit extends Task {

    /**
     * Constructs a exit Task.
     */
    public Exit() {
        super("exit", true);
        this.isExit = true;
    }

}
