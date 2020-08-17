import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Duke, more commonly known as Duck, is a Personal Assistant Chat Bot that
 * helps a person to keep track of various things.
 * Contains static attribute stored_text which stores text input from user.
 **/
public class Duke {

    public static List<String> stored_text = new ArrayList<>();

    /**
     * Lists stored text by looping through stored_text.
     **/
    public static void listStoredText() {
        if (stored_text.isEmpty()) {
            System.out.println("No text stored...");
        } else {
            int count = 1;
            for (String text : stored_text) {
                System.out.println(count + ". " + text);
                count++;
            }
        }
    }

    /**
     * Adds input text into stored_text.
     *
     * @param input Input text from user to be stored.
     **/
    public static void addText(String input) {
        stored_text.add(input);
        System.out.println("Quack! I have added: " + input + " \uD83C\uDF0A");
    }

    /**
     * Prints greeting message.
     * Scans for commands entered by the user, then stores input text into stored_text.
     * Upon user command input "list", stored text will be listed.
     * Upon user command input "bye", system is exited.
     **/
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String greeting_message = line +
                "\n Quack! I am Duck \uD83E\uDD86" +
                "\n How can I help you today?\n" + line;
        String exit_message = line +
                "\n Waddling off now. See you soon! \uD83D\uDC4B\uD83C\uDFFB \n" + line;
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting_message);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit_message);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                listStoredText();
                System.out.println(line);
            } else {
                System.out.println(line);
                addText(input);
                System.out.println(line);
            }
        }
        sc.close();
    }
}
