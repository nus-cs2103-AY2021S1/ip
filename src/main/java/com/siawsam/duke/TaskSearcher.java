package com.siawsam.duke;

import java.util.List;

/**
 * Represents a class to handle search operations within a tasklist.
 */
public class TaskSearcher {
    private final TaskList taskList;
    
    public TaskSearcher(TaskList taskList) {
        this.taskList = taskList;
    }
    
    /**
     * Searches a TaskList for Tasks matching a search string and prints them out.
     *
     * @param searchString The search string.
     * @return A response representing the search result.
     */
    public Response searchAndDisplay(String searchString) {
        List<Task> results = taskList.searchByKeyword(searchString);
        if (results.size() > 0) {
            return new Response(Ui.showSearchResults(results));
        } else {
            return new Response(Ui.showNoSearchResults());
        }
    }
}
