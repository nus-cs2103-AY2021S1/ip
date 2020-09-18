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

    public TaskList(ArrayList<Task> tasks, ArrayList<Note> notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

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
    
    public void addNote(Note note) {
        notes.add(note);
    }
    
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
