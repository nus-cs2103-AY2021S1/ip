import java.util.List;

/**
 * Encapsulates the user interaction.
 */
public class Ui {
    /**
     * Displays the loading error.
     */
    public void showLoadingError() {
        System.out.println("Failed to load the file.");
    }

    /**
     * Displays the number format error.
     */
    public void showNumberFormatError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! Please enter a number");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the potential errors when running the Duke bot.
     * @param e an exception object which contains the error message.
     */
    public void showDukeError(DukeException e) {
        System.out.println("    ____________________________________________________________");
        System.out.println(e.toString().substring(14));
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the file not found error.
     */
    public void showFileNotFoundError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! File is not found.");
        System.out.println("    ____________________________________________________________");
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
     */
    public void printList(List<Task> data) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the message to inform the success of Done operation.
     * @param data the list of the events.
     * @param n the label of the event that will set to be done.
     */
    public void printDone(List<Task> data, int n) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.printf("     %s\n", data.get(n).toString());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the first part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     */
    public void printDeletePre(List<Task> data, int n) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.printf("     %s\n", data.get(n).toString());
    }

    /**
     * Displays the second part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     */
    public void printDeletePost(List<Task> data, int n) {
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the message to inform the success of adding todo event operation.
     * @param data the list of the events.
     * @param t the todo event that will be added.
     */
    public void printTodo(List<Task> data, Todo t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the message to inform the success of adding deadline event operation.
     * @param data the list of the events.
     * @param t the deadline event that will be added.
     */
    public void printDeadline(List<Task> data, Deadline t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the message to inform the success of adding event operation.
     * @param data the list of the events.
     * @param t the event that will be added.
     */
    public void printEvent(List<Task> data, Event t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the goodbye message.
     */
    public void bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
