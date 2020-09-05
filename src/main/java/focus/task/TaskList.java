package focus.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import focus.parser.Parser;
import focus.storage.Storage;

/** Represents the task list of user. */
public class TaskList {
    /** Arraylist for the task list. */
    private final ArrayList<Task> taskList;

    /** Creates an empty task list if user does not have existing data in files. */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a task list with user's existing data.
     *
     * @param tasks String representation of tasks in user's data.
     */
    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (String task : tasks) {
            taskList.add(Parser.textToTask(task));
        }
    }

    /**
     * Returns number of tasks in task list.
     *
     * @return number of tasks.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds To-Do tasks into task list.
     *
     * @param description Description of To-Do.
     * @param storage Storage created for user.
     * @return String representation of added To-Do.
     */
    public String addToDo(String description, Storage storage) {
        Task toDo = new ToDo(description);
        taskList.add(toDo);
        storage.addData(toDo);
        String printing = "\tGotcha! I've added this task:\n\t"
                + toDo + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Adds Deadline tasks into task list.
     *
     * @param description Description of Deadline.
     * @param date Deadline of Deadline task.
     * @param storage Storage created for user.
     * @return String representation of added Deadline.
     */
    public String addDeadline(String description, LocalDateTime date, Storage storage) {
        Task deadline = new Deadline(description, date);
        taskList.add(deadline);
        storage.addData(deadline);
        String printing = "\tGotcha! I've added this task:\n\t"
                + deadline + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Adds Event tasks into task list.
     *
     * @param description Description of Event.
     * @param date Date and time of Event.
     * @param storage Storage created for user.
     * @return String representation of added Event.
     */
    public String addEvent(String description, LocalDateTime date, Storage storage) {
        Task event = new Event(description, date);
        taskList.add(event);
        storage.addData(event);
        String printing = "\tGotcha! I've added this task:\n\t"
                + event + "\n\t" + "You have "
                + taskList.size() + " tasks on your list now.\n";
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Deletes tasks from task list.
     *
     * @param index Index of task user wants to remove.
     * @param storage Storage created for user.
     * @return String representation of deleted task.
     */
    public String deleteTask(int index, Storage storage) {
        Task taskToBeDeleted = taskList.get(index - 1);
        taskList.remove(index - 1);
        storage.updateData(taskList);
        String deletedTask = "\tRoger that! I've removed this task:\n\t"
                + taskToBeDeleted
                + "\n\tYou have " + taskList.size()
                + " tasks on your list now.\n";
        assert !deletedTask.isEmpty() : "Printing should not blank here.";
        return deletedTask;
    }

    /**
     * Marks tasks done on task list.
     *
     * @param index Index of task user wants to mark as done.
     * @param storage Storage created for user.
     * @return String representation of done task.
     */
    public String markTaskDone(int index, Storage storage) {
        Task finishedTask = taskList.get(index - 1);
        finishedTask.markAsDone();
        storage.updateData(taskList);
        String doneTask = "\t\\(^O^)/ Good job! I've marked this task as done:\n\t"
                + finishedTask + "\n\tKeep going!\n";
        assert !doneTask.isEmpty() : "Printing should not blank here.";
        return doneTask;
    }

    /**
     * Checks the list if index is last index of task list.
     *
     * @param index Index of task.
     * @return String representation of list of tasks.
     */
    private String checkList(int index) {
        String printing = "";
        int number = index + 1;
        if (index == taskList.size() - 1) {
            printing += "" + number + ". " + taskList.get(index);
        } else {
            printing += "" + number + ". " + taskList.get(index) + "\n\t";
        }
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Lists all the tasks on task list.
     *
     * @return String representation of list of tasks.
     */
    public String listTasks() {
        String printing = "";
        printing += "\tHere are the tasks on your list:\n\t";
        for (int i = 0; i < taskList.size(); i++) {
            printing += checkList(i);
        }
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Checks if there are matches for tasks according to the keyword user input.
     *
     * @param keyword Keyword provided by user.
     * @return True if can find tasks that match keyword or false if cannot.
     */
    private boolean canFindTasks(String keyword) {
        boolean canFind = false;
        for (Task task : taskList) {
            if (task.getTaskName().contains(keyword)) {
                canFind = true;
                break;
            }
        }
        return canFind;
    }

    /**
     * Prints the found tasks according to the keyword user input.
     *
     * @param keyword Keyword provided by user.
     * @return String representation of found tasks.
     */
    private String printFoundTasks(String keyword) {
        String printing = "";
        int index = 1;
        printing += "\tHere are the matching tasks on your list:";
        for (Task task : taskList) {
            boolean taskContainsKeyword = task.getTaskName().contains(keyword);
            if (taskContainsKeyword) {
                printing += "\n\t" + index + ". " + task;
                index++;
            }
        }
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Finds the tasks according to the keyword user input.
     *
     * @param keyword Keyword provided by user.
     * @return String representation of found tasks.
     */
    public String findTasks(String keyword) {
        String printing;
        boolean canFind = canFindTasks(keyword);
        if (canFind) {
            printing = printFoundTasks(keyword);
        } else {
            return "\tSorry, there are no tasks that match your keyword!\n";
        }
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Checks the tasks in task list for tasks that are due soon.
     *
     * @param numberOfDays Number of days user sets.
     * @return String representation of tasks within specified time frame.
     */
    private String checkTasks(int numberOfDays) {
        String printing = "";
        int index = 1;
        ArrayList<Task> todoList = new ArrayList<>();
        LocalDateTime currentDate = LocalDateTime.now();
        printing += "\tHere are the deadlines and events tasks due in " + numberOfDays + " days:";
        for (Task task : taskList) {
            if (task.getDeadline() != null) { // not to-do tasks
                long daysDifference = ChronoUnit.DAYS.between(currentDate, task.getDeadline());
                if (daysDifference < numberOfDays) {
                    printing += "\n\t" + index + ". " + task;
                    index++;
                }
            } else {
                todoList.add(task);
            }
        }
        printing += addTodoToPrint(todoList);
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Adds To-Do tasks to print out.
     *
     * @param todoList To-Do list.
     * @return String representation of To-Do tasks.
     */
    private String addTodoToPrint(ArrayList<Task> todoList) {
        String printing = "";
        int todoIndex = 1;
        printing += "\n\n\tHere are the to-do tasks:";
        for (Task task : todoList) {
            printing += "\n\t" + todoIndex + ". " + task;
            todoIndex++;
        }
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }

    /**
     * Reminds user of tasks within a specified time frame.
     * If task is a To-Do task, Pocus will show it too.
     *
     * @param numberOfDays Number of days user sets.
     * @return String representation of tasks within specified time frame.
     */
    public String remindUserOfTasksWithin(int numberOfDays) {
        String printing = checkTasks(numberOfDays);
        assert !printing.isEmpty() : "Printing should not blank here.";
        return printing;
    }
}
