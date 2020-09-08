import java.util.Objects;

public class Tag {
    private static final String TAG_PREFIX = "#";
    private final String fullTag;

    public Tag(String keyword) {
        this.fullTag = TAG_PREFIX + keyword;
    }

    public String getFullTag() {
        return fullTag;
    }

    public String getTagKeyword() {
        return fullTag.substring(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(fullTag, tag.fullTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullTag);
    }

    @Override
    public String toString() {
        return this.fullTag;
    }
}
