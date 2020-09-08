package com.siawsam.duke;

import java.util.List;

public class Ui {
    /**
     * Displays the welcome message when Duke starts.
     */
    public static String showWelcomeMessage() {
        return "Hi I'm Duke, your personal task-tracker bot!\n"
               + "You can add todos, deadlines, or events to my list.";
    }

    static String showGoodbyeMessage() {
        return "Bye. Hope to see you again";
    }

    static String showSuccessfulLoad() {
        return "Your existing task list has been retrieved from disk.";
    }

    public static String showNoExistingSave() {
        return "You don't have an existing saved task list.";
    }

    static String showSuccessfulAdd(Task task) {
        return "added: " + task;
    }

    static String showSuccessfulSave() {
        return "Alright, your list has been saved!";
    }

    static String showErrorMessage(Exception exception) {
        return exception.getMessage();
    }

    static String showErrorMessage(String customMessage, Exception exception) {
        return customMessage + exception.getMessage();
    }

    static String showSuccessfulRemoval(Task task) {
        return "This task has been removed: " + task;
    }

    static String showMarkedAsDone(Task task) {
        return "I've marked this task as done:\n" + task;
    }
    
    static String showSearchResults(List<Task> results) {
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        
        results.forEach(task -> {
            stringBuilder.append(task.toString() + "\n");
        });
        return stringBuilder.toString();
    }
    
    static String showNoSearchResults() {
        return "There are no matching tasks in your list.";
    }
    
    static String printList(TaskList taskList) {
        return taskList.toString();
    }
    
    static String showSuccessfulTag(Task task) {
        return "This task has been tagged: " + task;
    }
    
    static String showSuccessfulUntag() {
        return "Task successfully untagged.";
    }
    
    static String showTaskAlreadyTagged() {
        return "This task has already been tagged. Untag before adding new tag.";
    }
    
    static String showItemNotTagged() {
        return "This item does not have a tag.";
    }
    
    static String printTags(TagList tagList) {
        return "These are your tags:\n" + tagList.toString();
    }
}
