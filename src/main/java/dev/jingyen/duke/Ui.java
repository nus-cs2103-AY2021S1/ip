package dev.jingyen.duke;

import java.util.List;

import dev.jingyen.duke.model.Task;

public class Ui {
    public static final String LOGO =
            "██████╗ ███████╗███╗   ██╗███████╗██████╗ ██╗ ██████╗████████╗\n"
                    + "██╔══██╗██╔════╝████╗  ██║██╔════╝██╔══██╗██║██╔════╝╚══██╔══╝\n"
                    + "██████╔╝█████╗  ██╔██╗ ██║█████╗  ██║  ██║██║██║        ██║\n"
                    + "██╔══██╗██╔══╝  ██║╚██╗██║██╔══╝  ██║  ██║██║██║        ██║\n"
                    + "██████╔╝███████╗██║ ╚████║███████╗██████╔╝██║╚██████╗   ██║\n"
                    + "╚═════╝ ╚══════╝╚═╝  ╚═══╝╚══════╝╚═════╝ ╚═╝ ╚═════╝   ╚═╝\n";
    public static final String GOODBYE_MESSAGE = "Ok lor like that lor.";

    /**
     * Displays a greeting message to the user.
     */
    public void displayGreeting() {
        System.out.println("Hi, I'm");
        System.out.println(LOGO);
        System.out.println("What do you need this time 😫");
    }

    /**
     * Displays a given number of messages in an indented block, enclosed by 2 indented lines.
     *
     * @param messages a variable number of messages to be displayed in the indented block
     */
    public String displayMessages(String... messages) {
        StringBuilder response = new StringBuilder();
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        for (String message : messages) {
            System.out.printf("     %s\n", message);
            response.append(message).append('\n');
        }
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();

        return response.toString();
    }

    /**
     * Displays a friendly reminder on the number of tasks to do, given a count. If the count is zero, display nothing.
     *
     * @param count the number of tasks
     */
    public String displayGreetingReminder(int count) {
        assert count >= 0;
        if (count == 0) {
            return "";
        }
        if (count == 1) {
            return displayMessages(
                    "Don't forget you already have one thing to do.",
                    "But okay.");
        }
        return displayMessages(
                "Don't forget you already have " + count + " things to do.",
                "But okay.");
    }

    /**
     * Displays a list of tasks in an indented block. If the list of tasks is empty, display a message indicating so.
     *
     * @param tasks the tasks to display in the indented block
     */
    public String displayTasks(List<Task> tasks) {
        int noOfTasks = tasks.size();
        if (noOfTasks == 0) {
            return displayMessages("You didn't tell me to remind you anything.");
        } else {
            String[] messages = new String[noOfTasks + 1];
            messages[0] = "Right, you said you wanted to:";

            for (int i = 0; i < noOfTasks; i++) {
                messages[i + 1] = String.format("%3d: %s", i + 1, tasks.get(i));
            }

            return displayMessages(messages);
        }
    }

    /**
     * Retrieves a nicely formatted reminder of the number of tasks, given the number of tasks left.
     *
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
    public String displayGoodbye() {
        return displayMessages(GOODBYE_MESSAGE);
    }

    // TODO: Consider something more descriptive

    /**
     * Display an error message to the user indicating that Duke failed to load saved tasks properly.
     */
    public String showLoadingError() {
        return displayMessages("Ugh, I can't remember what you told me to remind you :(");
    }

    /**
     * Displays a list of matching tasks in an indented block. If the list of tasks is empty, display a message
     * indicating so.
     *
     * @param matchingTasks the tasks to display in the indented block
     */
    public String displayMatchingTasks(List<Task> matchingTasks) {
        int noOfTasks = matchingTasks.size();
        if (noOfTasks == 0) {
            return displayMessages("Well, I don't recall you asking me to note down anything like that.");
        } else {
            String[] messages = new String[noOfTasks + 1];
            messages[0] = "Right, here's some tasks that match what you asked for:";

            for (int i = 0; i < noOfTasks; i++) {
                messages[i + 1] = String.format("%3d: %s", i + 1, matchingTasks.get(i));
            }

            return displayMessages(messages);
        }
    }

    /**
     * Displays a Help message for the user on how to use Duke.
     *
     * @return a formatted String for help
     */
    public String displayHelp() {
        return displayMessages(
                "So, I remind you of things.",
                "You can ask me for the following things:",
                "- help",
                "\tshow this help message.",
                "- list",
                "\ttell you what tasks you have.",
                "- find <search term>",
                "\tsee if you have any tasks that match the search term.",
                "- done <index>",
                "\tmark the task at the index as done.",
                "- delete <index>",
                "\tremove the task at the given index from the list.",
                "- todo <task name>",
                "\ttrack an uncompleted Todo to your list of tasks.",
                "- deadline <task name> /by <yyyy-mm-dd>",
                "\ttrack an uncompleted deadline Task.",
                "- event <task name> /at <period>",
                "\ttrack an uncompleted Event at a given period.",
                "- bye",
                "\texit. I'll remember the tasks you've told me to track."
        );
    }
}
