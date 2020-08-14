import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    final static List<String> items = new ArrayList<>();

    public static void main(String[] args) {
        speak("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String userInput;
        while (!(userInput = scanner.nextLine()).equals("bye")) {
            if (userInput.equals("list")) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < Duke.items.size(); i++) {
                    sb.append(String.format("%d. %s\n", i + 1, items.get(i)));
                }

                Duke.speak(sb.toString().trim());
            } else {
                items.add(userInput);
                Duke.speak(String.format("added: %s", userInput));
            }

        }

        Duke.speak("Bye. Hope to see you again soon!");

        scanner.close();
    }

    public static void speak(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

}
