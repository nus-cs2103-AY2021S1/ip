public class Command {
    protected String commandWord;

    public Command(String commandWord){
        this.commandWord = commandWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String execute() {
        return "SAMPLE EXECUTION";
    }
}
