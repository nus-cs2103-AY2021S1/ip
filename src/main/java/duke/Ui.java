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
     * Prints to let user know what talking you.
     */
    public void invalidInput() {
        horizontalDiv();
        System.out.println("Sorry! But I don't know what that means!");
        horizontalDiv();
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
     * Prints that the number does not exist in the list.
     */
    public void showDoneError() {
        horizontalDiv();
        System.out.println("Sorry! The number does not exist in the list!");
        horizontalDiv();
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
     * Prints that you do not have any task/
     */
    public void showListNoTask() {
        horizontalDiv();
        System.out.println("Congratulations! You have currently no task.");
        horizontalDiv();
    }

    /**
     * Prints the header for listing the list.
     */
    public void showListTask() {
        horizontalDiv();
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints the to-do task that have been added.
     * @param ls duke.TaskList
     * @param newTask duke.Task The new task to be added.
     */
    public void showTodoMsg(TaskList ls, Task newTask) {
        horizontalDiv();
        System.out.println("Got it. I've added this task: \n" + newTask.toString());
        if(ls.size() > 1) {
            System.out.println("Now you have " + ls.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + ls.size() + " task in the list.");
        }
        horizontalDiv();
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
     * Prints that you have added the deadline task.
     * @param ls duke.TaskList
     * @param newTask duke.Task The new task to be added.
     */
    public void showDeadlineEventMsg(TaskList ls, Task newTask) {
        horizontalDiv();
        System.out.println("Got it. I've added this task: \n" + newTask.toString());
        if(ls.size() > 1) {
            System.out.println("Now you have " + ls.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + ls.size() + " task in the list.");
        }
        horizontalDiv();
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
     * Prints a warning to show there is not description for the task.
     */
    public void showDeadlineError() {
        horizontalDiv();
        System.out.println("Sorry! The description of deadline cannot be empty!");
        System.out.println("Here's an example: deadline CS2103T project /by 2020-08-26 23:59");
        horizontalDiv();
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
     * Prints warning that the description of event task cannot be empty.
     */
    public void showEventError() {
        horizontalDiv();
        System.out.println("Sorry! The description of event cannot be empty!");
        System.out.println("Here's an example: event welcome tea /at 2020-08-29");
        horizontalDiv();
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
     * Prints an error saying the that deletion cannot occur.
     */
    public void showDeleteError() {
        horizontalDiv();
        System.out.println("Sorry! The number to be deleted does not exist in the list!");
        horizontalDiv();
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
     * Prints the error saying your format of input for checking date is wrong.
     */
    public void showCheckError() {
        horizontalDiv();
        System.out.println("Sorry! Seems like the format of your input is wrong ><");
        System.out.println("Here's an example: check 2020-08-08");
        horizontalDiv();
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
     * Prints an error when there is not keyword by user.
     */
    public void showFindError() {
        horizontalDiv();
        System.out.println("Key in something for me to find!");
        horizontalDiv();
    }
}
