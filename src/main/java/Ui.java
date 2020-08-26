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

    public String getMasterSpeak() {
        return this.masterSpeak;
    }

    public void welcomeMessage() {
        // Introduction at the beginning of the chat
        System.out.println(servantSpeak
                + "    Greetings my Liege.\n"
                + "    Why have you summoned me?\n");
    }
}
