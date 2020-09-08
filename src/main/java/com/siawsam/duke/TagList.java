package com.siawsam.duke;

import java.util.ArrayList;
import java.util.List;

public class TagList {
    private final List<Tag> tags = new ArrayList<>();
    
    private Tag createAndAddTag(String tagName) {
        Tag tag = new Tag(tagName);
        tags.add(tag);
        return tag;
    }
   
    private boolean doesTagExistInList(String tagName) {
        for (Tag t : tags) {
            if (t.getTagName().equals(tagName)) {
                return true;
            }
        }
        return false;
    }
    
    private Tag getTagByName(String tagName) throws DukeException {
        for (Tag t : tags) {
            if (t.getTagName().equals(tagName)) {
                return t;
            }
        }
        throw new DukeException("No such tag exists");
    }
    
    public Tag addTaggableToTag(String tagName, Taggable taggable) throws DukeException {
        Tag tag;
        if (doesTagExistInList(tagName)) {
            tag = getTagByName(tagName);
        } else {
            tag = createAndAddTag(tagName);
        }
        
        tag.addItem(taggable);
        return tag;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
    
        for (int i = 0; i < tags.size(); i++) {
            String taskString = (i + 1) + ". " + tags.get(i) + "\n";
            stringBuilder.append(taskString);
        }
    
        return stringBuilder.toString();
    }
}
