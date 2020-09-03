package duke.data;

/**
 * DukeState stores the state information of Duke.
 */
public class DukeState {

    private boolean exitLoop;
    private boolean useGui;

    /**
     * Constructs a DukeState.
     */
    public DukeState() {
        exitLoop = false;
        useGui = false;
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

    /**
     * Gets useGui value.
     * @return
     */
    public boolean getUseGui() {
        return useGui;
    }

    /**
     * Sets useGui value.
     * @param useGui
     */
    public void setUseGui(boolean useGui) {
        this.useGui = useGui;
    }
}
