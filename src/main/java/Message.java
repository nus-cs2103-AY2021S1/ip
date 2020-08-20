import java.util.List;

enum Command {
    START,
    LIST,
    DONE,
    BYE,
    ADD,
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
            case LIST:
                System.out.println("Na, here is your list lah:");
                int counter = 0;
                for (Task item : list) {
                    counter++;
                    System.out.println(counter +
                            ".[" +
                            item.getStatusIcon() +
                            "] " +
                            item.getTaskName());
                }
                break;
            case DONE:
                String[] words = message.split(" ");
                try {
                    int index = Integer.parseInt(words[1]);
                    Task item = list.get(index - 1);
                    item.markDone();
                    System.out.println("Can, I help you mark this as done liao:" +
                            "\n  [" +
                            item.getStatusIcon() +
                            "] " +
                            item.getTaskName());
                } catch (NumberFormatException e) {
                    System.out.println("Cannot find leh. Try typing \"done {index of list item}\".");
                }
                break;
            case BYE:
                System.out.println("Ok bye bye! C u again :P");
                break;
            case ADD:
                this.list.add(new Task(message));
                System.out.println("I added: " + message);
                break;
        }
        System.out.println("____________________________________________________________");
    }

    public Command getCmd() {
        return this.cmd;
    }
}
