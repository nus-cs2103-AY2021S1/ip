package com.siawsam.duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagList implements Serializable {
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
    
    public void untag(Tag tag, Taggable taggable) {
        tag.removeItem(taggable);
        //we don't keep tags that have no Taggables inside
        if (tag.isTagEmpty()) {
            // compare tags by their names since equality is not preserved by serialization
            tags.removeIf(t -> t.getTagName().equals(tag.getTagName()));
        }
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
