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

    public void displayGreeting() {
        System.out.println("Hi, I'm ");
        System.out.println(LOGO);
        System.out.println("What do you need this time 😫");
    }

    public void displayMessages(String...messages) {
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        for (String message : messages) {
            System.out.printf("     %s\n", message);
        }
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();
    }

    public void displayGreetingReminder(int count) {
        if (count == 0) {
            return;
        }
        this.displayMessages(
                "Don't forget you already have " + count + " things to do.",
                "But okay.");
    }

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

    public String getTasksLeftMessage(int noOfTasks) {
        return String.format(
                "Now you have %d thing%s you need me to remind you about.",
                noOfTasks,
                noOfTasks == 1 ? "" : "s");
    }

    public void displayGoodbye() {
        this.displayMessages(GOODBYE_MESSAGE);
    }

    // TODO: Consider something more descriptive
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
