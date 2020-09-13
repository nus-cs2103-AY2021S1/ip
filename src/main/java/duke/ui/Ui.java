package duke.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tool.TaskList;

/**
 * The class deals with interactions with the user.
 */
public class Ui {
    /**
     * Returns the formatted space.
     *
     * @return a whitespace.
     */
    public String spaceBeforeOrder() {
        return "      ";
    }


    /**
     * Prints the greeting message.
     */
    public String showGreeting() {
        return "Hello! I'm DuiDui\nWhat can I do for you?";
    }

    public String showReminderToday(TaskList taskList) {
        TaskList today = taskList.findTasksInCertainDate(LocalDate.now());
        String firstSentence = "DO REMIND TO DO IN TODAY:\n";
        if (today.getNumOfTasks() == 0) {
            return firstSentence + "\n"
                    + "Nothing to do on today.";
        }
        return useStreamListTasks(firstSentence, today);
    }

    public String showReminderTomorrow(TaskList taskList) {
        TaskList tomorrow = taskList.findTasksInCertainDate(LocalDate.now().plusDays(1));
        String firstSentence = "DO REMIND TO DO IN TOMORROW:\n";
        if (tomorrow.getNumOfTasks() == 0) {
            return firstSentence + "\n"
                    + "Nothing to do on tomorrow.";
        }
        return useStreamListTasks(firstSentence, tomorrow);
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    /**
     * Prints the message showing the task is done.
     *
     * @param taskList
     * @param i ith task.
     * @return
     */
    public String showDoneMessage(TaskList taskList, int i) {
        return "Nice! I've marked this task as done:\n"
                + spaceBeforeOrder() + taskList.getTask(i) + "\n" + spaceBeforeOrder() + "Now you have "
                + taskList.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Prints the message showing the task is deleted.
     *
     * @param taskList
     * @param removed the removed task.
     * @return
     */
    public String showDeleteMessage(TaskList taskList, Task removed) {
        return "Noted. I've removed this task:\n"
                + spaceBeforeOrder() + removed + "\n" + spaceBeforeOrder() + "Now you have "
                + taskList.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Prints the message showing the task is added.
     *
     * @param taskList
     * @param num current number of tasks in the list.
     */
    public String showAddedMessage(TaskList taskList, int num) {
        return "Got it. I've added this task:\n"
                + spaceBeforeOrder() + taskList.getTask(num)
                + "\n" + spaceBeforeOrder() + "Now you have "
                + (num + 1) + " tasks in the list.";
    }

    /**
     * Returns string representation of the tasks in the list.
     *
     * @param task
     * @param taskList
     * @return the string representation of the task.
     */
    public String presentTask(Task task, TaskList taskList) {
        return spaceBeforeOrder() + (taskList.getTaskList().indexOf(task) + 1)
                + ". " + task + "\n";
    }

    /**
     * Returns the string represents the whole task list
     * starting with the first sentence.
     *
     * @param firstSentence
     * @param taskList
     * @return the string representation.
     */
    public String useStreamListTasks(String firstSentence, TaskList taskList) {
        return taskList.getTaskList().stream()
                .reduce(firstSentence,
                        (string, task) -> string + presentTask(task, taskList),
                        (string1, string2) -> string1 + string2);
    }

    /**
     * Prints the tasks in the list.
     *
     * @param taskList
     * @return
     */
    public String listTasks(TaskList taskList) {
        String firstSentence = "Here are the tasks in your list:\n";
        if (taskList.getNumOfTasks() == 0) {
            return firstSentence + "\n"
                    + "No tasks need to be done! Take a rest!";
        }
        return useStreamListTasks(firstSentence, taskList);
    }

    /**
     * Prints the tasks in the list which contain the certain string.
     *
     * @param taskList
     * @param toFind
     * @return
     */
    public String listMatchedTasks(TaskList taskList, String toFind) {
        String firstSentence = "Here are the matching tasks in your list:\n";
        TaskList listWithKeyword = new TaskList(new ArrayList<>());
        taskList.getTaskList().stream()
                .filter(task -> task.getName().contains(toFind))
                .forEach(task -> listWithKeyword.add(task));
        if (listWithKeyword.getNumOfTasks() == 0) {
            return firstSentence + "\n"
                    + "Opps! Find nothing related.";
        }
        return useStreamListTasks(firstSentence, listWithKeyword);
    }

    /**
     * Prints the goodbye message.
     *
     * @return
     */
    public String showGoodbye() {
        return "Bye. Very nice to meet you!\n"
                + "Do remember my name is DuiDui.\n"
                + "Hope to see you again soon!";
    }
}
