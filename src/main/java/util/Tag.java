package util;

/**
 * Represents a tag which can be tagged to tasks.
 */
public class Tag {
    /**
     * Name of tag.
     */
    private final String name;

    /**
     * Creates a new tag with given name.
     *
     * @param name Name of tag.
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Returns name of tag.
     *
     * @return Name of tag.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns string representation of tag.
     *
     * @return String representation of tag.
     */
    @Override
    public String toString() {
        return "#" + name;
    }
}
