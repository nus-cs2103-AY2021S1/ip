package duke.ui;

import java.util.Scanner;

import duke.note.Note;
import duke.note.NoteList;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class handles all the interactions, including input and output, with the user.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a string representation for exiting the program.
     */
    public String printExit() {
        String s = "Bye. Hope to see you again soon!";
        return s;
    }

    /**
     * Returns a string representation of a section divider.
     */
    public String printDivider() {
        String s = "_______________________________________________________________";
        return s;
    }

    /**
     * Returns a string representation of greeting message.
     */
    public String printGreeting() {
        String s = "Hello! Dukenizer is back!\nWhat can I do for you?\n\n"
                + "Some things you can do:\n"
                + "1) list: see your task list!\n"
                + "2) notes: see all your notes!\n"
                + "3) todo <description>: add a new task!\n"
                + "4) note <description>: add a new note!\n\n"
                + "Type 'help' to see the full list of commands!";

        return s;

    }

    /**
     * Returns a string representation of the task list in the program.
     */
    public String printList(TaskList lst) {

        assert lst != null : "lst should be a valid list";

        String s = lst.toString();

        if (lst.getSize() == 0) {
            s = "There are no items found in your task list!";
        }
        return s;
    }

    /**
     * Takes in user input to be processed.
     *
     * @return Returns a string representation of the user input.
     */
    public String readCommand() {
        //Take in Input
        String query = sc.nextLine();
        return query;
    }

    /**
     * Returns a string representation of any error messages.
     *
     * @param message error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns a string representation of task being successfully added.
     *
     * @param lst  TaskList in the program
     * @param task Task that was added
     */
    public String printTaskAdded(TaskList lst, Task task) {
        assert task != null : "task should be valid";
        assert lst != null : "lst should be valid";

        String s = "Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + lst.getSize() + " tasks in the list.";

        return s;
    }

    /**
     * Returns a string representation of marking Task as done.
     *
     * @param task Task to be marked done.
     */
    public String printTaskDone(Task task) {
        assert task != null : "task should be valid";

        String s = "Nice! I've marked this task as done:\n" + task.toString();
        return s;
    }

    /**
     * Returns a string representation of Task being successfully deleted.
     *
     * @param lst  TaskList in the program
     * @param task Task to be deleted
     */
    public String printTaskDeleted(TaskList lst, Task task) {
        assert task != null : "task should be valid";
        assert lst != null : "lst should be valid";

        String s = "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + lst.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Returns a string representation of Note being successfully deleted.
     *
     * @param lst  NoteList in the program
     * @param note Note to be deleted
     */
    public String printNoteDeleted(NoteList lst, Note note) {
        assert note != null : "task should be valid";
        assert lst != null : "lst should be valid";

        String s = "Noted. I've removed this note:\n" + note.toString()
                + "\nNow you have " + lst.getSize() + " notes in your program.";
        return s;
    }

    /**
     * Returns a matching tasks message.
     */
    public String printMatchingTasks() {
        String s = "Here are the matching tasks in your list:";
        return s;

    }

    /**
     * Returns a new note added message.
     *
     * @param newNote note that was added.
     * @return String message to notify new note added.
     */
    public String printNoteAdded(Note newNote) {
        assert newNote != null : "note should be valid";

        String s = "Got it. Your note has been added:\n"
                + newNote.toString();
        return s;
    }

    /**
     * Returns a string representation of the note list in the program.
     *
     * @param notes List of notes in the program.
     * @return String representation of the note list.
     */
    public String printNotes(NoteList notes) {
        assert notes != null : "lst should be a valid list";

        String s = notes.toString();

        if (notes.getSize() == 0) {
            s = "There are no notes found! Type 'note <description>' to save a note!";
        }
        return s;
    }

    /**
     * Returns a help messsage for the list of commands.
     *
     * @return String representation of help message.
     */
    public String printHelp() {
        String s = "List of commands:\n"
                + "1) list -Lists the items in your task list.\n\n"
                + "2) todo <description>\n-Adds a To-Do task with the description to your task list.\n\n"
                + "3) deadline <description> /by <yyyy-MM-dd HH:mm>\n-Adds a task with a description "
                + "and a deadline to your task list. E.g. deadline sample task 1 /by 2020-09-12 23:22\n\n"
                + "4) event <description> /at <yyyy-MM-dd HH:mm>\n-Adds a task with a description "
                + "at a specified time to your task list. E.g. event sample task 2 /by 2020-09-12 11:22\n\n"
                + "5) done <item number>\n-Marks the task at item number index in your task list as \"done\"\n\n"
                + "6) delete <item number>\n-Deletes the task at item number index in your task list\n\n"
                + "7) find <description>\n-Finds all tasks in your task list with the description search term\n\n"
                + "8) note <description> -Adds a note with the description in Dukenizer. E.g. note i am 1.8m\n\n"
                + "9) notes -Lists all your notes in Dukenizer.\n\n"
                + "10) RemoveNote <note index>\n-Removes a note at the specified index in your note list\n";


        return s;
    }
}
