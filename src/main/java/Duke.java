import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
    
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet();
        
        boolean shouldLoop = true;
        while (shouldLoop) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseInput(input);
                switch (command) {
                case BYE:
                    handleBye();
                    shouldLoop = false;
                    break;
                case COMMANDS:
                    handleCommands();
                    break;
                case DEADLINE:
                    handleDeadline(input);
                    break;
                case DELETE:
                    handleDelete(input);
                    break;
                case DONE:
                    handleDone(input);
                    break;
                case EVENT:
                    handleEvent(input);
                    break;
                case LIST:
                    handleList();
                    break;
                case TODO:
                    handleToDo(input);
                    break;
                case UNKNOWN:
                    handleUnknown();
                    break;
                default:
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }
    
    private void handleBye() {
        ui.printResponse("Bye. Hope to see you again soon!");
    }

    private void handleCommands() {
        String response = "Here are all the commands:\n\n"
                + "bye\n"
                + "deadline <task> /by <time>\n"
                + "delete <task index>\n"
                + "done <task index>\n"
                + "event <task> /at <time>\n"
                + "list\n"
                + "todo <task>";
        ui.printResponse(response);
    }

    private void handleDeadline(String command) throws InvalidDeadlineException {
        try {
            String[] input = command.substring(command.indexOf(' ') + 1).split(" /by ");
            String description = input[0];
            String byString = input[1];
            Date by = DateFormatter.extractTimestampInput(byString);
            Task task = new Deadline(description, by);
            tasks.add(task);
            storage.save(tasks);
            String response = String.format(
                    "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                    task, tasks.size()
            );
            ui.printResponse(response);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            throw new InvalidDeadlineException();
        }
    }

    private void handleDelete(String command) throws InvalidDeleteIndexException {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size() || index < 1) {
            throw new InvalidDeleteIndexException(tasks.size());
        }

        Task task = tasks.remove(index-1);
        storage.save(tasks);
        String response = String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.", task, tasks.size());
        ui.printResponse(response);
    }

    private void handleDone(String command) throws InvalidDoneIndexException {
        int index = Integer.parseInt(command.split(" ")[1]);
        if (index > tasks.size() || index < 1) {
            throw new InvalidDoneIndexException(tasks.size());
        }

        Task task = tasks.get(index-1);
        task.completeTask();
        storage.save(tasks);
        String response = "Nice! I've marked this task as done:\n  " + task.toString();
        ui.printResponse(response);
    }

    private void handleEvent(String command) throws InvalidEventException {
        try {
            String[] input = command.substring(command.indexOf(' ') + 1).split(" /at ");
            String description = input[0];
            String atString = input[1];
            Date at = DateFormatter.extractTimestampInput(atString);
            Task task = new Event(description, at);
            tasks.add(task);
            storage.save(tasks);
            String response = String.format(
                    "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                    task, tasks.size()
            );
            ui.printResponse(response);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            throw new InvalidEventException();
        }
    }

    private void handleList() {
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.add(String.format("%d.%s", i+1, tasks.get(i)));
        }
        ui.printResponse(response.toString());
    }

    private void handleToDo(String command) throws InvalidToDoException {
        String[] split = command.split(" ");
        if (split.length == 1) {
            throw new InvalidToDoException();
        }

        String[] descriptionArray = Arrays.copyOfRange(split, 1, split.length);
        String description = String.join(" ", descriptionArray);
        Task task = new ToDo(description);
        tasks.add(task);
        storage.save(tasks);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        ui.printResponse(response);
    }

    private void handleUnknown() throws DukeException{
        throw new DukeException();
    }
}
