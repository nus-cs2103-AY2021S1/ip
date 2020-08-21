import exceptions.DukeException;
import exceptions.EmptyBodyException;
import exceptions.NoSuchTaskException;
import exceptions.WrongSyntaxException;
import exceptions.UnknownCommandException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.lang.StringBuilder;

public class Duke {

    private final String lineSeparator = "***********************";
    private final Storage store = new Storage();
    private List<Task> taskList = new ArrayList<>();

    public void greet() {
        printMessage("Hi! I'm Duke. What can I do for you?");
    }

    public void printMessage(String msg) {
        System.out.println(lineSeparator);
        System.out.println(msg);
        System.out.println(lineSeparator);
    }

    public void addTask(Task task) {
        taskList.add(task);
        printMessage(String.format("added: %s \n Now you have %d tasks in the list", task, taskList.size()));
    }

    public void completeTask(int taskNumber) {
        Task completedTask = taskList.get(taskNumber - 1).markCompleted();
        taskList.set(taskNumber - 1, completedTask);
        printMessage(String.format("Nice! I've marked this task as done: \n %s", completedTask.toString()));
    }

    public void deleteTask(int taskNumber) {
        Task toRemove = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        printMessage(String.format("Noted. I've removed this task: \n %s", toRemove.toString()));
    }

    public void printList() {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        printMessage(tasks.toString());
    }

    public boolean handleInput(Scanner scanner) throws DukeException {
        String userInput = scanner.next();
        Command command = Command.getCommand(userInput);
        boolean toContinue = true;
        boolean updateRequired = true;
        switch (command) {
        case BYE:
            printMessage("Bye! Hope to see you soon!");
            toContinue = false;
            updateRequired = false;
            break;
        case LIST:
            printList();
            updateRequired = false;
            break;
        case DONE:
            int taskNumber = scanner.nextInt();
            if (taskNumber < 1 || taskNumber > taskList.size()) {
                throw new NoSuchTaskException();
            }
            completeTask(taskNumber);
            break;
        case DELETE:
            int toDelete = scanner.nextInt();
            if (toDelete < 1 || toDelete > taskList.size()) {
                throw new NoSuchTaskException();
            }
            deleteTask(toDelete);
            break;
        case DEADLINE:
            String deadlineCommand = scanner.nextLine().trim();
            String[] deadlineParts = deadlineCommand.split(" /by ");
            if (deadlineParts.length != 2) {
                throw new WrongSyntaxException();
            }
            addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
            break;
        case EVENT:
            String eventCommand = scanner.nextLine().trim();
            String[] eventParts = eventCommand.split(" /at ");
            if (eventParts.length != 2) {
                throw new WrongSyntaxException();
            }
            addTask(new Event(eventParts[0], eventParts[1]));
            break;
        case TODO:
            String task = scanner.nextLine().trim();
            if (task.isBlank()) {
                throw(new EmptyBodyException());
            }
            addTask(new Todo(task));
            break;
        case UNKNOWN:
            // unknown command, skip the entire line
            scanner.nextLine();
            throw(new UnknownCommandException(userInput));
        default:
            scanner.nextLine();
        }

        if (updateRequired) {
            store.updateTasks(taskList);
        }
        return toContinue;
    }

    public void start() {
        greet();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            try {
                store.initializeStorage();
                taskList = store.getTasks();
                isRunning = handleInput(scanner);
            } catch (IOException e) {
                System.out.println("Error connecting to storage, actions made will not be saved");
            } catch (DukeException e) {
                printMessage(e.getFriendlyMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();        duke.start();
    }
}
