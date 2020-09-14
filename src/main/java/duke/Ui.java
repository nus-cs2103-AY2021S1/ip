package duke;

import java.util.List;

import duke.task.Task;


/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String INDENTATION = "     ";

    /**
     * Displays the introductions.
     *
     * @return The introductions.
     */
    public String intro() {
        return reply("^._.^  I'm Chubbs, I'll be your best fur-end!!! "
                + "Type 'meow' for the list of instructions" + '\n');
    }

    /**
     * Displays the message with the indentation.
     *
     * @param string String to be displayed.
     * @return The message with proper indentation.
     */
    public String reply(String string) {
        return INDENTATION + string;
    }

    /**
     * Displays the list of tasks
     *
     * @param tasks The list of tasks.
     * @return The list of tasks in proper format.
     */
    public String list(List<Task> tasks) {
        StringBuilder buffer = new StringBuilder();
        if (tasks.size() == 0) {
            buffer.append(reply("You have no pending tasks, fur real!"));
        } else {
            buffer.append(reply("Are these tasks fur-miliar?")).append("\n");
            for (int i = 0; i < tasks.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = tasks.get(i);
                buffer.append(reply(number + currentTask.toString())).append("\n");
            }
        }
        return buffer.toString();
    }

    /**
     * Display the filtered list with the proper response message to the user.
     *
     * @param filteredTasks The filtered tasks.
     * @return The list of filtered tasks.
     */
    public String filteredList(List<Task> filteredTasks) {
        StringBuilder buffer = new StringBuilder();
        if (filteredTasks.size() == 0) {
            buffer = new StringBuilder(reply("No tasks found or maybe I fur-get"));
        } else {
            buffer.append(reply("Here are the matching tasks in your list:")).append("\n");
            for (int i = 0; i < filteredTasks.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = filteredTasks.get(i);

                // Sample output: 1.[T][✘] Task with keyword
                buffer.append(reply(number + currentTask.toString())).append("\n");
            }
        }
        return buffer.toString();
    }

    /**
     * Displays the message when a task is added to the list.
     *
     * @param currentTask The task to be added.
     * @param size        The size of the list.
     * @return The message when a task is added.
     */
    public String addMessage(Task currentTask, Integer size) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(reply("Paw-some! I'll try to remember!"))
                .append("\n")
                .append(reply(INDENTATION + currentTask.toString()))
                .append("\n")
                .append(reply("Now you have " + size + " tasks in my memoewry."))
                .append("\n");
        return buffer.toString();
    }

    /**
     * Displays the message when a task is marked as done.
     *
     * @param currentTask The tasks that was marked as done.
     * @return The message when a task is marked as done.
     */
    public String doneMessage(Task currentTask) {
        currentTask.done();
        StringBuilder buffer = new StringBuilder();
        buffer.append(reply("Good meow!!! I've marked this task as done"))
                .append("\n")
                .append(reply(INDENTATION + currentTask.toString()))
                .append("\n");
        return buffer.toString();
    }

    /**
     * Displays the farewell message
     *
     * @return The farewell message.
     */
    public String farewell() {
        return reply("Bye!!! Don't fur-get me!!!");
    }

    /**
     * Displays the message after deleting a task.
     *
     * @param currentTask The task deleted.
     * @param sizeLeft    The size of the list after deleting the task.
     * @return The delete message.
     */
    public String deleteMessage(Task currentTask, Integer sizeLeft) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(reply("Meeeeoowww. I've eaten this task: "))
                .append("\n")
                .append(reply(INDENTATION + currentTask.toString()))
                .append("\n")
                .append(reply("Now you have " + sizeLeft + " delicious tasks in the list."))
                .append("\n");
        return buffer.toString();
    }

    /**
     * Displays the current deadlines/events this week.
     *
     * @param weeklyTasks The deadlines/events this week.
     * @return The string version of the deadlines/events this week in proper format.
     */
    public String currentWeekTasks(List<Task> weeklyTasks) {
        StringBuilder buffer = new StringBuilder();
        if (weeklyTasks.size() == 0) {
            buffer = new StringBuilder(reply("You are free this week! Time for a nap"));
        } else {
            buffer.append(reply("Don't fur-get your event/deadlines this week!:"))
                    .append("\n");
            for (int i = 0; i < weeklyTasks.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = weeklyTasks.get(i);

                // Sample output: 1.[T][✘] Task with keyword
                buffer.append(reply(number + currentTask.toString())).append("\n");
            }
        }
        return buffer.toString();
    }

    /**
     * Prints out the cat bot guide
     *
     * @return The cat bot guide
     */
    public String displayGuide() {
        return "Try these paw-sible commands!"
                + '\n' + '\n' + "'todo  {description}'"
                + "\n-Adds a todo task to my memeowry"

                + '\n' + '\n' + "'deadline  {description}  /by  {yyyy-MM-dd  HH:mm}'"
                + "\n-Adds a deadline task to my memeowry"

                + '\n' + '\n' + "'event  {description}  /at  {yyyy-MM-dd HH:mm}'"
                + "\n-Adds an event task to my memeowry"

                + '\n' + '\n' + "'list'"
                + "\n-I'll try to uuuuhhh recall your tasks!"

                + '\n' + '\n' + "'done  {task number}'"
                + "\n-I'll mark your task as done!"

                + '\n' + '\n' + "'delete  {task number}'"
                + "\n-Allows me to eat your task!"

                + '\n' + '\n' + "'find  {keyword}'"
                + "\n-I'll help you find some fur-miliar tasks!"

                + '\n' + '\n' + "'reminder'"
                + "\n-Purr-haps I'll show you your tasks this week!"

                + '\n' + '\n' + "'bye'"
                + "\n-Don't even";
    }
}
