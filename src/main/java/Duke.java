import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        speak("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String userInput;
        while (!(userInput = scanner.nextLine()).equals("bye")) {
            speak(userInput);
        }
        speak("Bye. Hope to see you again soon!");

        scanner.close();
    }

    public static void speak(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

}
