package duke.data;

/**
 * DukeState stores the state information of Duke.
 */
public class DukeState {

    private boolean exitLoop;

    /**
     * Constructs a DukeState.
     */
    public DukeState() {
        exitLoop = false;
    }

    /**
     * Gets the exitLoop value.
     * @return the exitLoop value
     */
    public boolean getExitLoop() {
        return exitLoop;
    }

    /**
     * Makes exitLoop true;
     */
    public void endLoop() {
        exitLoop = true;
    }
}
