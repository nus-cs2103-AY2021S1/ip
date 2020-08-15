import java.util.ArrayList;
import java.util.List;

public class Processor {
    List<Task> list = new ArrayList<>();
    String command;
    String description;
    boolean entered = false;

    public void process(String next) {
        try {
            findCommand(next);

            switch (this.command) {
                case "done":
                    int pos = Integer.parseInt(this.description);
                    list.get(pos - 1).markAsDone();
                    System.out.println("Marked this task as done for you!\n" + list.get(pos - 1));
                    break;
                case "list":
                    for (Task i : list) {
                        System.out.println(list.indexOf(i) + 1 + "." + i);
                    }
                    break;
                case "todo":
                    list.add(new Todo(this.description));
                    System.out.println("Hi there, I've added this task:\n"
                            + list.get(list.size() - 1) + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                case "deadline":
                    String[] descriptions = this.description.split("/by ");
                    list.add(new Deadline(descriptions[0], descriptions[1]));
                    System.out.println("Hi there, I've added this task:\n"
                            + list.get(list.size() - 1) + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                case "event":
                    String[] descriptions2 = this.description.split("/at ");
                    list.add(new Event(descriptions2[0], descriptions2[1]));
                    System.out.println("Hi there, I've added this task:\n"
                            + list.get(list.size() - 1) + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                default:
                    System.out.println("Error! Please key in what type of task it is.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }


    public void findCommand(String description) throws DukeException {
        if (description.length() == 0 && entered) {
            throw new DukeException("there's no commands");
        } else if (description.equals("list")) {
            if (list.size() == 0) {
                throw new DukeException("there's nothing on the list yet.");
            }
            this.command = "list";
        } else if (description.length() >= 4 && description.substring(0, 4).equals("done")) {
            if (description.substring(3).split(" ").length == 1) {
                throw new DukeException("you need to give a number.");
            } else if (description.split(" ").length > 2) {
                throw new DukeException("Check one at a time pls.");
            } else {
                try {
                    int index = Integer.parseInt(description.split(" ")[1]);
                    if (this.list.size() < index || index <= 0) {
                        throw new DukeException("you don't have this task number.");
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("you need to put a number.");
                }
            }
            this.command = "done";
            this.description = description.split(" ")[1];
        } else if (description.length() >= 4 && description.substring(0, 4).equals("todo")) {
            if (description.substring(3).split(" ").length == 1) {
                throw new DukeException(("you don't even know what you want to do."));
            }
            this.command = "todo";
            this.description = description.substring(5);
        } else if (description.length() >= 8 && description.substring(0, 8).equals("deadline")) {
            if (description.substring(7).split(" ").length == 1) {
                throw new DukeException("no deadline given so how you know when it is?");
            } else if (description.split("/by").length < 2) {
                throw new DukeException("for deadlines, please include a '/by' just before the deadline!");
            }
            this.command = "deadline";
            this.description = description.substring(9);
        } else if (description.length() >= 5 && description.substring(0, 5).equals("event")) {
            if (description.substring(4).split(" ").length == 1) {
                throw new DukeException("I don't see any event.");
            } else if (description.split("/at").length < 2) {
                throw new DukeException("for events, please include a '/at' just before the event!");
            }
            this.command = "event";
            this.description = description.substring(6);
        } else if (entered) {
            throw new DukeException("you gotta put in a correct command.");
        } else {
            throw new DukeException("type in 'todo', 'deadline', 'event' to start!");
        }
        entered = true;
    }
}

