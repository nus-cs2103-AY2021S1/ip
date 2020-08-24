package duke;

/**
 * Implementation of the Bot interface. Includes getters for Duke to actually
 * execute the actions.
 */
public class BotClass implements Bot {
    private boolean stop;
    private StringBuilder response;

    public BotClass() {
        stop = false;
        response = new StringBuilder();
    }

    @Override
    public void stop() {
        stop = true;
    }

    @Override
    public void sayLine(String string) {
        if (response.length() != 0) {
            response.append("\n");
        }
        response.append(string);
    }

    public String getMessage() { return response.toString(); }

    public boolean stopped() { return stop; }
}
