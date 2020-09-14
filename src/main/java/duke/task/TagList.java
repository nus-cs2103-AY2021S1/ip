package duke.task;

import java.util.ArrayList;

public class TagList {

    private static ArrayList<Tag> tags = new ArrayList<>();

    /**
     * Adds a tag to the list of tags if tag did not exist previously.
     *
     * @param tagName tag
     */
    public static void addTagIfNew(String tagName) {
        int tagIndex = TagList.containsTag(tagName);
        if (tagIndex == -1) {
            tags.add(new Tag(tagName));
        } else {
            tags.get(tagIndex).increaseCount();
        }
    }

    /**
     * Deletes a tag from the tag list.
     *
     * @param tagName tagName
     */
    public static void deleteTag(String tagName) {
        int tagIndex = TagList.containsTag(tagName);
        tags.remove(tagIndex);
    }

    /**
     * Checks if list of tags contains this tag.
     *
     * @param tagName tag
     * @return boolean
     */
    public static int containsTag(String tagName) {
        for (Tag tag : tags) {
            if (tag.getTagName().equals(tagName)) {
                return tags.indexOf(tag);
            }
        }
        return -1;
    }

    /**
     * Lists out all the tags in the list of tags.
     *
     * @return tags
     */
    public static String listTags() {
        StringBuilder reply = new StringBuilder("Here are your tags:");

        for (int i = 0; i < tags.size(); i++) {
            String tagName = tags.get(i).getTagName();
            reply.append("\n").append(i + 1).append(". ").append(tagName);
        }
        return reply.toString();
    }
}
