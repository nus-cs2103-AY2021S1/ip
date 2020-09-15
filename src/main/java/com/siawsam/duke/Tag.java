package com.siawsam.duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a taggable item's tag.
 */
public class Tag implements Serializable {
    private final String tagName;
    private final List<Taggable> taggedItems = new ArrayList<>();
    
    public Tag(String tagName) {
        this.tagName = tagName;
    }
    
    /**
     * Adds a {@link Taggable taggable} item to this tag.
     *
     * @param taggable The taggable to add.
     */
    public void addItem(Taggable taggable) {
        taggedItems.add(taggable);
    }
    
    /**
     * Removes a {@link Taggable taggable} item from this tag.
     *
     * @param taggable The taggable to remove.
     */
    void removeItem(Taggable taggable) {
        taggedItems.remove(taggable);
    }
    
    /**
     * Checks if the tag has any {@link Taggable taggables} tagged to it.
     *
     * @return True if no taggables are tagged, false if otherwise.
     */
    boolean isTagEmpty() {
        return taggedItems.isEmpty();
    }
    
    /**
     * Return the name of the tag.
     *
     * @return The string representation of the tag name.
     */
    String getTagName() {
        return tagName;
    }
    
    /**
     * Returns the list of {@link Taggable taggables} tagged to this tag.
     *
     * @return The list of taggables.
     */
    List<Taggable> getTaggedItems() {
        return taggedItems;
    }
    
    @Override
    public String toString() {
        return getTagName() + " (" + taggedItems.size() + " items)";
    }
}
