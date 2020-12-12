package duke.task;

/**
 * Tag for task
 */
public class Tag {

    private String description;

    /**
     * Construct a Tag object
     * @param description the description of the tag
     */
    public Tag(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the tag
     * @return the description of the tag
     */
    public String getTagDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "#" + description;
    }
}
