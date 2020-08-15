import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Chatbot to execute the necessary actions.
 */
public class Chatbot {

    protected String inquiry;
    protected List<Task> planner;
    protected final String INDENTATION = "     ";
    protected final String LINE = "    ____________________________________________________________";

    Chatbot() {
        this.inquiry = "";
        this.planner = new ArrayList<Task>();
    }

    /**
     * The chat method prints the introductions and carry out the necessary steps.
     * A String of horizontal lines will be printed after every inquiry.
     * Under the while loop, if the inquiry is 'list', it will list all the tasks.
     * If the inquiry starts with 'done', it will mark the task as done.
     * Once the user input "bye" the chat bot will print the farewell message.
     */
    void chat() {
        introductions();

        Scanner sc = new Scanner(System.in);
        this.inquiry = sc.nextLine();
        while (!inquiry.equals("bye")) {
            if (inquiry.equals("list")) {
                list();
            } else if (inquiry.startsWith("done")) {
                String number = "";
                for (String val: inquiry.split(" ")) {
                    number = val;
                }
                Task currentTask = planner.get(Integer.parseInt(number) - 1);
                done(currentTask);

            } else {
                Task currentTask = new Task(inquiry);
                addToPlanner(currentTask);
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
     * @param task String to be added to the list.
     */
    private void addToPlanner(Task task) {
        planner.add(task);
    }

    /**
     * Prints out the list.
     */
    private void list() {
        for (int i = 0; i < planner.size(); i++) {
            String number = (i + 1) + ".";
            String status = "[" + planner.get(i).getStatusIcon() + "]";
            reply(number + status + " " + planner.get(i).description);
        }
    }

    /**
     * Marks the task as done. It also prints the completion message.
     * @param task Task that was just completed.
     */
    private void done(Task task) {
        task.done();
        System.out.println(INDENTATION + "Good job! I've marked this task as done");
        System.out.println(INDENTATION
                + INDENTATION
                + "[" + "\u2713" + "] "
                + task.description);

    }


}
