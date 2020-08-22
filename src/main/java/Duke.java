import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final String line = "--------------------------------------------------";
    private final String stringFormat = line + "\n%s\n" + line + "\n\n";

    private Scanner scanner;
    private List<Task> list;

    private Duke(List<Task> list) {
        this.scanner = new Scanner(System.in);
        this.list = list;
        greet();
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.printf(stringFormat, "Hello! I'm Duke\nWhat can I do for you?");
    }

    private List<Task> getTaskList() {
        return this.list;
    }

    private void processBye() {
        this.scanner.close();
        System.out.printf(stringFormat, "Bye. Hope to see you again soon!");
    }

    private void processAdd(Task task) {
        this.list.add(task);
        String content = String.format("Got it. I've added this task:\n" +
                        "\t%1$s\n" +
                        "Now you have %2$d tasks in the list.",
                task.toString(), this.list.size());
        System.out.printf(stringFormat, content);
    }

    private void processList() {
        if (this.list.size() < 1) {
            System.out.printf(stringFormat, "There is nothing in the list!!");
            return;
        }
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            content.append(i + 1);
            content.append(". ");
            content.append(this.list.get(i).toString());
            if (i < this.list.size() - 1) {
                content.append("\n");
            }
        }
        System.out.printf(stringFormat, content.toString());
    }

    private void processDone(String action) throws TaskNumberNotFoundException {
        String[] splitKeys = action.split(" ");
        if (splitKeys.length < 2) {
            throw new TaskNumberNotFoundException("☹ OOPS!!! Please enter a task number after 'done'.");
        }

        int taskIndex = Integer.parseInt(splitKeys[1]);
        if (taskIndex > this.list.size() || taskIndex < 1) {
            throw new TaskNumberNotFoundException("☹ OOPS!!! The task number is not valid.");
        }
        Task task = this.list.get(taskIndex - 1);
        task.markAsDone();
        System.out.printf(stringFormat, "Nice! I've marked this task as done:\n\t" + task.toString());
    }

    private void processDelete(String action) throws TaskNumberNotFoundException {
        String[] splitKeys = action.split(" ");
        if (splitKeys.length < 2) {
            throw new TaskNumberNotFoundException("☹ OOPS!!! Please enter a task number after 'delete'.");
        }

        int taskIndex = Integer.parseInt(splitKeys[1]);
        if (taskIndex > this.list.size() || taskIndex < 1) {
            throw new TaskNumberNotFoundException("☹ OOPS!!! The task number is not valid.");
        }
        Task task = this.list.get(taskIndex - 1);
        this.list.remove(taskIndex - 1);
        String content = String.format("Got it. I've deleted this task:\n" +
                        "\t%1$s\n" +
                        "Now you have %2$d tasks in the list.",
                task.toString(), this.list.size());
        System.out.printf(stringFormat, content);
    }

    private void createTodo(String action) throws TaskDescriptionNotFoundException {
        if (action.length() < 6) throw new TaskDescriptionNotFoundException("☹ OOPS!!! The description of a todo cannot be empty.");
        String description = new StringBuilder(action).substring(5);
        Todo newTodo = new Todo(description);
        this.processAdd(newTodo);
    }

    private void createDeadline(String action) throws TaskDetailsNotFound, TaskDescriptionNotFoundException {
        if (action.length() < 10) throw new TaskDescriptionNotFoundException("☹ OOPS!!! The description of a deadline cannot be empty.");
        if (!action.contains(" /by ")) {
            throw new TaskDetailsNotFound("Invalid format, missing '/by' key or deadline!!");
        }
        String[] taskDetails = new StringBuilder(action).substring(9).split(" /by ");
        if (taskDetails[0].length() < 1) {
            throw new TaskDescriptionNotFoundException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (taskDetails.length < 2) {
            throw new TaskDetailsNotFound("☹ OOPS!!! The deadline cannot be empty.");
        }
        Deadline newDeadline = new Deadline(taskDetails[0], taskDetails[1]);
        this.processAdd(newDeadline);
    }

    private void createEvent(String action) throws TaskDetailsNotFound, TaskDescriptionNotFoundException {
        if (action.length() < 7) throw new TaskDescriptionNotFoundException("☹ OOPS!!! The description of an event cannot be empty.");
        if (!action.contains(" /at ")) {
            throw new TaskDetailsNotFound("Invalid format, missing '/at' key or event date!!");
        }
        String[] taskDetails = new StringBuilder(action).substring(6).split(" /at ");
        if (taskDetails[0].length() < 1) {
            throw new TaskDescriptionNotFoundException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (taskDetails.length < 2) {
            throw new TaskDetailsNotFound("☹ OOPS!!! The event date cannot be empty.");
        }
        Event newEvent = new Event(taskDetails[0], taskDetails[1]);
        this.processAdd(newEvent);
    }

    private boolean processAction() {
        try {
            System.out.println("Type an action (list, todo [description], deadline [description /by date], event [description /at date], done [task number], delete [task number], bye):");
            String action = this.scanner.nextLine();
            String key = action.split(" ")[0];
            switch (key) {
                case "bye":
                    this.processBye();
                    return false;
                case "list":
                    this.processList();
                    break;
                case "done":
                    this.processDone(action);
                    break;
                case "delete":
                    this.processDelete(action);
                    break;
                case "todo":
                    this.createTodo(action);
                    break;
                case "deadline":
                    this.createDeadline(action);
                    break;
                case "event":
                    this.createEvent(action);
                    break;
                default:
                    throw new UnknownActionException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception err) {
            System.out.printf(stringFormat, err.getMessage());
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            String pathName = "src/data/duke.txt";
            FileClass file = new FileClass(pathName);
            List<Task> list = file.readFromFile();
            Duke duke = new Duke(list);
            boolean status;
            do {
                status = duke.processAction();
            } while (status);
            file.writeToFile(duke.getTaskList());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}