import java.util.Scanner;

public class Duke {
    public static void say(String text) {
        System.out.println("Duke: " + text);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String greetingText = "Hey There! What can I do for you?";
        say(greetingText);

        String response = scanner.nextLine();
        while (!response.equals("bye")) {
            say(response);
            response = scanner.nextLine();
        }

        String byeText = "Bye! Hope to see you again.";
        say(byeText);
    }
}
