import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * 1-indexed list
     */
    private final List<Task> tasks;
    private static final String OUTPUT_FILE_NAME = "duke.txt";

    public Duke() {
        this.tasks = new ArrayList<>();
        tasks.add(null);
    }

    private static String getTaskDescription(Command type, String cmd) throws DukeException {
        String[] cmdParts = cmd.split(type.toString() + " ", 2);

        if (cmdParts.length != 2 || cmdParts[1].equals("")) {
            throw new DukeException(String.format("Error! The description of a %s cannot be empty.", type));
        }

        return cmdParts[1];
    }

    private int getTaskTargetIndex(Command type, String cmd) throws DukeException {
        String description = Duke.getTaskDescription(type, cmd);
        try {
            int index = Integer.parseInt(description);

            if (index == 0 || index > this.tasks.size() - 1) {
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

            return index;
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Input format is wrong. Please make sure it is `%s <task-index>`", type));
        }
    }

    private int getNumberOfTasks() {
        return this.tasks.size() - 1;
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String list() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < this.tasks.size(); i++) {
            output.append(String.format("%d. %s%n", i, this.tasks.get(i)));
        }
        return output.toString();
    }

    public void printList() {
        System.out.println(this.list());
    }

    public void saveList() {
        String projectDir = System.getProperty("user.dir");
        Path dataDir = Paths.get(projectDir, "data");
        try {
            if (!Files.exists(dataDir)) {
                Files.createDirectory(dataDir);
            }
            Path filePath = Paths.get(dataDir.toString(), Duke.OUTPUT_FILE_NAME);
            Files.createFile(filePath);
            Files.writeString(filePath, this.list());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void bye() {
        System.out.println("Bye! Hope to never see you again!");
    }

    public void done(String cmd) throws DukeException {
        int index = this.getTaskTargetIndex(Command.DONE, cmd);
        Task task = this.tasks.get(index);
        task.setDone(true);
        System.out.printf("Good job. This task is marked as done:\n %s%n", task);
    }

    public void delete(String cmd) throws DukeException {
        int index = this.getTaskTargetIndex(Command.DELETE, cmd);
        Task task = this.tasks.remove(index);
        System.out.printf("Noted. I've remove this task:\n %s%n", task);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.printf("Got it. I've added this task:%n %s%n", task);
    }

    public void event(String cmd) throws DukeException {
        String description = Duke.getTaskDescription(Command.EVENT, cmd);
        String[] taskParts = description.split(" /at ", 2);

        if (taskParts.length != 2 || taskParts[1].equals("")) {
            throw new DukeException(String.format("Error! The description of a %s is missing a date.", Command.EVENT));
        }

        String name = taskParts[0];
        String date = taskParts[1];
        this.addTask(new Event(name, date));
    }

    public void deadline(String cmd) throws DukeException {
        String description = Duke.getTaskDescription(Command.DEADLINE, cmd);
        String[] taskParts = description.split(" /by ", 2);

        if (taskParts.length != 2 || taskParts[1].equals("")) {
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
                    duke.printList();
                } else if (Command.DONE.is(cmd)) {
                    duke.done(cmd);
                } else if (Command.TODO.is(cmd)) {
                    duke.todo(cmd);
                } else if (Command.DEADLINE.is(cmd)) {
                    duke.deadline(cmd);
                } else if (Command.EVENT.is(cmd)) {
                    duke.event(cmd);
                } else if (Command.DELETE.is(cmd)) {
                    duke.delete(cmd);
                } else {
                    throw new DukeException("This command is invalid.");
                }
                duke.saveList();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            cmd = sc.nextLine();
        }

        duke.bye();
        sc.close();
    }
}
