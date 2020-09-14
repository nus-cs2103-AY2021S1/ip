package duke.task;

public class Tag {

    private int count;
    private final String tagName;

    /**
     * Constructs a tag.
     *
     * @param tagName tagName
     */
    public Tag(String tagName) {
        this.tagName = tagName;
        this.count = 1;
    }

    public int getCount() {
        return this.count;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        this.count--;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return " @" + this.tagName;
    }
}
