import java.util.Scanner;

public class Duke {

    private void userInput() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            System.out.println("Bye. Hope to see you again soon!\n"+"_____________________________________" );
        }
    }

    private void printWelcomeMessage() {
        String welcome = "_____________________________________ \n"
                + "Hello! I'm Ray \n" + "What can I do for you?\n"
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
