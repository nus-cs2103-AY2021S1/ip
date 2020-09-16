public class Command {
    private String dukeMessage;

    protected Command(String message) {
        dukeMessage = message;
    }

    protected void appendDukeMessage(String extraMessage) {
        dukeMessage = dukeMessage + extraMessage;
    }

    @Override
    public String toString() {
        return dukeMessage;
    }
}
