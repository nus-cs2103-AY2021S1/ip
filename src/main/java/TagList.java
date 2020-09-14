import java.util.ArrayList;

public class TagList {
    private ArrayList<Tag> tagList;

    public TagList() {
        this.tagList = new ArrayList<>();
    }

    public void addTag(Tag tagToAdd) {
        this.tagList.add(tagToAdd);
    }

    public void removeTag(Tag tagToRemove) {
        this.tagList.removeIf(tag -> tag == tagToRemove);
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    @Override
    public String toString() {
        String res = "";
        for (Tag tag : tagList) {
            res = res + tag.toString();
        }
        return res;
    }
}
