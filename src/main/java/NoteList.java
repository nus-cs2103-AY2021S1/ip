import java.util.List;

/**
 * Stores the list of notes.
 */
public class NoteList {
    private List<Note> notesList;

    /**
     * Creates a new NoteList with the given pre-existing notes.
     * @param read The list of notes read from the save file.
     */
    public NoteList(List<Note> read) {
        this.notesList = read;
    }

    /**
     * Checks if the note list is empty.
     *
     * @return Whether the list is empty.
     */
    public boolean isEmpty() {
        return notesList.isEmpty();
    }

    /**
     * Adds a note to the note list.
     *
     * @param toAdd The Note to be added.
     */
    public void add(Note toAdd) {
        notesList.add(toAdd);
    }

    /**
     * Deletes a note from the note list.
     *
     * @param toDelete The Note to be deleted.
     */
    public void delete(int toDelete) {
        notesList.remove(toDelete);
    }

    /**
     * Gets the list of notes.
     *
     * @return The list of notes.
     */
    public List<Note> getNotesList() {
        return notesList;
    }

    /**
     * Gets the size of the note list.
     *
     * @return The size of the note list.
     */
    public int size() {
        return notesList.size();
    }

    /**
     * View a specific note in the list.
     *
     * @param index
     * @return The content of the note or an error message.
     */
    public String viewNote(int index) {
        String result;

        if (index < 0 || index >= notesList.size()) {
            result = Responses.BAD_INDEX;
        } else {
            result = Ui.print(notesList.get(index).getContent());
        }
        return result;
    }

    /**
     * Returns the list of notes, showing the name and index.
     *
     * @return String representation of the names of the notes in list, or a message if it is empty.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (isEmpty()) {
            result = new StringBuilder("this list doesn't contain any notes!");
        } else {
            result.append("1. ").append(notesList.get(0).getName());
            for (int i = 2; i <= notesList.size(); i++) {
                result.append(" ✰\n✰ ").append(i).append(". ").append(notesList.get(i - 1).getName());
            }
        }
        return result.toString();
    }

}
