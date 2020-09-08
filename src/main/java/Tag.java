/**
 * Represents a tag.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class Tag {
    private String tag;

    public Tag() {
        this.tag = "";
    }

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return this.tag;
    }
}
