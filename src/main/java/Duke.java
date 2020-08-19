import main.java.manager.Parser;

public class Duke {

    private void printGreeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void run() {
        printGreeting();
        new Parser().handleUserInput();
        printGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
