package main.java.com.jacob.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

import main.java.com.jacob.duke.note.Note;
import main.java.com.jacob.duke.task.Task;

public class Ui {
    /**
     * UI of the Done command.
     *
     * @param taskDescription The currently operated task's status.
     */
    public String showDone(String taskDescription) {
        assert (taskDescription != null);
        return String.format(" Nice! I've marked this task as done: \n" + taskDescription);
    }

    /**
     * Show UI for the newly added tasks.
     *
     * @param taskDescription The currently operated task's status.
     * @param taskList List representation of the current task list.
     */
    public String showNewTaskAdded(String taskDescription, List<Task> taskList) {
        assert (taskDescription != null && taskList != null);
        return String.format(
                " Got it. I've added this task: \n   %s\n Now you have %d tasks in the list.\n",
                taskDescription, taskList.size());
    }

    /**
     * Show UI for the delete task command.
     *
     * @param taskDescription The currently operated task's status.
     * @param taskList List representation of the current task list.
     */
    public String showTaskDeleted(String taskDescription, List<Task> taskList) {
        assert (taskDescription != null && taskList != null);
        return String.format(" Noted. I've removed this task:\n "
                + "   %s\n"
                + " Now you have %d tasks in the list.\n", taskDescription, taskList.size());
    }

    /**
     * Show UI of the print list command.
     *
     * @param taskList List representation of the current task list.
     */
    public String showFullList(List<Task> taskList) {
        assert (taskList != null);
        int count = 1;
        StringBuffer currentTaskList = new StringBuffer();
        currentTaskList.append(" Here are the tasks in your list:\n");
        for (Task t: taskList) {
            currentTaskList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
            count++;
        }
        return currentTaskList.toString();
    }

    /**
     * Show UI of the print filtered list command.
     *
     * @param inputCommand Command includes the date time it is filtering for.
     * @param taskList List representation of the current task list.
     * @throws DukeException Duke Exception thrown if the command is incorrect.
     */
    public String showFilteredDateTimeList(String inputCommand, List<Task> taskList) throws DukeException {
        assert (inputCommand != null && taskList != null);
        if (inputCommand.length() <= "list-due ".length()) {
            throw new DukeException(" list-due command cannot be empty!!");
        }
        //get the date time string from the initial string
        String dateTime = inputCommand.substring("list-due ".length());

        LocalDateTime dateTimeFilter = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));

        Predicate<LocalDateTime> dateTimePredicate = x -> x.equals(dateTimeFilter);

        int count = 1;
        StringBuffer dueDateList = new StringBuffer();
        dueDateList.append(" Here are the tasks in your filtered list:\n");
        for (Task t : taskList) {
            if (t.getDueDateTime() != null && dateTimePredicate.test(t.getDueDateTime())) {
                dueDateList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
                count++;
            }
        }
        return dueDateList.toString();
    }

    /**
     * Show List of tasks with keyword.
     *
     * @param inputCommand Command includes keyword.
     * @param taskList List representation of the current task list.
     * @throws DukeException Throws Exception if the search string is empty.
     */
    public String showKeywordList(String inputCommand, List<Task> taskList) throws DukeException {
        assert (inputCommand != null && taskList != null);
        if (inputCommand.length() <= "find ".length()) {
            throw new DukeException(" find command cannot be empty.");
        }
        String keyword = inputCommand.substring("find ".length());

        if (keyword.trim().equals("")) {
            throw new DukeException(" Search String cannot be left empty.");
        }
        Predicate<String> searchStringPredicate = x -> x.contains(keyword);

        int count = 1;
        StringBuffer keywordList = new StringBuffer();
        keywordList.append(" Here are the tasks in your filtered list:\n");
        for (Task t : taskList) {
            if (t.getDescription() != null && searchStringPredicate.test(t.getDescription())) {
                keywordList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
                count++;
            }
        }
        return keywordList.toString();
    }

    /**
     * UI of the bye command
     */
    public String sayBye() {
        return " Bye. Hope to see you again soon.";
    }

    /**
     * Show UI for the newly added note.
     *
     * @param noteStatus The currently operated task's status.
     * @param noteList List representation of the current task list.
     */
    public String showNewNoteAdded(String noteStatus, List<Note> noteList) {
        assert (noteStatus != null && noteList != null);
        return String.format(
                " Got it. I've added this note: \n   %s\n Now you have %d notes in the list.\n",
                noteStatus, noteList.size());
    }
    /**
     * Show UI for the delete note command.
     *
     * @param noteDescription The currently operated task's status.
     * @param noteList List representation of the current task list.
     */
    public String showNoteDeleted(String noteDescription, List<Note> noteList) {
        assert (noteDescription != null && noteList != null);
        return String.format(" Noted. I've removed this note:\n "
                + "   %s\n"
                + " Now you have %d notes in the list.\n", noteDescription, noteList.size());

    }
    /**
     * Show UI of the print all notes command.
     *
     * @param noteList List representation of the current task list.
     */
    public String showAllNotes(List<Note> noteList) {
        assert (noteList != null);
        int count = 1;
        StringBuffer currentNoteList = new StringBuffer();
        currentNoteList.append(" Here are the notes you stored:\n");
        for (Note t: noteList) {
            currentNoteList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
            count++;
        }
        return currentNoteList.toString();
    }
}
