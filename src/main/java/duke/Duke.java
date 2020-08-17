package duke;

import exception.UnknownCommandException;
import exception.InvalidUsageException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    Scanner sc;
    String input;
    List<Task> todoList;

    public Duke() {
        sc = new Scanner(System.in);
        String input;
        todoList = new ArrayList<>();
    }

    public void start() {
        Duke.hello();
        handleInteraction();
        Duke.bye();
    }

    public void handleInteraction() {
        while (sc.hasNextLine()) {
            buildChatFence();
            input = sc.nextLine();
            // stopping condition
            if (input.equals("bye")) {
                break;
            }

            // handle commands
            try {
                String[] parsed = parseInput(input);
                String command = parsed[0];
                switch (command) {
                    case "list":
                        // we ignore the argument after `list`.
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < todoList.size(); i++) {
                            System.out.printf("     %d. %s%n", i + 1, todoList.get(i).showTask());
                        }
                        break;
                    case "done":
                        try {
                            int taskNumber = Integer.parseInt(parsed[1]) - 1;
                            // Check that the task number makes sense.
                            if (taskNumber >= 0 && taskNumber < todoList.size()) {
                                todoList.get(taskNumber).markAsDone();
                                System.out.println("     Good job! I've marked this task as done:");
                                System.out.printf("      %s%n", todoList.get(taskNumber).showTask());
                            } else {
                                System.out.println("     Sorry, I can't find it in your list!");
                            }
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                            throw new InvalidUsageException("Usage: done <task number>");
                        }
                        break;
                    case "todo":
                        try {
                            this.addTodo(parsed[1]);
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            throw new InvalidUsageException("The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        try {
                            this.addDeadline(parsed[1]);
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            throw new InvalidUsageException("The description of a deadline cannot be empty.");
                        }
                        break;
                    case "event":
                        try {
                            this.addEvent(parsed[1]);
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            throw new InvalidUsageException("The description of an event cannot be empty.");
                        }
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (InvalidUsageException | UnknownCommandException ex) {
                System.out.println(ex.getMessage());
            }
            buildChatFence();
        }
    }

    private static void hello() {
        buildChatFence();
        System.out.println("     Hellowww!! I'm Alexa, your personal todo manager!");
        System.out.println("     How can I help you today?");
        buildChatFence();
    }

    private static void bye() {
        buildChatFence();
        System.out.println("     Bye? I hope it's not forever! Come back soon!");
        buildChatFence();
    }

    private static void buildChatFence() {
        System.out.println("    ----------------------------------------");
    }

    private void printAddConfirmation(int index) {
        int size = todoList.size();
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s%n", todoList.get(index).showTask());
        System.out.printf("     Now you have %d %s in the list%n", size, size > 1 ? "tasks" : "task");
    }

    /**
     * Split the input string into first word (command) and others
     *
     * @param input user input
     * @return an array, first element is command, second element is input
     */
    private String[] parseInput(String input) {
        return input.split("\\W+", 2);
    }

    private void addTodo(String todo) {
        todoList.add(new Todo(todo));
        printAddConfirmation(todoList.size() - 1);
    }

    private void addDeadline(String deadline) throws InvalidUsageException {
        String[] parsedDeadline = deadline.split(" /by ", 2);
        if (parsedDeadline.length < 2) {
            String errorMsg = "Please ensure that the deadline description and date are not empty.\n"
                    + "     Usage: deadline <deadline description> /by <deadline date>";
            throw new InvalidUsageException(errorMsg);
        }
        todoList.add(new Deadline(parsedDeadline[0], parsedDeadline[1]));
        printAddConfirmation(todoList.size() - 1);
    }

    private void addEvent(String event) throws InvalidUsageException {
        String[] parsedEvent = event.split(" /at ", 2);
        if (parsedEvent.length < 2) {
            String errorMsg = "Please ensure that the event description and date are not empty.\n"
                    + "     Usage: event <event description> /at <event date>";
            throw new InvalidUsageException(errorMsg);
        }
        todoList.add(new Event(parsedEvent[0], parsedEvent[1]));
        printAddConfirmation(todoList.size() - 1);
    }
}

