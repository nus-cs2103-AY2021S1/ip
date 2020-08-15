import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Chatbot to execute the necessary actions.
 */
public class Chatbot {

    private String inquiry;
    private List<String> planner;
    private final String INDENTATION = "     ";
    private final String LINE = "    ____________________________________________________________";

    Chatbot() {
        this.inquiry = "";
        this.planner = new ArrayList<String>();
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
            if (inquiry.equals("list")) {
                list();
            } else {
                addToPlanner(inquiry);
                reply("added: " + inquiry);
            }
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

    /**
     * Add inquiry to the planner.
     * @param event String to be added to the list.
     */
    private void addToPlanner(String event) {
        planner.add(event);
    }

    /**
     * Prints out the list.
     */
    private void list() {
        for (int i = 0; i < planner.size(); i++) {
            String number = (i + 1) + ". ";
            reply(number + planner.get(i));
        }
    }


}
