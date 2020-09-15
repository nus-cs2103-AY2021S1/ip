package duke.task;

import java.util.ArrayList;

/**
 * The list of Tag of the task
 */
public class TagList {

    private ArrayList<Tag> tags;

    /**
     * Construct a TagList object
     */
    public TagList(){
        tags = new ArrayList<>();
    }

    /**
     * Returns a TagList with a list of tags
     * @param tags the list of tags
     * @return a TagList with a list of tags
     */
    public static TagList of(String[] tags) {
        TagList res = new TagList();
        if (tags == null) {
            return res;
        }
        for (String tag : tags) {
            res.addTag(tag);
        }
        return res;
    }

    /**
     * Returns a TagList with a list of tags
     * @param tags the list of tags
     * @return a TagList with a list of tags
     */
    public static TagList of(String tags) {
        return TagList.of(tags.substring(1).split("#"));
    }

    /**
     * Adds new Tag
     * @param tagDescription the description of the new Tag
     */
    public void addTag(String tagDescription){
        tags.add(new Tag(tagDescription));
    }

    /**
     * Returns the Tag at specified index
     * @param index the index that the Tag is at the TagList
     * @return the Tag at specified index
     */
    public Tag getTag(int index) {
        return tags.get(index - 1);
    }

    /**
     * Returns the size of the tag list
     * @return the size of the tag list
     */
    public int getSize() {
        return tags.size();
    }

    public String fileToString() {
        StringBuilder res = new StringBuilder();
        for (Tag tag : tags) {
            res.append(tag.toString());
        }
        return res.toString();
    }

    @Override
    public String toString() {
        if (tags.size() <= 0) {
            return "";
        }
        StringBuilder res = new StringBuilder("   Tags: ");
        for (Tag tag : tags) {
            res.append(tag.toString()).append(" ");
        }
        return res.toString();
    }
}
