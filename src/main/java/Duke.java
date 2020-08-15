import java.util.Scanner;

public class Duke {

    private void userInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Output output = new Output();
            output.response(input);
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
        printWelcomeMessage();
        userInput();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
