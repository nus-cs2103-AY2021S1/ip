import java.util.Scanner;

/**
 * Chatbot to execute the necessary actions.
 */
public class Chatbot {

    private String inquiry;
    private final String INDENTATION = "     ";
    private final String LINE = "    ____________________________________________________________";

    Chatbot() {
        this.inquiry = "";
    }

    /**
     * The chat method prints the introductions and carry out the necessary steps.
     * A String of horizontal lines will be printed after every inquiry.
     * Once the user input "bye" the chat bot will print the farewell message.
     */
    void chat() {
        introductions();

        Scanner sc = new Scanner(System.in);
        this.inquiry = sc.nextLine();
        while (!inquiry.equals("bye")) {
            reply(inquiry);
            System.out.println(LINE);
            inquiry = sc.nextLine();
        }

        farewell();
    }

    /**
     * Prints out the introductions.
     */
    private void introductions() {
        reply("Hello, I'm Ravenloss");
        reply("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the farewell message.
     */
    private void farewell() {
        reply("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Prints out the string with the indentation.
     * @param string The string to be printed.
     */
    private void reply (String string) {
        System.out.println(INDENTATION + string);
    }


}
