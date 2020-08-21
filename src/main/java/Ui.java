import java.util.List;

public class Ui {
    private static final String SEPARATOR = "   ----------------------------------------------------------\n";

    protected static void printMsg(String msg) {
        System.out.print(SEPARATOR);
        System.out.printf("    %s\n", msg);
        System.out.println(SEPARATOR);
    }

    protected static void greetings() {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");
    }

    protected static void goodBye() {
        printMsg("Bye! Hope to see you again soon! ☺");
    }

    protected static void markTaskAsDone(Task current) {
        printMsg(String.format("Nice! I've marked this task as done:\n      %s", current));
    }

    protected static void deleteTask(Task current, int size) {
        printMsg(String.format("Noted. I've removed this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", current, size));
    }

    protected static void addTask(Task newTask, int size) {
        printMsg(String.format("Got it. I've added this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", newTask, size));
    }

    protected static void emptyTaskList() {
        printMsg("You currently have no tasks in the list.");
    }

    protected static void showTaskList(List<Task> tasks) {
        StringBuilder str1 = new StringBuilder();
        str1.append("Here are the tasks in your list:\n");
        int size = tasks.size();
        for (int i = 0; i < size - 1; i++) {
            str1.append(String.format("     %d.%s\n", i + 1, tasks.get(i)));
        }
        str1.append(String.format("     %d.%s", size, tasks.get(size - 1)));
        printMsg(str1.toString());
    }
}
