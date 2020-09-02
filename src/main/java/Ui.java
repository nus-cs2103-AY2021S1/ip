import java.util.List;

public class Ui {
    public void showLoadingError() {
        System.out.println("Failed to load the file.");
    }
    public void showNumberFormatError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! Please enter a number");
        System.out.println("    ____________________________________________________________");
    }
    public void showDukeError(DukeException e) {
        System.out.println("    ____________________________________________________________");
        System.out.println(e.toString().substring(14));
        System.out.println("    ____________________________________________________________");
    }
    public void showFileNotFoundError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     ☹ OOPS!!! File is not found.");
        System.out.println("    ____________________________________________________________");
    }
    public void welcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }
    public void printList(List<Task> data) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }
    public void printDone(List<Task> data, int n) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.printf("     %s\n", data.get(n).toString());
        System.out.println("    ____________________________________________________________");
    }
    public void printDeletePre(List<Task> data, int n) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.printf("     %s\n", data.get(n).toString());
    }
    public void printDeletePost(List<Task> data, int n) {
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }
    public void printTodo(List<Task> data, Todo t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }
    public void printDeadline(List<Task> data, Deadline t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }
    public void printEvent(List<Task> data, Event t) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", t.toString());
        System.out.printf("     Now you have %d tasks in the list.\n", data.size());
        System.out.println("    ____________________________________________________________");
    }
    public void bye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
