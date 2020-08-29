package com.siawsam.duke;

import java.util.List;

public class TaskSearcher {
    TaskList taskList;
    
    public TaskSearcher(TaskList taskList) {
        this.taskList = taskList;
    }
    
    /**
     * Searches a TaskList for Tasks matching a search string and prints them out.
     *
     * @param searchString The search string.
     */
    public void searchAndDisplay(String searchString) {
        List<Task> results = taskList.searchByKeyword(searchString);
        if (results.size() > 0) {
            Ui.showSearchResults(results);
        } else {
            Ui.showNoSearchResults();
        }
    }
}
