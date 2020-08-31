package duke;

import java.util.ArrayList;

public class ParseInfo {
    private Boolean wasTaskListUpdated;
    private ArrayList<String> responses;

    /**
     * Initialises a ParseInfo object.
     */
    public ParseInfo() {
        this.wasTaskListUpdated = false;
        this.responses = new ArrayList<String>();
    }

    public void taskListDidUpdate() {
        this.wasTaskListUpdated = true;
    }

    public void addResponse(String response) {
        this.responses.add(response);
    }

    public Boolean isListUpdated() {
        return this.wasTaskListUpdated;
    }

    public ArrayList<String> getResponses() {
        return this.responses;
    }
}
