package Command;

/**
 * Represents a command that displays all commands and the snytax.
 */
public class ShowFunctionCommand extends Command {

    /**
     * Prints all commands and their syntax.
     */
    public static void execute() {
        System.out.println("list: to show all existing tasks.");
        System.out.println("bye: to exit the todo bot.");
        System.out.println("delete [task index]: to delete the selected task from the todolist.");
        System.out.println("todo [task name]: to add the todo task into the list.");
        System.out.println("deadline [task name] /by [dd-MM-uuuu HHmm]: "
                        + "add a deadline task with the specific time and date.");
        System.out.println("event [task name] /at [dd-MM-uuuu HHmm]: "
                        + "add a event task with the specific period.");
        System.out.println("done [task index]: to mark the specific task as completed.");
        System.out.println("filter [d-MM-uuuu]: to show all the tasks with this date.");
        System.out.println("find [keyword]: to find all tasks with that has the keywords in its name.");
    }

    public static String execute2() {
        String result = "";
        String nLine = System.lineSeparator();
        String one = "list: to show all existing tasks.";
        String two = "bye: to exit the todo bot.";
        String three = "delete [task index]: to delete the selected task from the todolist.";
        String four = "todo [task name]: to add the todo task into the list.";
        String five = "deadline [task name] /by [dd-MM-uuuu HHmm]: "
                + "add a deadline task with the specific time and date.";
        String six = "event [task name] /at [dd-MM-uuuu HHmm]: "
                + "add a event task with the specific period.";
        String seven = "done [task index]: to mark the specific task as completed.";
        String eight = "filter [d-MM-uuuu]: to show all the tasks with this date.";
        String nine = "find [keyword]: to find all tasks with that has the keywords in its name.";
        result = result + one + nLine + two + nLine + three + nLine + four + nLine + five
                + nLine + six + nLine + seven + nLine + eight + nLine + nine;
        return result;
    }
}
