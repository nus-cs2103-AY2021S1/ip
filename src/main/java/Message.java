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
        if (this.cmd == Command.START) {
            System.out.println("Eh what's up! I'm Meimei" +
                    "\nWhat you want ah?");
        } else if (this.cmd == Command.LIST) {
            System.out.println("Na, here is your list lah:");
            int counter = 0;
            for (Task item : list) {
                counter++;
                System.out.println(counter +
                        "." +
                        item.toString());
            }
        } else if (this.cmd == Command.DONE) {
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
        } else if (this.cmd == Command.BYE) {
            System.out.println("Ok bye bye! C u again :P");
        } else if (this.cmd == Command.TODO ||
                   this.cmd == Command.DEADLINE ||
                   this.cmd == Command.EVENT) {
            String[] words = message.split(" ", 2);
            String taskString = words[1];

            Task t = null;
            if (this.cmd == Command.TODO) {
                t = new Todo(taskString);
            } else if (this.cmd == Command.DEADLINE) {
                String[] temp = taskString.split(" /by ");
                if (temp.length == 2) {
                    t = new Deadline(temp[0], temp[1]);
                } else {
                    System.out.println("Eh you type wrong lah!" +
                            "\nTry \"deadline {description of task} /by {date/time}\"");
                }
            } else { // should be event
                String[] temp = taskString.split(" /at ");
                if (temp.length == 2) {
                    t = new Event(temp[0], temp[1]);
                } else {
                    System.out.println("Eh you type wrong lah!" +
                            "\nTry \"event {description of task} /at {date/time}\"");
                }
            }

            if (t != null) {
                list.add(t);

                System.out.println("Orh. I added:" +
                        "\n  " +
                        t.toString() +
                        "\nNow you got " +
                        list.size() +
                        " things in the list.");
            }

        } else if (this.cmd == Command.NOT_FOUND) {
            System.out.println("I cannot find this command leh. Try sth else?");
        }
        System.out.println("____________________________________________________________");
    }

    public Command getCmd() {
        return this.cmd;
    }
}
