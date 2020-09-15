package duke;

/**
 * Represents a To Do with a specified description, and boolean to indicate if completed or not.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the Todo description to the user.
     *
     * @return Todo description.
     */
    public String formattedDateString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the Todo description and tags to the user.
     *
     * @return Todo description with tags.
     */
    public String formattedDateStringWithTags() {
        return toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString()
                + (this.tags.size() == 0 ? "" : "\ntags: " + super.getTags());
    }
}
