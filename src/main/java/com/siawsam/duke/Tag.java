package com.siawsam.duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable {
    private final String tagName;
    private final List<Taggable> taggedItems = new ArrayList<>();
    
    Tag(String tagName) {
        this.tagName = tagName;
    }
    
    void addItem(Taggable taggable) {
        taggedItems.add(taggable);
    }
    
    void removeItem(Taggable taggable) {
        taggedItems.remove(taggable);
    }
    
    boolean isTagEmpty() {
        return taggedItems.isEmpty();
    }
    
    String getTagName() {
        return tagName;
    }
    
    List<Taggable> getTaggedItems() {
        return taggedItems;
    }
    
    @Override
    public String toString() {
        return getTagName() + " (" + taggedItems.size() + " items)";
    }
}
