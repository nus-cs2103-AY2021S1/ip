import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * 1-indexed list
     */
    private final List<Task> tasks;

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

    public void done(String cmd) throws DukeException {
        String[] cmdParts = cmd.split(" ");
        try {
            this.done(Integer.parseInt(cmdParts[1]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Input format is wrong. Please make sure it is `done <task-index>`");
        }
    }

    public void done(int index) throws DukeException {
        try {
            if (index == 0) {
                // Task list is 1-indexed, 0 is an out-of-bound value
                throw new IndexOutOfBoundsException();
            }

            Task task = this.tasks.get(index);
            task.setDone(true);
            System.out.printf("Good job. This task is marked as done:\n %s%n", task);
        } catch (IndexOutOfBoundsException e) {
            StringBuilder message = new StringBuilder(String.format("Task at index %d doesn't exist%n", index));
            if (this.tasks.size() == 0) {
                message.append("\nThere are no tasks currently.");
            } else {
                message.append(String.format(
                        "There are %d tasks currently. Please a number between 1 and %d inclusive.",
                        this.getNumberOfTasks(), this.getNumberOfTasks()));
            }

            throw new DukeException(message.toString());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.printf("Got it. I've added this task:%n %s%n", task);
    }

    public static String getTaskDescription(Command type, String cmd) throws DukeException {
        String[] cmdParts = cmd.split(type.toString() + " ", 2);

        if (cmdParts.length != 2 || cmdParts[1].equals("")) {
            throw new DukeException(String.format("Error! The description of a %s cannot be empty.", type));
        }

        return cmdParts[1];
    }

    public void event(String cmd) throws DukeException {
        String description = Duke.getTaskDescription(Command.EVENT, cmd);
        String[] taskParts = description.split(" /at ", 2);

        if (taskParts.length != 2) {
            throw new DukeException(String.format("Error! The description of a %s is missing a date.", Command.EVENT));
        }

        String name = taskParts[0];
        String date = taskParts[1];
        this.addTask(new Event(name, date));
    }

    public void deadline(String cmd) throws DukeException {
        String description = Duke.getTaskDescription(Command.DEADLINE, cmd);
        String[] taskParts = description.split(" /by ", 2);

        if (taskParts.length != 2) {
            throw new DukeException(String.format("Error! The description of a %s is missing a date.", Command.DEADLINE));
        }

        String name = taskParts[0];
        String date = taskParts[1];
        this.addTask(new Deadline(name, date));
    }

    public void todo(String cmd) throws DukeException {
        String description = Duke.getTaskDescription(Command.TODO, cmd);
        this.addTask(new ToDo(description));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (!Command.BYE.is(cmd)) {
            try {
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
                } else {
                    throw new DukeException("This command is invalid.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            cmd = sc.nextLine();
        }

        duke.bye();
        sc.close();
    }
}
