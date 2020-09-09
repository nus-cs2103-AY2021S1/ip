import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotesList implements Serializable {

    // Attributes
    private final List<Note> notesList;

    // Constructor

    /**
     * Creates a new TaskList.
     */
    public NotesList() {
        this.notesList = new ArrayList<>();
    }

    public NotesList(List<Note> listofNotes) {
        this.notesList = listofNotes;
    }

    // Methods

    /**
     * Adds a new note into the NotesList.
     * @param newNote note to be added into the NotesList.
     * @return String denoting result of adding the note into the NotesList.
     */
    public String createNote(Note newNote) {
        notesList.add(newNote);
        int newSize = notesList.size();
        String noteText = String.format("Got it. I've added this note:\n"
                + " %s\n"
                + "Now you have %s tasks in the list", newNote, newSize);
        return noteText;
    }

    /**
     * Deletes a note with specified note number.
     * @param noteNumber note number of note to be deleted.
     * @return String denoting result of deleting note.
     * @throws IndexOutOfBoundsDukeException If note number given is not valid.
     */
    public String deleteNote(int noteNumber) throws IndexOutOfBoundsDukeException {
        try {
            Note note = notesList.get(noteNumber - 1);
            notesList.remove(noteNumber - 1);
            String deleteText = String.format("Okay. I've removed this note:\n"
                    + " %s", note);
            return deleteText;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException(noteNumber, notesList.size(), "note");
        }
    }

    // String Representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int sizeOfList = notesList.size();

        for (int i = 0; i < sizeOfList; i++) {
            int number = i + 1;
            String text = notesList.get(i).toString();
            sb.append(String.format(" %s. %s\n", number, text));
        }

        return sb.toString();
    }

}
