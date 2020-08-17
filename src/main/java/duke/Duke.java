package duke;

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

            // add instructions
            String[] parsed = parseInput(input);
            String command = parsed[0];
            switch (command) {
                case "list":
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < todoList.size(); i++) {
                        System.out.printf("     %d. %s%n", i + 1, todoList.get(i).showTask());
                    }
                    break;
                case "done":
                    int done = Integer.parseInt(parsed[1]);
                    if (done > 0 && done <= todoList.size()) {
                        todoList.get(done - 1).markAsDone();
                        System.out.println("     Good job! I've marked this task as done:");
                        System.out.printf("      %s%n", todoList.get(done - 1).showTask());
                    } else {
                        System.out.println("     Sorry, I can't find it in your list!");
                    }
                    break;
                case "todo":
                    todoList.add(new Todo(parsed[1]));
                    printAddConfirmation(todoList.size() - 1);
                    break;
                case "deadline":
                    String[] parsedDeadline = parsed[1].split(" /by ", 2);
                    todoList.add(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                    printAddConfirmation(todoList.size() - 1);
                    break;
                case "event":
                    String[] parsedEvent = parsed[1].split(" /at ", 2);
                    todoList.add(new Event(parsedEvent[0], parsedEvent[1]));
                    printAddConfirmation(todoList.size() - 1);
                    break;
                default:
                    // TODO: handle unknown cases here
                    System.out.println("Invalid command!");
                    continue;
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
}
