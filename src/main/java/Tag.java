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
    public String toString() {
        return this.fullTag;
    }
}
