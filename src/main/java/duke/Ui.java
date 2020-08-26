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

    public void list(TaskList tasks) {
        printBorder();
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (Task task : tasks.getTasks()) {
            int index = tasks.getTasks().indexOf(task) + 1;
            System.out.println(INDENTATION + index + "." + task);
        }
        printBorder();
    }

    public void markDone(TaskList tasks, int index) {
        Task oldTask = tasks.getTasks().get(index);
        Task newTask = oldTask.markAsDone();
        tasks.replace(oldTask, newTask);

        printBorder();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }

    public void findMatching(TaskList taskList, String textToMatch) {
        printBorder();
        System.out.println(INDENTATION + "Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : taskList.getTasks()) {
            String description = task.getDescription();
            if (description.contains(textToMatch)) {
                System.out.println(INDENTATION + index + "." + task);
                index++;
            }
        }
        printBorder();
    }
}
