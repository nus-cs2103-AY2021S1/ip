import processor.UI;

public class Duke {

    private boolean isWorking;
    private UI ui;

    /**
     * Constructor for this object
     */
    public Duke() {
        this.isWorking = true;
        this.ui = new UI();
    }

    /**
     * Checks if Duke is working.
     *
     * @return working status of Duke. False if Duke have not terminated, true otherwise.
     */
    public boolean isWorking() {
        return this.isWorking;
    }

    /**
     * Returns the repsonse to a given command.
     *
     * @param input command entered by user
     * @return response processed by UI
     */
    String getResponse(String input) {
        String reply = this.ui.interact(input);
        if (ui.getDoneStatus()) {
            this.isWorking = false;
        }
        return reply;
    }
}
