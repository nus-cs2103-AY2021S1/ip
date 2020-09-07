import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Parser. The Parser class deals with making sense of the user command and running the command.
 */
public class Parser {
    /**
     * The user interface.
     */
    private static Ui ui;
    /**
     * The list of task.
     */
    private static TaskList tasks;
    /**
     * The storage object used.
     */
    private static Storage storage;

    /**
     * Creates a new <code>Parser</code> with the given <code>Ui</code>, <code>TaskList</code> and <code>Storage</code>.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Greets and runs the user command line by line.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printLogo();
        ui.greet();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            try {
                handleCommand(command);
            } catch (DukeException e) {
                ui.showCommandError(e);
            }
        }
    }

    /**
     * Deals with the user command.
     * @param command the command given by user.
     * @throws DukeException when the instruction has an invalid task type, or the task type is specified but content is empty.
     */
    public static void handleCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            ui.exit();
        } else if (command.equals("list")) {
            ui.printList(tasks.getTasks());
        } else if (command.split(" ")[0].equals("done")) {
            int index = Integer.parseInt(command.split(" ")[1]);
            tasks.done(index - 1);
            storage.record(tasks.getTasks());
        } else if (command.split(" ")[0].equals("delete")) {
            tasks.delete(Integer.parseInt(command.split(" ")[1]));
            storage.record(tasks.getTasks());
        } else if (command.split(" ")[0].equals("find")) {
            String keyword = command.replace("find ", "");
            tasks.find(keyword);
        } else if (command.split(" ")[0].equals("todo")) {
            String taskcommand = command.replace("todo", "");
            if (!taskcommand.equals("")) {
                tasks.add(new ToDo(taskcommand));
            } else {
                throw new DukeException("EmptyToDo");
            }
            storage.record(tasks.getTasks());
        } else if (command.split(" ")[0].equals("deadline")) {
            String taskcommand = command.split("/")[0].replace("deadline", "");
            if (!taskcommand.equals("")) {
                String time = command.split("/")[1].replace("by ", "");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                tasks.add(new Deadline(taskcommand, LocalDateTime.parse(time, formatter)));
            } else {
                throw new DukeException("EmptyDeadline");
            }
            storage.record(tasks.getTasks());
        } else if (command.split(" ")[0].equals("event")) {
            String taskcommand = command.split("/")[0].replace("event", "");
            if (!taskcommand.equals("")) {
                String time = command.split("/")[1].replace("at ", "");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                tasks.add(new Deadline(taskcommand, LocalDateTime.parse(time, formatter)));
            } else {
                throw new DukeException("EmptyEvent");
            }
            storage.record(tasks.getTasks());
        } else {
            throw new DukeException("invalid");
        }
    }
}