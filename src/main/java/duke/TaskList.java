package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the contents of the ArrayList in a String.
     *
     * @return Contents of the ArrayList.
     * @throws DukeException If ArrayList is empty.
     */
    public String listContents() throws DukeException {
        if (list.size() > 0) {
            String text = "Here is your list:";
            for (int i = 1; i <= list.size(); i++) {
                text = text + "\n" + i + "." + list.get(i - 1);
            }
            return text;
        } else {
            throw(DukeException.emptyList());
        }
    }

    /**
     * Adds the specified task to the list.
     *
     * @param task The task description.
     * @param dateTime The date and time of the task.
     * @param type The task type.
     */
    public void add(String task, LocalDateTime dateTime, TaskType type) {
        switch (type) {
        case DEADLINE:
            Task nextDeadline = new Deadline(task, dateTime);
            list.add(nextDeadline);
            break;
        case EVENT:
            Task nextEvent = new Event(task, dateTime);
            list.add(nextEvent);
            break;
        case TODO:
            Task nextToDo = new ToDo(task);
            list.add(nextToDo);
            break;
        }
    }

    /**
     * Deletes the specified task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The description of the deleted task.
     * @throws DukeException If there is no task with the given index.
     */
    public String delete(int index) throws DukeException {
        try {
            Task task = list.get(index);
            list.remove(index);
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param index The index of the task to be marked.
     * @return The description of the marked task.
     * @throws DukeException If there is no task with the given index.
     */
    public String done(int index) throws DukeException {
        try {
            list.get(index).setDone();
            return list.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw(DukeException.outOfBounds());
        }
    }

    /**
     * Returns the tasks in the list in a format readable by the Storage when it is loading the save file.
     *
     * @return Tasks in a Storage readable format.
     */
    public String extractListData() {
        int len = list.size();
        if (len > 0) {
            String text = list.get(0).toCommand();
            for (int i = 1; i < len; i++) {
                Task t = list.get(i);
                text = text + "\n" + t.toCommand();
            }
            return text;
        } else {
            return "";
        }
    }

    /**
     * Returns a list of tasks due in the given number of hours from current time.
     *
     * @param hours Given number of hours.
     * @return A list of tasks due.
     */
    public String extractDueTasksHours(long hours) {
        int i = 0;
        String text = "These tasks are due in " + hours + " hour(s):";
        for (Task t : list) {
            if (t.compareTime(LocalDateTime.now(), hours)) {
                i++;
                text += "\n" + i + "." + t;
            }
        }
        text += "\nCount: " + i;
        return text;
    }

    /**
     * Returns a list of tasks due in the given number of days from current time.
     *
     * @param days Given number of days.
     * @return A list of tasks due.
     */
    public String extractDueTasksDays(long days) {
        int i = 0;
        String text = "These tasks are due in " + days + " day(s):";
        for (Task t : list) {
            if (t.compareTime(LocalDateTime.now(), days * 24)) {
                i++;
                text += "\n" + i + "." + t;
            }
        }
        text += "\nCount: " + i;
        return text;
    }
}
