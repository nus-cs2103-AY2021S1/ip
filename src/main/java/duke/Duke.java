package duke;

import exception.*;
import storage.Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duke {
    Scanner sc;
    String input;
    TaskList taskList;
    Storage storage = new Storage();

    public Duke() {
        sc = new Scanner(System.in);
        String input;
    }

    public void start() {
        try {
            this.taskList = new TaskList(storage.load());
            Duke.hello();
            handleInteraction();
            Duke.bye();
        } catch (StorageException ex) {
            ex.printStackTrace();
        }
    }

    

    // interaction functions
    public void handleInteraction() {
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            // stopping condition
            if (input.equals("bye")) {
                break;
            }

            buildChatFence();

            // handle commands
            try {
                String[] parsed = parseInput(input);
                Commands command;

                try {
                    // modify parsed[0] to uppercase to ensure that comparison with the commands enum is standardized
                    command = Commands.valueOf(parsed[0].toUpperCase());
                } catch (IllegalArgumentException ex) {
                    // if parsed[0] not amongst valid commands, will throw an IllegalArgumentException
                    throw new UnknownCommandException();
                }

                switch (command) {
                case LIST:
                    // we ignore the argument after `list`.
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.printf("     %d. %s%n", i + 1, taskList.show(i));
                    }
                    break;
                case DONE:
                    try {
                        int taskNumber = Integer.parseInt(parsed[1]) - 1;
                        // Check that the task number makes sense.
                        if (taskNumber >= 0 && taskNumber < taskList.size()) {
                            taskList.markAsDone(taskNumber);
                            storage.save(this.taskList);
                            System.out.println("     Good job! I've marked this task as done:");
                            System.out.printf("      %s%n", taskList.show(taskNumber));
                        } else {
                            System.out.println("     Sorry, I can't find it in your list!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new InvalidUsageException("Usage: done <task number>");
                    }
                    break;
                case DELETE:
                    try {
                        int taskNumber = Integer.parseInt(parsed[1]) - 1;
                        // Check that the task number makes sense.
                        if (taskNumber >= 0 && taskNumber < taskList.size()) {
                            System.out.println("     Noted. I've removed this task: ");
                            System.out.printf("      %s%n", taskList.remove(taskNumber).showTask());
                            System.out.printf("     Now you have %d %s in the list%n",
                                    taskList.size(), taskList.size() > 1 ? "tasks" : "task");
                            storage.save(this.taskList);
                        } else {
                            System.out.println("     Sorry, I can't find it in your list!");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new InvalidUsageException("Usage: delete <task number>");
                    }
                    break;
                case TODO:
                    try {
                        this.addTodo(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new TodoInvalidUsageException("Todo description cannot be empty.");
                    }
                    break;
                case DEADLINE:
                    try {
                        this.addDeadline(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DeadlineInvalidUsageException("Deadline description cannot be empty.");
                    }
                    break;
                case EVENT:
                    try {
                        this.addEvent(parsed[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new EventInvalidUsageException("Event description cannot be empty.");
                    }
                    break;
                case VIEWALL:
                    try {
                        TaskList filtered = taskList.viewAll(parsed[1]);
                        System.out.println("     Here are the tasks on given date:");
                        for (int i = 0; i < filtered.size(); i++) {
                            System.out.printf("     %d. %s%n", i + 1, filtered.show(i));
                        }
                    } catch (DateTimeException | ArrayIndexOutOfBoundsException ex) {
                        throw new ViewallInvalidUsageException("Date should be in yyyy-mm-dd format.");
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
        int size = taskList.size();
        System.out.println("     Got it. I've added this task:");
        System.out.printf("       %s%n", taskList.show(index));
        System.out.printf("     Now you have %d %s in the list%n", size, size > 1 ? "tasks" : "task");
    }

    /**
     * Split the input string into first word (command) and others
     *
     * @param input user input
     * @return an array, first element is command, second element is input
     */
    private String[] parseInput(String input) {
        return input.split("\\s+", 2);
    }

    private void addTodo(String todo) {
        taskList.add(new Todo(todo));
        storage.save(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }

    private void addDeadline(String deadline) throws DeadlineInvalidUsageException {
        taskList.add(Deadline.create(deadline));
        storage.save(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }

    private void addEvent(String event) throws EventInvalidUsageException {
        taskList.add(Event.create(event));
        storage.save(this.taskList);
        printAddConfirmation(taskList.size() - 1);
    }


}
