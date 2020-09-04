package duke.tasks;

import java.util.ArrayList;
import java.util.List;

import duke.DukeAction;
import duke.DukeException;
import duke.storage.Storage;

/**
 * The TaskList class implements a task list
 * with functionalities to update the task list.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class TaskList {
    protected List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    /**
     * Returns string of list of Task in the task list.
     *
     * @return String of tasks in task list
     * @throws DukeException  If there is no task in the list.
     */
    public String printList() throws DukeException {
        StringBuilder result = new StringBuilder();

        if (this.taskList.size() != 0) {
            // Dino lists out all items in list
            result.append("Dino lists your tasks:");
            for (int i = 0; i < this.taskList.size(); i++) {
                int index = i + 1;
                result.append("\n").append(index).append(". ").append(this.taskList.get(i));
            }
            result.append("\nTo mark off a task after completion"
                    + ", input 'done <task number>'.");
            return result.toString();
        } else {
            throw new DukeException("Rawr! Dino could not find any items in your task list."
                    + "\nGet started by entering a task. "
                    + "Formats for a task can be found by entering 'format'.");
        }
    }

    /**
     * Returns string specifying successful deletion of task from task list.
     * If the task number to delete is valid, removes task from task list
     * and writes this action to the hard disk.
     *
     * @param storage Storage used to write to delete task from hard disk.
     * @param input String of input that describes task to delete.
     *
     * @return String string specifying successful deletion of task
     * @throws DukeException  If task number is invalid.
     */
    public String deleteTask(Storage storage, String input) throws DukeException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber <= this.taskList.size() && taskNumber > 0) {
            // task number is valid
            // Dino deletes task from list
            int taskIndex = taskNumber - 1;
            Task toDelete = this.taskList.get(taskIndex);
            this.taskList.remove(taskIndex);
            storage.writeToFile(toDelete, DukeAction.DELETE);
            return "Rawr! Dino has deleted " + "Task " + taskNumber
                    + " from your list:\n" + toDelete
                    + "\nNumber of tasks in list: " + this.taskList.size();
        } else {
            throw new DukeException("Task " + taskNumber + " is not in your list of tasks!");
        }
    }

    /**
     * Returns string specifying successful adding of task to task list.
     * If input is in correct format for creation of Todo, Deadline or
     * Event, method adds task to the task list, and writes this action
     * to the hard disk.
     *
     * @param storage Storage used to write to add task to hard disk.
     * @param input String of input that describes task to add.
     *
     * @return String string specifying successful deletion of task
     * @throws DukeException  If invalid format for task creation is input.
     */
    public String addTask(Storage storage, String input) throws DukeException {
        String[] inputWords = input.split(" ");
        try {
            String successStatement;
            switch (inputWords[0]) {
            case "todo":
                successStatement = addTodoToListAndStorage(storage, input);
                break;
            case "deadline":
                successStatement = addDeadlineToListAndStorage(storage, input);
                break;
            case "event":
                successStatement = addEventToListAndStorage(storage, input);
                break;
            default:
                // when input is not a deadline, event or todo
                throw new DukeException("Rawr! Dino could not add your task. "
                        + "Make sure your format is correct."
                        + "\nFormats to input a task can be found by entering 'format'.");
            }
            return successStatement;
        } catch (IndexOutOfBoundsException e) {
            // invalid task format entered
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    /**
     * Returns string of Todo task that is added to list and storage.
     * @param storage Storage used to write to add task to hard disk.
     * @param input String of input describing the task to add.
     * @return String of Todo task that is added to list and storage.
     * @throws DukeException If failed to write task to storage.
     */
    public String addTodoToListAndStorage(Storage storage, String input) throws DukeException {
        String task = input.substring(5);
        Todo todo = new Todo(task);
        this.taskList.add(todo);
        storage.writeToFile(todo, DukeAction.ADD);
        return "Dino has added to your list of tasks:\n"
                + todo
                + "\nNumber of tasks in list: " + this.taskList.size();
    }

    /**
     * Returns string of Deadline task that is added to list and storage.
     * @param storage Storage used to write to add task to hard disk.
     * @param input String of input describing the task to add.
     * @return  String of Deadline task that is added to list and storage.
     * @throws DukeException If failed to write task to storage.
     */
    public String addDeadlineToListAndStorage(Storage storage, String input) throws DukeException {
        String[] taskBy = input.substring(9).split(" /by");
        if (taskBy.length == 2 && !taskBy[0].equals("")) {
            // condition checks that input has task description and date/time
            // task deadline taken as string after first '/by'
            Deadline deadline = Deadline.createDeadline(taskBy[0], taskBy[1].substring(1));
            this.taskList.add(deadline);
            storage.writeToFile(deadline, DukeAction.ADD);
            return "Dino has added to your list of tasks:\n"
                    + deadline
                    + "\nNumber of tasks in list: " + this.taskList.size();
        } else {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    /**
     * Returns string of Event task that is added to list and storage.
     * @param storage Storage used to write to add task to hard disk.
     * @param input String of input describing the task to add.
     * @return String of Event task that is added to list and storage.
     * @throws DukeException If failed to write task to storage.
     */
    public String addEventToListAndStorage(Storage storage, String input) throws DukeException {
        String[] eventAt = input.substring(6).split(" /at");
        if (eventAt.length == 2 && !eventAt[0].equals("")) {
            // condition checks that input has task description and date/time
            // task deadline taken as string after first '/at'
            Event event = Event.createEvent(eventAt[0], eventAt[1].substring(1));
            this.taskList.add(event);
            storage.writeToFile(event, DukeAction.ADD);
            return "Dino has added to your list of tasks:\n"
                    + event
                    + "\nNumber of tasks in list: " + this.taskList.size();
        } else {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    /**
     * Returns string specifying successful marking done of task in task list.
     * If input task number is valid, method marks the task as done,
     * and writes this action to the hard disk.
     *
     * @param storage Storage used to write to mark done for task in hard disk.
     * @param input String of input that describes task to mark as done.
     *
     * @return String string specifying successful marking done of task.
     * @throws DukeException  If invalid task number is input.
     */
    public String markDone(Storage storage, String input) throws DukeException {
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber <= this.taskList.size() && taskNumber > 0) {
            // task number is valid
            // Dino marks task as done
            int taskIndex = taskNumber - 1;
            Task doneTask = this.taskList.get(taskIndex);
            storage.writeToFile(doneTask, DukeAction.MARK_DONE);
            doneTask.markAsDone();
            return "Great! Dino has marked " + "Task " + taskNumber
                    + " as done:\n" + doneTask;
        } else {
            throw new DukeException("Task " + taskNumber + " is not in your list of tasks!");
        }
    }

    /**
     * Returns string specifying successful finding of tasks that
     * matches the user input keyword.
     *
     * @param input String of input keyword.
     *
     * @return String string specifying
     * successful search of matching tasks
     * @throws DukeException  If there are no matching tasks.
     */
    public String findTask(String input) throws DukeException {
        String[] inputWords = input.split(" ");
        String keyWord = inputWords[1];
        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:");

        int matchingTasks = 0;
        for (int i = 0; i < this.taskList.size(); i++) {
            String taskString = taskList.get(i).toString();
            if (taskString.contains(keyWord)) {
                result.append("\n").append(taskString);
                matchingTasks++;
            }
        }

        if (matchingTasks > 0) {
            return result.toString();
        } else {
            throw new DukeException("Rawr! "
                    + "Dino could not find any matching tasks in your list.");
        }
    }
}
