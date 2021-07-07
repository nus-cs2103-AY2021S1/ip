package duke.note;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;

public class NoteList {

    private static final String ERROR_MESSAGE = "error loading from file";
    private List<Note> noteList;

    /**
     * Creates a new TaskList Object.
     */
    public NoteList() {
        noteList = new ArrayList<>();
    }

    /**
     * Creates a new TaskList Object from a String source. If the String is valid, a new
     * TaskList with pre-existing tasks would be constructed. Otherwise, a DukeException will be thrown.
     *
     * @param load String to be parsed and converted to list of tasks(TaskList).
     * @throws DukeException when String cannot be successfully parsed.
     */
    public NoteList(String load) throws DukeException {
        //initialize  list of tasks
        noteList = new ArrayList<>();

        //nothing to load from storage
        if (load.equals("")) {
            return;
        }

        try {
            //loop through load to form task list
            String[] notes = load.split("\\|");
            for (String note : notes) {
                Note newNote = new Note(note);
                noteList.add(newNote);
            }
        } catch (Exception e) {
            throw new DukeException(ERROR_MESSAGE);
        }
    }

    /**
     * Adds a Note into the NoteList Object.
     *
     * @param note Note to be added.
     */
    public void add(Note note) {
        assert note != null : "note should be valid";

        this.noteList.add(note);
    }

    /**
     * Returns size of the note list.
     *
     * @return number of notes.
     */
    public int getSize() {
        return this.noteList.size();
    }

    /**
     * Returns the note at a specified index of the note list.
     *
     * @param index index of note.
     * @return Note object at index.
     */
    public Note get(int index) {
        return this.noteList.get(index);
    }

    /**
     * Returns the string representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    public String toString() {
        //initialize String
        String noteList = "";

        //get list to loop through tasks
        List<Note> notes = this.noteList;

        for (int i = 0; i < notes.size(); i++) {
            Note currentNote = notes.get(i);

            //append item index
            noteList += addIndex(i + 1);

            //append item
            noteList += currentNote.toString();


            boolean isNotLastNote = i < notes.size() - 1;

            //append newLine after each task except for last task
            if (isNotLastNote) {
                noteList += "\n";
            }
        }

        return noteList;
    }

    private String addIndex(int i) {
        return String.valueOf(i) + ".";
    }


    /**
     * Deletes a Note in the note list at the specified task number.
     *
     * @param itemNumber task number in the note list(not index number)
     * @return Note that was deleted from the note list.
     */
    public Note delete(int itemNumber) {
        assert itemNumber > 0 : "note number should be a valid item number in the note list";

        Note item = get(itemNumber - 1);
        this.noteList.remove(item);
        return item;
    }
}


