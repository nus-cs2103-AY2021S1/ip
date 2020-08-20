import java.util.HashSet;

enum Command {
    START,
    BYE,
    ADD,
}

public class Message {
    private final String message;
    private Command cmd;
    private HashSet<String> list;

    // public constructor
    public Message(String message, HashSet<String> list) {
        this.message = message;
        String[] words = message.split(" ");
        String cmdString = words[0];

        for (Command c : Command.values()) {
            if (c.toString().equalsIgnoreCase(cmdString)) {
                this.cmd = c;
                break;
            }
        }

        if (this.cmd == null) { // if not a recognised command
            this.cmd = Command.ADD;
        }

        this.list = list;
    }

    public void reply() {
        System.out.println("____________________________________________________________");
        switch (cmd) {
            case START:
                System.out.println("Eh what's up! I'm Meimei" +
                        "\nWhat you want ah?");
                break;
            case BYE:
                System.out.println("Ok bye bye! C u again :P");
                break;
            case ADD:
                System.out.println("added: " + message);
                break;
        }
        System.out.println("____________________________________________________________");
    }

    public Command getCmd() {
        return this.cmd;
    }
}
