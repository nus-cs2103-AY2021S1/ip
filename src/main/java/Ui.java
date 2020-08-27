import java.util.ArrayList;

public class Ui {
    private final String SERVANT_SPEAK;
    private final String USER_PROMPT;

    public Ui () {
        // Initialise strings to separate messages from Duke
        // and commands from CLI
        this.SERVANT_SPEAK = "Duke:\n";
        this.USER_PROMPT = "Your Command Sire:";
    }

    public String getServantSpeak() {
        return this.SERVANT_SPEAK;
    }

    public String getUserPrompt() {
        return this.USER_PROMPT;
    }

    public void welcomeMessage() {
        // Introduction at the beginning of the chat
        System.out.println(SERVANT_SPEAK
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");
    }

    public void availableCommands() {
        System.out.println(SERVANT_SPEAK
                + "    These are your available commands my Lord:\n\n"
                + "    help - Show list of available commands\n"
                + "    todo <Description of task>"
                + " - To add a normal to do task\n"
                + "    deadline <Description of task>"
                + " /by <Date in YYYY-MM-DD> <Time in hh:mm>"
                + " - To add a task with a deadline\n"
                + "    event <Description of event>"
                + " /at <Date in YYYY-MM-DD> <Time in hh:mm>"
                + " - To add an event\n"
                + "    list - Show list of tasks\n"
                + "    done <task number> - Mark task as done\n"
                + "    delete <task number> - Delete task from list\n");
    }

    public void printTaskAddedMessage(Task task, int size) {
        System.out.println(SERVANT_SPEAK
                + "    As you wish Sire. I have added the task:\n       "
                + task.toString() + "\n"
                + "    Now you have " + size
                + " tasks in the list.\n");
    }

    public void printListOfTasks(ArrayList<Task> userTasks) {
        System.out.println(SERVANT_SPEAK
                + "    Here are your tasks your Majesty:");
        System.out.println(new TaskList(userTasks).toString());
    }

    public void printMarkAsDoneMessage(Task task) {
        System.out.println(SERVANT_SPEAK
                + "    As you wish Sire. I have marked this task as done:\n"
                + "       " + task.toString());
    }

    public void printTaskDeletedMessage(Task task) {
        System.out.println(SERVANT_SPEAK
                + "    As you wish Sire. I removed this task:\n"
                + "       " + task.toString());
    }

    public void printByeMessage() {
        System.out.println(SERVANT_SPEAK
                + "    It was a pleasure to serve you my Liege.\n"
                + "    Till next time.");
    }

    public void printError(DukeException ex) {
        System.out.println(ex);
    }
}
