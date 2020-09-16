import java.util.List;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;
    private String line = "";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }

    public String showError(String e) {
        return (e);
    }

    public String sayHi() {
        String str = ("\t" + line + "\n"
                + "\tHi I am RollRoll :D\n"
                + "\tYour Todo-list helper teehee <3\n"
                + "\tTell me the following commands and i will \n\thelp you track your tasks:\n"
                + "\t\t 1. todo <task>\n"
                + "\t\t 2. deadline <task> /by \n\t\t<YYYY-MM-DD>\n"
                + "\t\t 3. event <task> /at <YYYY-MM-DD>\n"
                + "\t\t 4. done <number of the task>\n"
                + "\t\t 5. prioritize <number of the task>\n"
                + "\t\t 6. delete <number of the task>\n"
                + "\t\t 7. find <keyword>\n"
                + "\t\t 8. list\n"
                + "\t\t 9. bye\n"
                + "\t" + line);
        return (str);
    }

    public String sayBye() {
        sc.close();
        return ("\t" + line + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + line);
    }

    public String printAddTask(Task task, int numOfTask) {
        String tasks = numOfTask == 1 ? "task" : "tasks";
        return ("\t" + line + "\n\tGot it. I've added this task:\n"
                + "\t  " + task + "\n"
                + "\tNow you have " + numOfTask + " " + tasks + " in the list.\n"
                + "\t" + line);
    }

    public String showList(TaskList task) {
        String str;
        if (task.getList().size() > 0) {
            str = ("\t" + line + "\n\tHere are the tasks in your list:\n");
            int index = 1;
            List<Task> ls = task.getList();
            for (Task t : ls) {
                str += ("\t" + index + ". " + t + "\n");
                index++;
            }
            str += ("\t" + line + "\n");
        } else {
            str = ("\t" + line + "\n");
            str += ("\tThere is no task in the list.\n");
            str += ("\t" + line);
        }
        return str;
    }

    public String printDone(TaskList list, int num) {
        return ("\t" + line + "\n\tNice! I've marked this task as done:\n\t  "
                + list.getList().get(num - 1)
                + "\n\t" + line);
    }

    public String printInvalidNumber() {
        return ("☹ OOPS!!! The task number is invalid.");
    }

    public String errorWithFile() {
        return ("☹ OOPS!!! Cannot store tasks.");
    }

    public String printDelete(TaskList list, int num) {
        return ("\t" + line + "\n\tNoted. I've removed this task:\n\t  "
                + list.getList().get(num - 1)
                + "\n\tNow you have " + (list.getList().size() - 1) + " tasks in the list."
                + "\n\t" + line);
    }

    public String printNoDescription() {
        return ("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public String printNoDate() {
        return ("☹ OOPS!!! The description or date of a deadline cannot be empty.");
    }

    public String printNoDateEvent() {
        return ("☹ OOPS!!! The description or date of an event cannot be empty.");
    }

    public String printInvalidDate() {
        return ("☹ OOPS!!! The date must be valid and in the format of YYYY-MM-DD.");
    }

    public String errorWithLoading() {
        return ("☹ OOPS!!! Your file cannot be loaded :-(");
    }
<<<<<<< HEAD

    public String printFindTask(TaskList task) {
        String str;
        if (task.getList().size() > 0) {
            str = ("\t" + line + "\n\tHere are the matching task(s) in your list:\n");
            int index = 1;
            List<Task> ls = task.getList();
            for (Task t : ls) {
                str += ("\t" + index + ". " + t + "\n");
                index++;
            }
            str += ("\t" + line + "\n");
        } else {
            str = ("\t" + line + "\n");
            str += ("\tThere is no matching task in the list.\n");
            str += ("\t" + line);
        }
        return str;
    }

    public String printInvalidKeyword() {
        return ("☹ OOPS!!! Please tell me a keyword!");
    }
||||||| 1cb78ba
=======

    public String printPrioritizeTask(TaskList list, int num) {
        return ("\t" + line + "\n\tNoted. I've prioritized this task:\n\t  "
                + list.getList().get(num - 1)
                + "\n\t" + line);
    }
>>>>>>> 9add1ed834983e187ad56fdfc20287695369ee95
}
