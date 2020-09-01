package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h>duke.Ui</h>
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Constructor of duke.Ui class.
     */
    public Ui() {
    }

    /**
     * Prints a horizontal divider line.
     */
    protected void horizontalDiv() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error when there is format issue.
     * @param i The position where the formatting has error.
     */
    protected static void printFormatError(int i) {
        System.out.println("Hello! Looks like there is a format error in your saved file!");
        if (i >= 0) {
            System.out.println("The line on " + (i + 1) + " will be ignored");
        }
    }

    /**
     * Prints the starting welcome message of the bot
     */
    public void showWelcome() {
        horizontalDiv();
        System.out.println("Hello! I'm Dude\n" + "What can I do for you today?");
        horizontalDiv();
    }

    /**
     * Returns the welcome message with GUI is initialized.
     * @return String Welcome message.
     */
    public static String outputWelcome() {
        return "Hello! I'm Dude\n" + "What can I do for you today?";
    }

    /**
     * Prints to let user know what talking you.
     */
    public void invalidInput() {
        horizontalDiv();
        System.out.println("Sorry! But I don't know what that means!");
        horizontalDiv();
    }

    /**
     * Returns a error message on an invalid input
     * @return String Invalid input message.
     */
    public String outputInvalidInput() {
        return "Sorry! But I don't know what that means!";
    }

    /**
     * Prints to greet good bye to user.
     */
    public void showBye() {
        horizontalDiv();
        System.out.println("Bye! Hope to see you again soon!");
        horizontalDiv();
    }

    /**
     * Returns exit message.
     * @return String Exit message.
     */
    public String outputBye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Prints that the number does not exist in the list.
     */
    public void showDoneError() {
        horizontalDiv();
        System.out.println("Sorry! The number does not exist in the list!");
        horizontalDiv();
    }

    /**
     * Returns an error message that it cannot be marked as done.
     * @return String Done error message.
     */
    public String outputDoneError() {
        return "Sorry! The number does not exist in the list!";
    }

    /**
     * Prints that you have mark that task as done.
     * @param str String The task that is being marked as done.
     */
    public void showDoneMsg(String str) {
        horizontalDiv();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(str);
        horizontalDiv();
    }

    /**
     * Returns the message that marks as done.
     * @param task Task to be marked as done.
     * @return String Message to say that it is done.
     */
    public String outputDoneMsg(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Prints that you do not have any task/
     */
    public void showListNoTask() {
        horizontalDiv();
        System.out.println("Congratulations! You have currently no task.");
        horizontalDiv();
    }

    /**
     * Returns message that there is no task in the list.
     * @return String No task message.
     */
    public String outputListNoTask() {
        return "Congratulations! You have currently no task.";
    }

    /**
     * Prints the header for listing the list.
     */
    public void showListTask() {
        horizontalDiv();
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Returns the list of all the task.
     * @param ls List Current list.
     * @return String All the current task.
     */
    public String outputListTask(TaskList ls) {
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < ls.size(); i++) {
            Task task = ls.get(i);
            int num = i + 1;
            str.append(num).append(". ").append(task.toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Prints the to-do task that have been added.
     * @param ls duke.TaskList
     * @param newTask duke.Task The new task to be added.
     */
    public void showTodoMsg(TaskList ls, Task newTask) {
        horizontalDiv();
        System.out.println("Got it. I've added this task: \n" + newTask.toString());
        if (ls.size() > 1) {
            System.out.println("Now you have " + ls.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + ls.size() + " task in the list.");
        }
        horizontalDiv();
    }

    /**
     * Returns a confirmation message that the user have added a to-do task.
     * @param ls The current task list.
     * @param newTask The to-do task to be added in.
     * @return String To-do message.
     */
    public String outputTodoMsg(TaskList ls, Task newTask) {
        String str = "Got it. I've added this task: \n" + newTask.toString() + "\n";
        if (ls.size() > 1) {
            str += "Now you have " + ls.size() + " tasks in the list.";
        } else {
            str += "Now you have " + ls.size() + " task in the list.";
        }
        return str;
    }

    /**
     * Prints that your input for to-do task is wrong.
     */
    public void showTodoError() {
        horizontalDiv();
        System.out.println("Sorry! The description of todo cannot be empty!!");
        System.out.println("Here's an example: todo Homework");
        horizontalDiv();
    }

    /**
     * Returns the error message of to-do.
     * @return String To-do error message.
     */
    public String outputTodoError() {
        return "Sorry! The description of todo cannot be empty!!\n"
                + "Here's an example: todo Homework";
    }

    /**
     * Prints that you have added the deadline task.
     * @param ls duke.TaskList
     * @param newTask duke.Task The new task to be added.
     */
    public void showDeadlineEventMsg(TaskList ls, Task newTask) {
        horizontalDiv();
        System.out.println("Got it. I've added this task: \n" + newTask.toString());
        if (ls.size() > 1) {
            System.out.println("Now you have " + ls.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + ls.size() + " task in the list.");
        }
        horizontalDiv();
    }

    /**
     * Returns a confirmation message that the user has added the deadline task.
     * @param ls TaskList
     * @param newTask Task The new task to be added.
     * @return String A confirmation message for deadline task.
     */
    public String outputDeadlineEventMsg(TaskList ls, Task newTask) {
        String str = "Got it. I've added this task: \n" + newTask.toString() + "\n";
        if (ls.size() > 1) {
            str += "Now you have " + ls.size() + " tasks in the list.";
        } else {
            str += "Now you have " + ls.size() + " task in the list.";
        }
        return str;
    }

    /**
     * Prints that user input for deadline task is wrong.
     */
    public void showDeadlineFormatError() {
        horizontalDiv();
        System.out.println("Sorry! Please enter a date for the deadline using the command '/by'!");
        System.out.println("Here's an example: deadline CS2103T project /by 2020-08-26 23:59");
        horizontalDiv();
    }

    /**
     * Returns an error message that the user input for deadline task is wrong.
     * @return String Error message of deadline.
     */
    public String outputDeadlineFormatError() {
        return "Sorry! Please enter a date for the deadline using the command '/by'!\n"
                + "Here's an example: deadline CS2103T project /by 2020-08-26 23:59";
    }

    /**
     * Prints a warning to show there is not description for the task.
     */
    public void showDeadlineError() {
        horizontalDiv();
        System.out.println("Sorry! The description of deadline cannot be empty!");
        System.out.println("Here's an example: deadline CS2103T project /by 2020-08-26 23:59");
        horizontalDiv();
    }

    /**
     * Returns a warning message to show there is not description for the task.
     * @return String An error message for deadline with an example.
     */
    public String outputDeadlineError() {
        return "Sorry! The description of deadline cannot be empty!\n"
                + "Here's an example: deadline CS2103T project /by 2020-08-26 23:59";
    }

    /**
     * Prints there is a format error for the event task by the user.
     */
    public void showEventFormatError() {
        horizontalDiv();
        System.out.println("Sorry! Please enter a duration for the event using the command '/at'!");
        System.out.println("Here's an example: event welcome tea /at 2020-08-29");
        horizontalDiv();
    }

    /**
     * Returns a error message that there is a format error for the event task by the user.
     * @return String An error message for event task with an example of input.
     */
    public String outputEventFormatError() {
        return "Sorry! Please enter a duration for the event using the command '/at'!\n"
                + "Here's an example: event welcome tea /at 2020-08-29";
    }

    /**
     * Prints warning that the description of event task cannot be empty.
     */
    public void showEventError() {
        horizontalDiv();
        System.out.println("Sorry! The description of event cannot be empty!");
        System.out.println("Here's an example: event welcome tea /at 2020-08-29");
        horizontalDiv();
    }

    /**
     * Returns a error message that warns the description of event task cannot be empty.
     * @return String Error message for event task.
     */
    public String outputEventError() {
        return "Sorry! The description of event cannot be empty!\n"
                + "Here's an example: event welcome tea /at 2020-08-29";
    }

    /**
     * Prints the confirmation that the task has been deleted.
     * @param ls duke.TaskList
     * @param tsk duke.Task duke.Task to be deleted.
     */
    public void showDeleteMsg(TaskList ls, Task tsk) {
        horizontalDiv();
        System.out.println("Successfully deleted this task:");
        System.out.println(tsk.toString());
        if (ls.size() > 1) {
            System.out.println("Now you have " + ls.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + ls.size() + " task in the list.");
        }
        horizontalDiv();
    }

    /**
     * Returns the confirmation that the task has been deleted.
     * @param ls TaskList
     * @param tsk Task Task to be deleted.
     * @return String Confirmation message.
     */
    public String outputDeleteMsg(TaskList ls, Task tsk) {
        String str = "Successfully deleted this task:\n" + tsk.toString() + "\n";
        if (ls.size() > 1) {
            str += "Now you have " + ls.size() + " tasks in the list.";
        } else {
            str += "Now you have " + ls.size() + " task in the list.";
        }
        return str;
    }

    /**
     * Prints an error saying the that deletion cannot occur.
     */
    public void showDeleteError() {
        horizontalDiv();
        System.out.println("Sorry! The number to be deleted does not exist in the list!");
        horizontalDiv();
    }

    /**
     * Returns an error message stating the deletion cannot occur.
     * @return String An error message.
     */
    public String outputDeleteError() {
        return "Sorry! The number to be deleted does not exist in the list!";
    }

    /**
     * Prints that you have no task for all days.
     */
    public void showCheckNoTask() {
        horizontalDiv();
        System.out.println("You have currently no task on all days!");
        horizontalDiv();
    }

    /**
     * Returns message when there is no task for all days.
     * @return String Message of no task.
     */
    public String outputCheckNoTask() {
        return "You have currently no task on all days!";
    }

    /**
     * Prints the number of task for the date that the user checked.
     * @param counter int The number of task counted.
     * @param date LocalDate The date the user is checking.
     */
    public void showCheckTask(int counter, LocalDate date) {
        if (counter == 0) {
            System.out.println("You have currently no incomplete task on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else if (counter == 1) {
            System.out.println("You have a total of " + counter + " incomplete task on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            System.out.println("You have a total of " + counter + " incomplete tasks on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        horizontalDiv();
    }

    /**
     * Returns the number of task for the date that the user checked.
     * @param ls TaskList Current list of task.
     * @param date LocalDate Date to be checked.
     * @return
     */
    public String outputCheckTask(TaskList ls, LocalDate date) {
        StringBuilder str = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < ls.size(); i++) {
            Task task = ls.get(i);
            if (task instanceof Event) {
                if (((Event) task).getAt().equals(date) && !task.isDone()) {
                    counter += 1;
                    str.append(counter).append(". ").append(task.toString()).append("\n");
                }
            } else if (task instanceof Deadline && !task.isDone()) {
                if (((Deadline) task).getDate().equals(date)) {
                    counter += 1;
                    str.append(counter).append(". ").append(task.toString()).append("\n");
                }
            }
        }
        if (counter == 0) {
            str.append("You have currently no incomplete task on ")
                    .append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else if (counter == 1) {
            str.append("You have a total of ").append(counter)
                    .append(" incomplete task on ").append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            str.append("You have a total of ").append(counter)
                    .append(" incomplete tasks on ").append(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }

        return str.toString();
    }

    /**
     * Prints the error saying your format of input for checking date is wrong.
     */
    public void showCheckError() {
        horizontalDiv();
        System.out.println("Sorry! Seems like the format of your input is wrong ><");
        System.out.println("Here's an example: check 2020-08-08");
        horizontalDiv();
    }

    /**
     * Returns the error saying your format of input for checking date is wrong.
     * @return String Error Message.
     */
    public String outputCheckError() {
        return "Sorry! Seems like the format of your input is wrong :o\n"
                + "Here's an example: check 2020-08-08";
    }

    /**
     * Prints the description of finding the task.
     * @param counter int Number of counted matches.
     * @param str String The keyword.
     */
    public void showFindTask(int counter, String str) {
        if (counter == 0) {
            System.out.println("Looks like there is no task matching that keyword: " + str);
            horizontalDiv();
        } else if (counter == 1) {
            System.out.println("^ Only the above task matched the keyword: '" + str + "' from your list.");
            horizontalDiv();
        } else {
            System.out.println("^ The above are matching tasks with keyword: '" + str + "' from your list.");
            horizontalDiv();
        }
    }

    /**
     * Returns the description of finding the task.
     * @param ls TaskList The current list of task.
     * @param keyword String The keyword.
     * @return String Description of finding the task.
     */
    public String outputFindTask(TaskList ls, String keyword) {
        StringBuilder str = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < ls.size(); i++) {
            Task task = ls.get(i);
            if (task.getDescription().contains(keyword)) {
                counter++;
                str.append(counter).append(". ").append(task.toString()).append("\n");
            }
        }
        if (counter == 0) {
            str.append("Looks like there is no task matching that keyword: ").append(keyword);
        } else if (counter == 1) {
            str.append("^ Only the above task matched the keyword: '").append(keyword)
                    .append("' from your list.");
        } else {
            str.append("^ The above are matching tasks with keyword: '").append(keyword)
                    .append("' from your list.");
        }

        return str.toString();
    }

    /**
     * Prints an error when there is no keyword by user.
     */
    public void showFindError() {
        horizontalDiv();
        System.out.println("Key in something for me to find!");
        horizontalDiv();
    }

    /**
     * Returns an error when there is no keyword by user.
     * @return String Error message.
     */
    public String outputFindError() {
        return "Key in something for me to find!";
    }
}
