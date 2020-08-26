import java.util.List;

public class Ui {
    private static final String LOGO =
            "██████╗ ███████╗███╗   ██╗███████╗██████╗ ██╗ ██████╗████████╗\n"
                    + "██╔══██╗██╔════╝████╗  ██║██╔════╝██╔══██╗██║██╔════╝╚══██╔══╝\n"
                    + "██████╔╝█████╗  ██╔██╗ ██║█████╗  ██║  ██║██║██║        ██║\n"
                    + "██╔══██╗██╔══╝  ██║╚██╗██║██╔══╝  ██║  ██║██║██║        ██║\n"
                    + "██████╔╝███████╗██║ ╚████║███████╗██████╔╝██║╚██████╗   ██║\n"
                    + "╚═════╝ ╚══════╝╚═╝  ╚═══╝╚══════╝╚═════╝ ╚═╝ ╚═════╝   ╚═╝\n";
    private static final String GOODBYE_MESSAGE = "Ok lor like that lor.";

    /**
     * Displays a greeting message to the user.
     */
    public void displayGreeting() {
        System.out.println("Hi, I'm ");
        System.out.println(LOGO);
        System.out.println("What do you need this time 😫");
    }

    /**
     * Displays a given number of messages in an indented block, enclosed by 2 indented lines.
     * @param messages a variable number of messages to be displayed in the indented block
     */
    public void displayMessages(String... messages) {
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        for (String message : messages) {
            System.out.printf("     %s\n", message);
        }
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();
    }

    /**
     * Displays a friendly reminder on the number of tasks to do, given a count. If the count is zero, display nothing.
     *
     * @param count the number of tasks
     */
    public void displayGreetingReminder(int count) {
        if (count == 0) {
            return;
        }
        if (count == 0) {
            this.displayMessages(
                    "Don't forget you already have one thing to do.",
                    "But okay.");
            return;
        }
        this.displayMessages(
                "Don't forget you already have " + count + " things to do.",
                "But okay.");
    }

    /**
     * Displays a list of tasks in an indented block. If the list of tasks is empty, display a message indicating so.
     *
     * @param tasks the tasks to display in the indented block
     */
    public void displayTasks(List<Task> tasks) {
        int noOfTasks = tasks.size();
        if (noOfTasks == 0) {
            this.displayMessages("You didn't tell me to remind you anything.");
        } else {
            String[] messages = new String[noOfTasks + 1];
            messages[0] = "Right, you said you wanted to:";

            for (int i = 0; i < noOfTasks; i++) {
                messages[i + 1] = String.format("%3d: %s", i + 1, tasks.get(i));
            }

            this.displayMessages(messages);
        }
    }

    /**
     * Retrieves a nicely formatted reminder of the number of tasks, given the number of tasks left.
     * @param noOfTasks the number
     * @return a formatted String
     */
    public String getTasksLeftMessage(int noOfTasks) {
        return String.format(
                "Now you have %d thing%s you need me to remind you about.",
                noOfTasks,
                noOfTasks == 1 ? "" : "s");
    }

    /**
     * Displays a goodbye message.
     */
    public void displayGoodbye() {
        this.displayMessages(GOODBYE_MESSAGE);
    }

    // TODO: Consider something more descriptive
    /**
     * Display an error message to the user indicating that Duke failed to load saved tasks properly.
     */
    public void showLoadingError() {
        this.displayMessages("Ugh, I can't remember what you told me to remind you :(");
    }

    public void displayMatchingTasks(List<Task> matchingTasks) {
        int noOfTasks = matchingTasks.size();
        if (noOfTasks == 0) {
            this.displayMessages("Well, I don't recall you asking me to note down anything like that.");
        } else {
            String[] messages = new String[noOfTasks + 1];
            messages[0] = "Right, here's some tasks that match what you asked for:";

            for (int i = 0; i < noOfTasks; i++) {
                messages[i + 1] = String.format("%3d: %s", i + 1, matchingTasks.get(i));
            }

            this.displayMessages(messages);
        }
    }
}
