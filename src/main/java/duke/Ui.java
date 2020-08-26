package duke;

public class Ui {

    private static String BORDER = "-----------------------------------------------------------";
    private static String INDENTATION = "    ";

    public void printBorder() {
        System.out.println(INDENTATION + BORDER);
    }

    public void greet() {
        printBorder();
        System.out.println(INDENTATION + "Hello! I'm Duke\n    What can I do for you?");
        printBorder();
    }

    public void exit() {
        printBorder();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        printBorder();
    }

    public void list(TaskList taskList) {
        printBorder();
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            int index = taskList.getTasks().indexOf(task) + 1;
            System.out.println(INDENTATION + index + "." + task);
        }
        printBorder();
    }

    public void markDone(TaskList taskList, int index) {
        Task oldTask = taskList.getTasks().get(index);
        Task newTask = oldTask.markAsDone();
        taskList.replace(oldTask, newTask);

        printBorder();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }
}
