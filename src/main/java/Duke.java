import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String userInput;

        greeting();

        do {
            userInput = getUserInput();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(userInput);
            }
        } while (!userInput.equals("bye"));
    }

    public static void greeting() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}
