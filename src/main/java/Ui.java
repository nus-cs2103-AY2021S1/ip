import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {

    public Ui() {
    }

    protected void horizontalDiv() {
        System.out.println("____________________________________________________________");
    }

    protected static void printFormatError(int i) {
        System.out.println("Hello! Looks like there is a format error in your saved file!");
        if (i >= 0) {
            System.out.println("The line on " + (i + 1) + " will be ignored");
        }
    }

    public void showWelcome() {
        horizontalDiv();
        System.out.println("Hello! I'm Dude\n" + "What can I do for you today?");
        horizontalDiv();
    }

    public void invalidInput() {
        horizontalDiv();
        System.out.println("Sorry! But I don't know what that means!");
        horizontalDiv();
    }

    public void showBye() {
        horizontalDiv();
        System.out.println("Bye! Hope to see you again soon!");
        horizontalDiv();
    }

    public void showDoneError() {
        horizontalDiv();
        System.out.println("Sorry! The number does not exist in the list!");
        horizontalDiv();
    }

    public void showDoneMsg(String str) {
        horizontalDiv();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(str);
        horizontalDiv();
    }

    public void showListNoTask() {
        horizontalDiv();
        System.out.println("Congratulations! You have currently no task.");
        horizontalDiv();
    }

    public void showListTask() {
        horizontalDiv();
        System.out.println("Here are the tasks in your list:");
    }

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

    public void showTodoError() {
        horizontalDiv();
        System.out.println("Sorry! The description of todo cannot be empty!!");
        System.out.println("Here's an example: todo Homework");
        horizontalDiv();
    }

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

    public void showDeadlineFormatError() {
        horizontalDiv();
        System.out.println("Sorry! Please enter a date for the deadline using the command '/by'!");
        System.out.println("Here's an example: deadline CS2103T project /by 2020-08-26 23:59");
        horizontalDiv();
    }

    public void showDeadlineError() {
        horizontalDiv();
        System.out.println("Sorry! The description of deadline cannot be empty!");
        System.out.println("Here's an example: deadline CS2103T project /by 2020-08-26 23:59");
        horizontalDiv();
    }

    public void showEventFormatError() {
        horizontalDiv();
        System.out.println("Sorry! Please enter a duration for the event using the command '/at'!");
        System.out.println("Here's an example: event welcome tea /at 2020-08-29");
        horizontalDiv();
    }

    public void showEventError() {
        horizontalDiv();
        System.out.println("Sorry! The description of event cannot be empty!");
        System.out.println("Here's an example: event welcome tea /at 2020-08-29");
        horizontalDiv();
    }

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

    public void showDeleteError() {
        horizontalDiv();
        System.out.println("Sorry! The number to be deleted does not exist in the list!");
        horizontalDiv();
    }

    public void showCheckNoTask() {
        horizontalDiv();
        System.out.println("You have currently no task on all days!");
        horizontalDiv();
    }

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

    public void showCheckError() {
        horizontalDiv();
        System.out.println("Sorry! Seems like the format of your input is wrong ><");
        System.out.println("Here's an example: check 2020-08-08");
        horizontalDiv();
    }
}
