package duke.note;

public class Note {
    private String title;
    private String description;

    /**
     * Creates a note.
     *
     * @param title       The title of the note.
     * @param description The description of the note.
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String writeMessage() {
        return String.format("%s | %s", this.title, this.description);
    }

    @Override
    public String toString() {
        return this.title;
    }

}
