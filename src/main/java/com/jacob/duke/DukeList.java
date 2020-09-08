package main.java.com.jacob.duke;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jacob.duke.note.Note;
import main.java.com.jacob.duke.task.Task;

public class DukeList {
    private List<Task> taskList;
    private List<Note> noteList;

    /**
     * Constructor for Duke List, composite holder of dual storage note list and task list.
     *
     * @param taskList List of tasks stored.
     * @param noteList List of notes stored.
     */
    public DukeList(List<Task> taskList, List<Note> noteList) {
        this.taskList = taskList;
        this.noteList = noteList;
    }

    /**
     * Create a taskList object to encapsulate the task list and note list.
     */
    public DukeList() {
        taskList = new ArrayList<>();
        noteList = new ArrayList<>();
    }

    /**
     * List containing the list of to do items.
     *
     * @return List containing the list of to do items.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * List containing the list of notes.
     *
     * @return List containing the notes made
     */
    public List<Note> getNoteList() {
        return noteList;
    }
}
