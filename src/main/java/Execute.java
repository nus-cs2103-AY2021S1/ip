import java.util.Scanner;

public class Execute {
    public static void output() {
        // Initial welcome message
        Section welcomeSection = new Section("Hello! I'm Duke\n      What can I do for you?");
        welcomeSection.displayText();

        // Subsequent messages
        Scanner scan = new Scanner(System.in);
        while (true) {
            String userInput = scan.next();
            if (userInput.equals("bye")) {
                Section exitSection = new Section("Bye. Hope to see you again soon!");
                exitSection.displayText();
                break;
            }
                Section section = new Section(userInput);
                section.displayText();
        }
    }
}
