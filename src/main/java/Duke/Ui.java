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
    private String line = "    ____________________________________________________________";
    public String showLoadingError() {
        System.out.println("Failed to load the file.");
        return "Failed to load the file.\n";
    }

    /**
     * Displays the number format error.
     * @return String result for GUI
     */
    public String showNumberFormatError() {
        System.out.println(line);
        System.out.println("      OOPS!!! Please enter a number");
        System.out.println(line);
        return line + "\n      OOPS!!! Please enter a number\n" + line + "\n";
    }

    /**
     * Displays the potential error when running the Duke bot.
     * @param e an exception object which contains the error message.
     *          @return String result for GUI
     */
    public String showDukeError(DukeException e) {
        System.out.println(line);
        System.out.println(e.toString().substring(19));
        System.out.println(line);
        return line + "\n" + e.toString().substring(19) + "\n" + line + "\n";
    }

    /**
     * Displays the file not found error.
     * @return String result for GUI
     */
    public String showFileNotFoundError() {
        System.out.println(line);
        System.out.println("      OOPS!!! File is not found.");
        System.out.println(line);
        return line + "\n      OOPS!!! File is not found.\n" + line + "\n";
    }

    /**
     * Displays the welcome message.
     */
    public void welcome() {
        System.out.println(line);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(line);
    }

    /**
     * Displays the list of the todo events.
     * @param data the list of the todo events.
     * @return String result for GUI
     */
    public String printList(List<Task> data) {
        String res = "";
        System.out.println(line);
        res += line + "\n";
        System.out.println("     Here are the tasks in your list:");
        res += "     Here are the tasks in your list:\n";
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
            res += "     " + (i + 1) + "." + data.get(i).toString() + "\n";
        }
        System.out.println(line);
        res += line + "\n";
        return res;
    }

    /**
     * Displays the message to inform the success of Done operation.
     * @param data the list of the events.
     * @param n the label of the event that will set to be done.
     * @return String result for GUI
     */
    public String printDone(List<Task> data, int n) {
        System.out.println(line);
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.printf("     %s\n", data.get(n).toString());
        System.out.println(line);
        return line + "\n" + "    Nice! I've marked this task as done: \n     "
                + data.get(n).toString() + "\n" + line + "\n";
    }

    /**
     * Displays the first part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     * @return String result for GUI
     */
    public String printDeletePre(List<Task> data, int n) {
        System.out.println(line);
        System.out.println("     Noted. I've removed this task: ");
        System.out.printf("     %s\n", data.get(n).toString());
        return line + "\n"
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
        System.out.println(line);
        return "     Now you have " + data.size() + " tasks in the list.\n"
            + line + "\n";
    }

    /**
     * Displays the message to inform the success of adding todo event operation.
     * @param data the list of the events.
     * @param t the todo event that will be added.
     * @return String result for GUI
     */
    public String printTodo(List<Task> data, Todo t) {
        System.out.println(line);
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println(line);
        return line + "\n"
                + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n"
                + line + "\n";
    }

    /**
     * Displays the message to inform the success of adding deadline event operation.
     * @param data the list of the events.
     * @param t the deadline event that will be added.
     * @return String result for GUI
     */
    public String printDeadline(List<Task> data, Deadline t) {
        System.out.println(line);
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println(line);
        return line + "\n" + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n" + line + "\n";
    }

    /**
     * Displays the message to inform the success of adding event operation.
     * @param data the list of the events.
     * @param t the event that will be added.
     * @return String result for GUI
     */
    public String printEvent(List<Task> data, Event t) {
        System.out.println(line);
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println(line);
        return line + "\n" + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n" + line + "\n";
    }

    /**
     * Displays the result of all the tasks that contains the keyword.
     * @param data the list of the events.
     * @return String result for GUI
     */
    public String printFind(List<Task> data) {
        String res = "";
        System.out.println(line);
        res += line + "\n";
        res += "     Here are the matching tasks in your list:\n";
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
            res += "     " + (i + 1) + "." + data.get(i).toString() + "\n";
        }
        System.out.println(line);
        res += line + "\n";
        return res;

    }
    /**
     * Displays the goodbye message.
     * @return String result for GUI
     */
    public String bye() {
        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
        return line + "\n" + "     Bye. Hope to see you again soon!\n" + line + "\n";
    }
}
