package duke.task;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    private static List<String> tags = new ArrayList<>();
    private final String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    /**
     * Adds a tag to the list of tags if tag did not exist previously.
     *
     * @param tag tag
     */
    public static void addTagIfNew(String tag) {
        if (!Tag.containsTag(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Checks if list of tags contains this tag.
     *
     * @param tag tag
     * @return boolean
     */
    public static boolean containsTag(String tag) {
        return tags.contains(tag);
    }

    /**
     * Lists out all the tags in the list of tags.
     *
     * @return tags
     */
    public static String listTags() {
        StringBuilder reply = new StringBuilder("Here are your tags:");

        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            reply.append("\n").append(i + 1).append(". ").append(tag);
        }
        return reply.toString();
    }

    @Override
    public String toString() {
        return " @" + this.tagName;
    }
}
