package com.siawsam.duke;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private final String tagName;
    private final List<Task> taggedTasks = new ArrayList<>();
    
    Tag(String tagName) {
        this.tagName = tagName;
    }
    
    void addTask(Task task) {
        taggedTasks.add(task);
    }
    
    String getTagName() {
        return tagName;
    }
    
    List<Task> getTaggedTasks() {
        return taggedTasks;
    }
}
