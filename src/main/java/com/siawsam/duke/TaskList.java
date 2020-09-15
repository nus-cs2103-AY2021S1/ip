package com.siawsam.duke;

import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and all operations related to modifying it.
 */
public class TaskList implements Serializable {
    private final List<Task> itemList = new ArrayList<>();
    
    /**
     * Adds an item to the TaskList by parsing the item's string literal.
     *
     * @param itemLiteral The string literal of the item.
     * @return The Task object constructed and added to the TaskList.
     * @throws DukeException if an invalid string literal is passed.
     */
    public Task addItem(String itemLiteral) throws DukeException {
        String type = getItemType(itemLiteral);
        Task taskToAdd = null;
        
        switch (type) {
        case "todo":
            taskToAdd = parseTodo(itemLiteral);
            break;
        case "deadline":
            taskToAdd = parseDeadline(itemLiteral);
            break;
        case "event":
            taskToAdd = parseEvent(itemLiteral);
            break;
        default:
            throw new DukeException("Unrecognized task/command.");
        }
        
        itemList.add(taskToAdd);
        return taskToAdd;
    }
    
    //--------start of item literal utilities---------//
    private String getItemType(String item) {
        assert item.length() > 0 : "empty item literal";
    
        return item.split(" ")[0].trim();
    }
    
    private String getItemDescription(String item, String itemType) throws DukeException {
        assert item.length() > 0 : "empty item literal";
        assert itemType.length() > 0 : "empty item type string";
        
        // remove itemtype, which is the first word of the item literal
        return splitItemLiteral(item, itemType)[0]
                       .split(itemType)[1]
                       .trim();
    }
    
    private String getItemParameter(String item, String itemType) throws DukeException {
        assert item.length() > 0 : "empty item literal";
        assert itemType.length() > 0 : "empty item type string";
        
        return splitItemLiteral(item, itemType)[1].trim();
    }
    
    private String[] splitItemLiteral(String item, String itemType) throws DukeException {
        assert item.length() > 0 : "empty item literal";
        assert itemType.length() > 0 : "empty item type string";
        
        String delimiter = itemType.equals("deadline") ? "/by" : "/at";
        String[] tokens = item.split(delimiter);
        
        if (tokens.length == 1) {
            throw new DukeException("Invalid " + itemType + " command (wrong delimiter).");
        }
        
        return tokens;
    }
    //--------end of item literal utilities---------//
    
    //--------start of item literal parsers---------//
    private Task parseTodo(String itemLiteral) throws DukeException {
        assert itemLiteral.length() > 0 : "empty item literal";
        
        if (itemLiteral.trim().equals("todo")) {
            throw new DukeException("Todos must have non-empty "
                                            + "descriptions!");
        }
        
        String todoDescription = itemLiteral.split("todo")[1].trim();
        return new Todo(todoDescription);
    }
    
    private Task parseDeadline(String itemLiteral) throws DukeException {
        assert itemLiteral.length() > 0 : "empty item literal";
        
        try {
            return new Deadline(
                    getItemDescription(itemLiteral, "deadline"),
                    getItemParameter(itemLiteral, "deadline")
            );
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date entered");
        }
    }
    
    private Task parseEvent(String itemLiteral) throws DukeException {
        assert itemLiteral.length() > 0 : "empty item literal";
        
        return new Event(
                getItemDescription(itemLiteral, "event"),
                getItemParameter(itemLiteral, "event")
        );
    }
    //--------end of item literal parsers---------//
    
    Response markAsDone(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does "
                                                       + "not exist and cannot "
                                                       + "be marked as done.");
        }
        return itemList.get(itemIndex - 1).markAsDone();
    }
    
    Response removeItem(int itemIndex) throws IllegalArgumentException {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does "
                                                       + "not exist and cannot "
                                                       + "be removed.");
        }
        Task removed = itemList.remove(itemIndex - 1);
        return new Response(Ui.showSuccessfulRemoval(removed));
    }
    
    /**
     * Returns a list of Task objects that match a search string.
     *
     * @param keyword The search string.
     * @return A List of Tasks.
     */
    public List<Task> searchByKeyword(String keyword) {
        List<Task> results = new ArrayList<>();
        
        itemList.forEach(task -> {
            if (task.includesKeyword(keyword)) {
                results.add(task);
            }
        });
        
        return results;
    }
    
    /**
     * Tags an item in the tasklist.
     *
     * @param itemIndex The index of the item in the tasklist to tag.
     * @param tagName The name of the tag.
     * @param tagList The tag list instance to handle the tagging.
     * @return A Response representing the result of the tagging operation.
     */
    Response tagItem(int itemIndex, String tagName, TagList tagList) {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does "
                                                       + "not exist and cannot "
                                                       + "be tagged.");
        }
        
        try {
            Task task = itemList.get(itemIndex - 1);
            if (task.isTagged()) {
                return new Response(Ui.showTaskAlreadyTagged());
            }
            
            Tag tag = tagList.addTaggableToTag(tagName, task);
            task.addTag(tag);
            return new Response(Ui.showSuccessfulTag(task));
        } catch (DukeException exception) {
            return new Response(Ui.showErrorMessage(exception));
        }
    }
    
    /**
     * Untags an item.
     *
     * @param itemIndex The index of the item in the tasklist to untag.
     * @param tagList The tag list instance to handle the untagging.
     * @return A Response representing the result of the untagging operation.
     */
    Response untagItem(int itemIndex, TagList tagList) {
        if (itemIndex > itemList.size()) {
            throw new IllegalArgumentException("Item #" + itemIndex + " does "
                                                       + "not exist and cannot "
                                                       + "be untagged.");
        }
        
        Task task = itemList.get(itemIndex - 1);
        if (!task.isTagged()) {
            return new Response(Ui.showItemNotTagged());
        }
        tagList.untag(task.getTag(), task);
        task.removeTag();
        return new Response(Ui.showSuccessfulUntag());
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 1; i <= itemList.size(); i++) {
            String taskString = i + ". " + itemList.get(i - 1) + "\n";
            stringBuilder.append(taskString);
        }
        
        return stringBuilder.toString();
    }
}
