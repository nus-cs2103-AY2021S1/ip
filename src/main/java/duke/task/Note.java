package duke.task;

public class Note {
    private String title;
    private String description;

    Note (String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note: \n" +
                "Title: '" + title +
                "\nDescription='" + description;
    }
}
