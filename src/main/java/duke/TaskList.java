package duke;

import java.util.ArrayList;

import duke.note.Note;
import duke.task.Task;

/**
 * Creates a list that keeps the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<Note> notes;

    /**
     * Creates a task and note lists with an array list of tasks and an array list of notes.
     *
     * @param tasks an array list of tasks
     * @param notes an array list of notes
     */
    public TaskList(ArrayList<Task> tasks, ArrayList<Note> notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

    /**
     * Creates a task list with empty lists of tasks and notes.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.notes = new ArrayList<Note>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a note to the note list.
     *
     * @param note The note to be added.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Delete a note from the note list.
     *
     * @param idx The index of the note to be deleted.
     */
    public void deleteNote(int idx) {
        notes.remove(idx);
    }

    /**
     * Deletes the task with the index of it in the list.
     *
     * @param idx The index of the task to be deleted in the list.
     */
    public void delete(int idx) {
        tasks.remove(idx);
    }

    /**
     * Gets the task of the index passed in.
     *
     * @param idx The index of the task.
     * @return The task with the index passed in.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Gets the note of the index.
     *
     * @param idx The index of the note.
     * @return the note requested.
     */
    public Note getNote(int idx) {
        return notes.get(idx);
    }

    /**
     * Gets an array of the tasks in the task list.
     *
     * @return An array of the tasks.
     */
    public Task[] getArray() {
        return this.tasks.toArray(new Task[0]);
    }

    /**
     * Gets the array of the notes.
     *
     * @return an array of the notes.
     */
    public Note[] getNotesArray() {
        return this.notes.toArray(new Note[0]);
    }

    /**
     * Gets an array list of tasks that contain the keyword.
     *
     * @param keyword The searching keyword.
     * @return An array list of tasks that contain the keyword.
     */
    public ArrayList<Task> filter(String keyword) {
        ArrayList<Task> satisfiedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                satisfiedTasks.add(tasks.get(i));
            }
        }
        return satisfiedTasks;
    }

    /**
     * Gets the string representation of the list of the tasks in the task list.
     *
     * @return A string that represents the list of tasks
     */
    public String getList() {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            list += t.writeMessage() + "\n";
        }
        return list;
    }

    /**
     * Returns the string of the list of notes.
     *
     * @return The string of the list of notes.
     */
    public String getNoteList() {
        String list = "";
        for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            list += n.writeMessage() + "\n";
        }
        return list;
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the size of the note list.
     *
     * @return the size of the note list.
     */
    public int noteSize() {
        return this.notes.size();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += (i + 1) + ": " + tasks.get(i);

            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TaskList)) {
            return false;
        }

        TaskList tasks = (TaskList) obj;

        return tasks.toString().equals(toString());
    }

}
