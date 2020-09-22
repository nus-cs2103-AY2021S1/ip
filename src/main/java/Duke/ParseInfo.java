package duke;

import java.util.ArrayList;

public class ParseInfo {
    private Boolean wasTaskListUndone;
    private Boolean wasTaskListUpdated;
    private ArrayList<String> responses;

    /**
     * Initialises a ParseInfo object.
     */
    public ParseInfo() {
        this.wasTaskListUndone = false;
        this.wasTaskListUpdated = false;
        this.responses = new ArrayList<String>();
    }

    public void taskListDidUpdate() {
        this.wasTaskListUpdated = true;
    }

    public void taskListWasUndone() {
        this.wasTaskListUndone = true;
    }

    /**
     * Adds a response to ParseInfo.
     * @param response The response to be added to ParseInfo.
     */
    public void addResponse(String response) {
        assert response.length() != 0 : "empty response added";
        this.responses.add(response);
    }

    public Boolean isListUpdated() {
        return this.wasTaskListUpdated;
    }

    public Boolean isListUndone() {
        return this.wasTaskListUndone;
    }

    public ArrayList<String> getResponses() {
        return this.responses;
    }
}
