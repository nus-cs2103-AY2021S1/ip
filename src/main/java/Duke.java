import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private List<Task> taskList;
    private String logo, greetingMessage, exitMessage;

    public void initialise() {
        scanner = new Scanner(System.in);
        taskList = new ArrayList<>();
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greetingMessage = "Hello! My name is Duke.\n" + "What can I do for you?";
        exitMessage = "Bye. Hope to see you again soon!";
        greet();
        listenForCommands();
    }

    private void greet() {
        System.out.println(logo);
        System.out.println(greetingMessage);
    }

    private void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int size = taskList.size();
        for (int i = 0; i < size; i++) {
            int taskNumber = i + 1;
            String entry = String.format("%d. %s", taskNumber, taskList.get(i));
            System.out.println(entry);
        }
    }

    private void addTask(Task task) {
        taskList.add(task);
        int size = taskList.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        String taskWord = (size > 1 ? "tasks" : "task");
        System.out.println("Now you have " + size + " " + taskWord + " in the list.");
    }

    private void markTaskAsDone(int taskNumber) {
        int index = taskNumber - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(task);
    }

    private void handleDoneCommand(String input) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input);
            markTaskAsDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! The task number you entered does not exist.");
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
            int index = taskNumber - 1;
            Task task = taskList.get(index);
            taskList.remove(index);
            System.out.println("Got it. I've removed this task:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error! The task number you entered does not exist.");
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Please enter a valid task number.");
        }
    }

    private void exit() {
        System.out.println(exitMessage);
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
            System.out.println(e.getMessage());
        }
        listenForCommands();
    }
}