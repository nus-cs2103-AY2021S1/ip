import java.util.List;

enum Command {
    START,
    LIST,
    DONE,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    NOT_FOUND
}

public class Message {
    private final String message;
    private Command cmd;
    private List<Task> list;

    // public constructor
    public Message(String message, List<Task> list) {
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
            this.cmd = Command.NOT_FOUND;
        }

        this.list = list;
    }

    public void reply() {
        System.out.println("____________________________________________________________");
        if (cmd == Command.START) {
            System.out.println("Eh what's up! I'm Meimei" +
                    "\nWhat you want ah?");
        } else if (cmd == Command.LIST) {
            System.out.println("Na, here is your list lah:");
            int counter = 0;
            for (Task item : list) {
                counter++;
                System.out.println(counter +
                        "." +
                        item.toString());
            }
        } else if (cmd == Command.DONE) {
            String[] words = message.split(" ");
            try {
                int index = Integer.parseInt(words[1]);
                Task item = list.get(index - 1);
                item.markDone();
                System.out.println("Can, I help you mark this as done liao:" +
                        "\n  " +
                        item.toString());
            } catch (NumberFormatException e) {
                System.out.println("Cannot find leh. Try typing \"done {index of list item}\".");
            }
        } else if (cmd == Command.BYE) {
            System.out.println("Ok bye bye! C u again :P");
        } else if (cmd == Command.NOT_FOUND) {
            System.out.println("I cannot find this command leh. Try sth else?");
        }
        System.out.println("____________________________________________________________");
    }

    public Command getCmd() {
        return this.cmd;
    }
}
