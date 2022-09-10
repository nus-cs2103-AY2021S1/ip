package duke.task;

public class Tag {

    private String description;


    public Tag(String description) {
        this.description = description;
    }

    public boolean equals(Tag otherTag) {
        return description == otherTag.getDescription();
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "#" + description;
    }
}
