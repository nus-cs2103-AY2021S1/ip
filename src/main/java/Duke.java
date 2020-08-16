import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private List<Task> taskList;
    private String logo, exitMessage;

    public void initialise() {
        scanner = new Scanner(System.in);
        taskList = new ArrayList<>();
        logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        greet();
        listenForCommands();
    }
    
    private void sendMessage(List<String> messages) {
        System.out.println("\t____________________________________________________________");
        for (String message : messages) {
            System.out.println("\t " + message);
        }
        System.out.println("\t____________________________________________________________");
    }

    private void greet() {
        System.out.printf(logo);
        List<String> messages = new ArrayList<>();
        messages.add("Hello! My name is Duke.");
        messages.add("What can I do for you?");
        sendMessage(messages);
    }

    private void listAllTasks() {
        List<String> messages = new ArrayList<>();
        messages.add("Here are the tasks in your list:");
        int size = taskList.size();
        for (int i = 0; i < size; i++) {
            int taskNumber = i + 1;
            String entry = String.format("%d. %s", taskNumber, taskList.get(i));
            messages.add(entry);
        }
        sendMessage(messages);
    }

    private void addTask(Task task) {
        taskList.add(task);
        int size = taskList.size();
        String taskWord = (size > 1 ? "tasks" : "task");
        List<String> messages = new ArrayList<>();
        messages.add("Got it. I've added this task:");
        messages.add("  " + task);
        messages.add("Now you have " + size + " " + taskWord + " in the list.");
        sendMessage(messages);
    }

    private void markTaskAsDone(int taskNumber) {
        int index = taskNumber - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        List<String> messages = new ArrayList<>();
        messages.add("Nice! I have marked this task as done:");
        messages.add("  " + task);
        sendMessage(messages);
    }
    
    private void deleteTask(int taskNumber) {
        int index = taskNumber - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        List<String> messages = new ArrayList<>();
        messages.add("Got it. I've removed this task:");
        messages.add("  " + task);
        sendMessage(messages);
    }

    private void handleDoneCommand(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input);
            markTaskAsDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! The task number entered does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        }
    }
    
    private void handleTodoCommand(String input) {
        Todo todo = new Todo(input);
        addTask(todo);
    }
    
    private void handleDeadlineCommand(String input) throws DukeException {
        String[] inputBreakdown = input.split(" /by ", 2);
        if (inputBreakdown.length < 2) {
            throw new DukeException("Error! Note the syntax: deadline [description] /by [date]");
        } else {
            String description = inputBreakdown[0];
            String by = inputBreakdown[1];
            Deadline deadline = new Deadline(description, by);
            addTask(deadline);
        }
    }
    
    private void handleEventCommand(String input) throws DukeException {
        String[] inputBreakdown = input.split(" /at ", 2);
        if (inputBreakdown.length < 2) {
            throw new DukeException("Error! Note the syntax: event [description] /at [date]");
        } else {
            String description = inputBreakdown[0];
            String at = inputBreakdown[1];
            Event event = new Event(description, at);
            addTask(event);
        }
    }
    
    private void handleDeleteCommand(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input);
            deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! The task number entered does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        }
    }

    private void exit() {
        List<String> messages = new ArrayList<>();
        messages.add("Bye. Hope to see you again soon!");
        sendMessage(messages);
        System.exit(0);
    }

    private void listenForCommands() {
        String input = scanner.nextLine();
        String[] inputBreakdown = input.split(" ", 2);
        String command = inputBreakdown[0];
        try {
            switch (command) {
                case ("list"):
                    listAllTasks();
                    break;
                case ("done"):
                    if (inputBreakdown.length < 2) {
                        throw new DukeException("Error! Note the syntax: done [task number]");
                    } else {
                        handleDoneCommand(inputBreakdown[1]);
                    }
                    break;
                case ("todo"):
                    if (inputBreakdown.length < 2) {
                        throw new DukeException("Error! Note the syntax: todo [description]");
                    } else {
                        handleTodoCommand(inputBreakdown[1]);
                    }
                    break;
                case ("deadline"):
                    if (inputBreakdown.length < 2) {
                        throw new DukeException("Error! Note the syntax: deadline [description] /by [date]");
                    } else {
                        handleDeadlineCommand(inputBreakdown[1]);
                    }
                    break;
                case ("event"):
                    if (inputBreakdown.length < 2) {
                        throw new DukeException("Error! Note the syntax: event [description] /at [date]");
                    } else {
                        handleEventCommand(inputBreakdown[1]);
                    }
                    break;
                case ("delete"):
                    if (inputBreakdown.length < 2) {
                        throw new DukeException("Error! Note the syntax: delete [task number]");
                    } else {
                        handleDeleteCommand(inputBreakdown[1]);
                    }
                    break;
                case ("bye"):
                    exit();
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means \u2639");
            }
        } catch (DukeException e) {
            List<String> messages = new ArrayList<>();
            messages.add(e.getMessage());
            sendMessage(messages);
        }
        listenForCommands();
    }
}