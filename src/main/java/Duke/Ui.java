package Duke;

import java.util.List;

/**
 * Encapsulates the user interaction.
 */
public class Ui {
    /**
     * Displays the loading error.
     * @return String result for GUI
     */
    public String showLoadingError() {
        System.out.println("Failed to load the file.");
        return "Failed to load the file.\n";
    }

    /**
     * Displays the number format error.
     * @return String result for GUI
     */
    public String showNumberFormatError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("      OOPS!!! Please enter a number");
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n      OOPS!!! Please enter a number\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * Displays the potential error when running the Duke bot.
     * @param e an exception object which contains the error message.
     *          @return String result for GUI
     */
    public String showDukeError(DukeException e) {
        System.out.println("    ____________________________________________________________");
        System.out.println(e.toString().substring(19));
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n" + e.toString().substring(19) + "\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * Displays the file not found error.
     * @return String result for GUI
     */
    public String showFileNotFoundError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("      OOPS!!! File is not found.");
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n      OOPS!!! File is not found.\n" +
                "    ____________________________________________________________\n";
    }

    /**
     * Displays the welcome message.
     */
    public void welcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the list of the todo events.
     * @param data the list of the todo events.
     * @return String result for GUI
     */
    public String printList(List<Task> data) {
        String res = "";
        System.out.println("    ____________________________________________________________");
        res += "    ____________________________________________________________\n";
        System.out.println("     Here are the tasks in your list:");
        res += "     Here are the tasks in your list:\n";
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
            res += "     " + (i + 1) + "." + data.get(i).toString() + "\n";
        }
        System.out.println("    ____________________________________________________________");
        res += "    ____________________________________________________________\n";
        return res;
    }

    /**
     * Displays the message to inform the success of Done operation.
     * @param data the list of the events.
     * @param n the label of the event that will set to be done.
     * @return String result for GUI
     */
    public String printDone(List<Task> data, int n) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.printf("     %s\n", data.get(n).toString());
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n"
                + "    Nice! I've marked this task as done: \n     " + data.get(n).toString() + "\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * Displays the first part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     * @return String result for GUI
     */
    public String printDeletePre(List<Task> data, int n) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.printf("     %s\n", data.get(n).toString());
        return "    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n     " + data.get(n).toString() + "\n";
    }

    /**
     * Displays the second part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     * @return String result for GUI
     */
    public String printDeletePost(List<Task> data, int n) {
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
        return "     Now you have " + data.size() + " tasks in the list.\n"
            + "    ____________________________________________________________";
    }

    /**
     * Displays the message to inform the success of adding todo event operation.
     * @param data the list of the events.
     * @param t the todo event that will be added.
     * @return String result for GUI
     */
    public String printTodo(List<Task> data, Todo t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * Displays the message to inform the success of adding deadline event operation.
     * @param data the list of the events.
     * @param t the deadline event that will be added.
     * @return String result for GUI
     */
    public String printDeadline(List<Task> data, Deadline t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * Displays the message to inform the success of adding event operation.
     * @param data the list of the events.
     * @param t the event that will be added.
     * @return String result for GUI
     */
    public String printEvent(List<Task> data, Event t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n";
    }

    /**
     * Displays the result of all the tasks that contains the keyword.
     * @param data the list of the events.
     * @return String result for GUI
     */
    public String printFind(List<Task> data) {
        String res = "";
        System.out.println("    ____________________________________________________________");
        res += "    ____________________________________________________________\n";
        res += "     Here are the matching tasks in your list:\n";
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
            res += "     " + (i + 1) + "." + data.get(i).toString() + "\n";
        }
        System.out.println("    ____________________________________________________________");
        res += "    ____________________________________________________________\n";
        return res;

    }
    /**
     * Displays the goodbye message.
     * @return String result for GUI
     */
    public String bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        return "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n";
    }
}
