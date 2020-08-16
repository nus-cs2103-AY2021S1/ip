import java.util.Scanner;

public class Duke {

    private void userInput(TodoList todoList) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Output output = new Output();
            output.response(input, todoList);
            if (input.equals("bye")) break;
        }
        sc.close();
    }

    private void printWelcomeMessage() {
        String welcome = "_____________________________________ \n"
                + "Hello! I'm Ray \n" + "Please input a command\n"
                + "_____________________________________ \n";
        System.out.println(welcome);
    }

    public void start() {
        TodoList todoList = new TodoList();
        printWelcomeMessage();
        userInput(todoList);
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
