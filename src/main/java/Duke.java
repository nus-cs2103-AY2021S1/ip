import exceptions.InvalidCommandException;

import java.util.Scanner;

public class Duke {

    private void userInput(TaskList taskList) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Output output = new Output();
            try {
                output.response(input, taskList);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
            if (input.equals("bye")) break;
        }
        sc.close();
    }

    private void printWelcomeMessage() {
        String welcome = "_____________________________________\n"
                + "Hello! I'm Ray\n" + "Please input a command\n"
                + "_____________________________________\n";
        System.out.println(welcome);
    }

    public void start() {
        TaskList todoList = new TaskList();
        printWelcomeMessage();
        userInput(todoList);
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
