import java.util.Scanner;

public class Duke {

    String introduction = "Hello! I'm Duke";
    String greeting = "What can I do for you?";
    String goodbye = "Bye. Hope to see you again soon!";

    private void printGreeting() {
        System.out.println(this.introduction);
        System.out.println(this.greeting);
    }

    private void printGoodbye() {
        System.out.println(this.goodbye);
    }

    private void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }
        sc.close();
        printGoodbye();
    }

    public void run() {
        printGreeting();
        handleUserInput();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
