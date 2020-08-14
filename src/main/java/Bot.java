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

    ArrayList<String> todos;

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
                    addTodos(command);
                    break;
            }
            System.out.println(Bot.demarcation);
        }
    }

    private void printTodos() {
        int counter = 1;

        for (String todo : todos) {
            System.out.print(indentWord(Integer.toString(counter) + ". "));
            System.out.println(todo);
            counter++;
        }
    }

    private void addTodos(String command) {
        // Add todos
        todos.add(command);
        // Response from Duke for adding todos
        System.out.print(indentWord("added: "));
        System.out.println(command);
    }

    private String indentWord(String word) {
        return Bot.indentation + word;
    }
}
