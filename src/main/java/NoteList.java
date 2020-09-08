import java.util.ArrayList;
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

    public boolean isEmpty() {
        return notesList.isEmpty();
    }

    public void add(Note toAdd) {
        notesList.add(toAdd);
    }

    public void delete(int toDelete) {
        notesList.remove(toDelete);
    }

    public Note get(int index) {
        return notesList.get(index);
    }

    public List<Note> getNotesList() {
        return notesList;
    }

    public int size() {
        return notesList.size();
    }

    /**
     * Returns the list of notes by name.
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
