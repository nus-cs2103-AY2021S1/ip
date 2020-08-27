public class Ui {
    private String servantSpeak;
    private String masterSpeak;

    public Ui () {
        // Initialise strings to separate messages from Duke
        // and commands from CLI
        this.servantSpeak = "Duke:\n";
        this.masterSpeak = "Your Command Sire:";
    }

    public String getServantSpeak() {
        return this.servantSpeak;
    }

    public String getUserPrompt() {
        return this.masterSpeak;
    }

    public void welcomeMessage() {
        // Introduction at the beginning of the chat
        System.out.println(servantSpeak
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");
    }

    public void availableCommands() {
        System.out.println(servantSpeak
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
}
