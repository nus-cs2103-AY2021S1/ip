import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * 1-indexed list
     */
    private List<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
        tasks.add(null);
    }

    private int getNumberOfTasks() {
        return this.tasks.size() - 1;
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void list() {
        for (int i = 1; i < this.tasks.size(); i++) {
            System.out.printf("%d. %s%n", i, this.tasks.get(i));
        }
    }

    public void bye() {
        System.out.println("Bye! Hope to never see you again!");
    }

    public void done(String cmd) {
        String[] cmdParts = cmd.split(" ");
        try {
            this.done(Integer.parseInt(cmdParts[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.print("Input format is wrong. Please make sure it is `done <task-index>`");
        }
    }

    public void done(int index) {
        try {
            if (index == 0) {
                // Task list is 1-indexed, 0 is an out-of-bound value
                throw new IndexOutOfBoundsException();
            }

            Task task = this.tasks.get(index);
            task.setDone(true);
            System.out.printf("Good job. This task is marked as done:\n %s%n", task);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Task at index %d doesn't exist%n", index);
            if (this.tasks.size() == 0) {
                System.out.println("There are no tasks currently.");
            } else {
                System.out.printf(
                        "There are %d tasks currently. Please a number between 1 and %d inclusive%n",
                        this.getNumberOfTasks(), this.getNumberOfTasks());
            }
        }
    }

    public void event(String cmd) {
    }

    public void deadline(String cmd) {
    }

    public void todo(String cmd) {
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (!Command.BYE.is(cmd)) {
            if (Command.LIST.is(cmd)) {
                duke.list();
            } else if (Command.DONE.is(cmd)) {
                duke.done(cmd);
            } else if (Command.TODO.is(cmd)) {
                duke.todo(cmd);
            } else if (Command.DEADLINE.is(cmd)) {
                duke.deadline(cmd);
            } else if (Command.EVENT.is(cmd)) {
                duke.event(cmd);
            }

            cmd = sc.nextLine();
        }

        duke.bye();
        sc.close();
    }
}
