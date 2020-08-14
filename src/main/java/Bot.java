import java.util.Scanner;
import java.util.ArrayList;

public class Bot {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String prompt = "What can I do for you?";
    static String farewellMsg = "Bye. Hope to see you again soon!";
    static String indentation = "    ";
    static String demarcation = Bot.indentation + "---------------";

    ArrayList<TodoItem> todos;

    Bot() {
        this.todos = new ArrayList<>(100);
    }

    public void interact() {
        System.out.println("Hello from\n" + Bot.logo);
        System.out.println(Bot.prompt);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String command = scanner.nextLine();

            System.out.println(Bot.demarcation);
            // Dispatch respective handlers depending on command
            switch (command) {
                case ("bye"):
                    System.out.println(indentWord(farewellMsg));
                    return;
                case ("list"):
                    printTodos();
                    break;
                default:
                    if (command.startsWith("done ")) {
                        markComplete(command);
                    } else {
                        addTodos(command);
                    }
                    break;
            }
            System.out.println(Bot.demarcation);
        }
    }

    private String indentWord(String word) {
        return Bot.indentation + word;
    }

    private void printTodos() {
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
        for (TodoItem todo : todos) {
            System.out.print(indentWord(Integer.toString(counter) + ". "));
            System.out.println(todo);
            counter++;
        }
    }

    private void addTodos(String command) {
        TodoItem todo = new TodoItem(command);
        // Add todos
        todos.add(todo);
        // Response from Duke for adding todos
        System.out.print(indentWord("added: "));
        System.out.println(todo);
    }

    private void markComplete(String command) {
        int indexToMark = Integer.parseInt(command.substring(5)) - 1;
        todos.get(indexToMark).markDone();

        // Print todo that has been marked done
        System.out.println(indentWord("Nice! I've marked this task as done: "));
        System.out.println(Bot.indentation + todos.get(indexToMark));
    }
}
