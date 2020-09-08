package com.siawsam.duke;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private final String tagName;
    private final List<Taggable> taggedItems = new ArrayList<>();
    
    Tag(String tagName) {
        this.tagName = tagName;
    }
    
    void addItem(Taggable taggable) {
        taggedItems.add(taggable);
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
