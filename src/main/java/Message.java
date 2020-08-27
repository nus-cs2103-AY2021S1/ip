import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

enum Command {
    START,
    LIST,
    DONE,
    DELETE,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    NOT_FOUND
}

public class Message {
    private final String message;
    private Command cmd;
    private final List<Task> list;
    private final File taskFile;

    // public constructor
    public Message(String message, List<Task> list, File taskFile) {
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
        this.taskFile = taskFile;
    }

    public void reply() throws NoDescriptionException, NoCommandException, WrongItemIndexException {
        System.out.println("____________________________________________________________");
        if (this.cmd == Command.START) {
            System.out.println("Eh what's up! I'm Meimei" +
                    "\nWhat you want ah?");
        } else if (this.cmd == Command.LIST) {
            System.out.println("Na, here is your list lah:");
            int counter = 0;
            for (Task item : this.list) {
                counter++;
                System.out.println(counter +
                        "." +
                        item.toString());
            }
        } else if (this.cmd == Command.DONE) {
            try {
                String[] words = message.split(" ", 2);
                int index = Integer.parseInt(words[1]);
                Task item = this.list.get(index - 1);
                item.markDone();
                FileWriter fileWriter = new FileWriter(this.taskFile);
                String fileContents = this.list.get(0).toString();

                for (int i = 1; i < this.list.size(); i++) {
                    fileContents += "\n" + this.list.get(i).toString();
                }

                fileWriter.write(fileContents);
                fileWriter.close();

                System.out.println("Can, I help you mark this as done liao:" +
                        "\n  " +
                        item.toString());
            } catch (Exception e) {
                throw new WrongItemIndexException("done", this.list.size());
            }
        } else if (this.cmd == Command.DELETE) {
            try {
                String[] words = message.split(" ", 2);
                int index = Integer.parseInt(words[1]);
                Task item = list.get(index - 1);
                list.remove(index - 1);

                System.out.println("Okay, I deleted this liao:" +
                        "\n  " +
                        item.toString() +
                        "\nNow left " +
                        list.size() +
                        " things in the list.");

                FileWriter fileWriter = new FileWriter(this.taskFile);
                String fileContents = this.list.get(0).toString();

                for (int i = 1; i < this.list.size(); i++) {
                    fileContents += "\n" + this.list.get(i).toString();
                }

                fileWriter.write(fileContents);
                fileWriter.close();
            } catch (Exception e) {
                throw new WrongItemIndexException("done", this.list.size());
            }
        } else if (this.cmd == Command.BYE) {
            System.out.println("Ok bye bye! C u again :P");
        } else if (this.cmd == Command.TODO ||
                   this.cmd == Command.DEADLINE ||
                   this.cmd == Command.EVENT) {
            String[] words = message.split(" ", 2);
            String taskString;

            try {
                taskString = words[1];
            } catch (Exception e) {
                throw new NoDescriptionException(cmd.toString().toLowerCase());
            }

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
                this.list.add(t);
                try {
                    FileWriter fileWriter = new FileWriter(this.taskFile, true);
                    fileWriter.write(t.toString());
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Orh. I added:" +
                        "\n  " +
                        t.toString() +
                        "\nNow you got " +
                        list.size() +
                        " things in the list.");
            }

        } else if (this.cmd == Command.NOT_FOUND) {
            throw new NoCommandException();
        }
        System.out.println("____________________________________________________________");
    }

    public Command getCmd() {
        return this.cmd;
    }
}
