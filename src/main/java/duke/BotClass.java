package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Bot interface. Includes getters for Duke to actually
 * execute the actions.
 */
public class BotClass implements Bot {
    private boolean stop;
    private List<String> response;

    public BotClass() {
        stop = false;
        response = new ArrayList<>();
    }

    @Override
    public void stop() {
        stop = true;
    }

    @Override
    public void sayLine(String string) {
        response.add(string);
    }

    @Override
    public void sayLines(List<String> strings) {
        response.addAll(strings);
    }

    public List<String> getLines() { return response; }

    public boolean stopped() { return stop; }
}
