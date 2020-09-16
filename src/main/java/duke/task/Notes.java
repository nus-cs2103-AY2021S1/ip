package duke.task;

public class Notes {

    private String note;
    Notes(String note) {
        if (note.equals("null")) {
            this.note = null;
        } else {
            this.note = note;
        }
    }
    public String getNote() {
        return note;
    }

    /**
     * Changes the current note to the argument note.
     *
     * @param note The note to replace the current note.
     * @return The note that replaced the current note.
     */
    public String editNote(String note) {
        this.note = note;
        return note;
    }
    @Override
    public String toString() {
        if (note == null) {
            return "No written note.";
        }
        return note;
    }

}
