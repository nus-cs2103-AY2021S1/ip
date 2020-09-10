package duke.note;

/**
 * Represents a Note object where it stores a String description.
 */
public class Note {

    private String description;

    /**
     * Constructs a note with the given description.
     *
     * @param description The description of the note.
     */
    public Note(String description) {
        this.description = description;
    }

    public void overwrite(String description) {
        this.description = description;
    }

    /**
     * Returns the String representation of a note.
     *
     * @return Description of the note.
     */
    @Override
    public String toString() {
        return this.description;
    }


}
