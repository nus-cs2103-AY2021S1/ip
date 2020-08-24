package duke;

import duke.commands.*;

public class Parser {
    private Commands command;
    boolean entered = false;
    String description = "";

    public Command findCommand(String description) throws DukeException {
        if (description.length() == 0 && entered) {
            throw new DukeException("there's no commands");
        } else if (description.equals("list")) {
            command = Commands.LIST;
            return new ListCommand();
        } else if (description.length() >= 4 && description.substring(0, 4).equals("done")) {
            if (description.substring(3).split(" ").length == 1) {
                throw new DukeException("you need to give a number.");
            } else if (description.split(" ").length > 2) {
                throw new DukeException("Check one at a time pls and only one space between your 'done' and the task number.");
            }
            command = Commands.DONE;
            this.description = description.split(" ")[1];
            return new DoneCommand(this.description);
        } else if (description.length() >= 4 && description.substring(0, 4).equals("todo")) {
            if (description.substring(3).split(" ").length == 1) {
                throw new DukeException(("you don't even know what you want to do."));
            }
            command = Commands.TODO;
            this.description = description.substring(5);
            return new AddCommand(command, this.description);
        } else if (description.length() >= 8 && description.substring(0, 8).equals("deadline")) {
            if (description.substring(7).split(" ").length == 1) {
                throw new DukeException("no deadline given so how you know when it is?");
            } else if (description.split("/by").length < 2) {
                throw new DukeException("for deadlines, please include a '/by' just before the deadline!");
            }
            command = Commands.DEADLINE;
            this.description = description.substring(9);
            return new AddCommand(command, this.description);
        } else if (description.length() >= 5 && description.substring(0, 5).equals("event")) {
            if (description.substring(4).split(" ").length == 1) {
                throw new DukeException("I don't see any event.");
            } else if (description.split("/at").length < 2) {
                throw new DukeException("for events, please include a '/at' just before the event!");
            }
            command = Commands.EVENT;
            this.description = description.substring(6);
            return new AddCommand(command, this.description);
        } else if (description.length() >= 6 && description.substring(0,6).equals("delete")) {
            if (description.substring(5).split(" ").length == 1) {
                throw new DukeException("you need to give a number.");
            } else if (description.split(" ").length > 2) {
                throw new DukeException("Delete one at a time pls and only have one space between 'delete' and the task number.");
            }
            command = Commands.DELETE;
            this.description = description.split(" ")[1];
            return new DeleteCommand(this.description);
        } else if (description.length() >= 4 && description.substring(0,4).equals("date")) {
            if (description.substring(3).split(" ").length == 1 || description.substring(3).split(" ").length > 2 ) {
                throw new DukeException(("you need to input a legit date for e.g: 29-01-19, no more and no less."));
            }
            command = Commands.DATE;
            this.description = description.substring(5);
            return new DateCommand(this.description);
        } else if (description.equals("bye")) {
            return new ByeCommand();
        } else if (entered) {
            throw new DukeException("you gotta put in a correct command.");
        } else {
            entered = true;
            throw new DukeException("type in 'todo', 'deadline', 'event' to start!\nAlso, type 'date' and key in a date in YYYY-MM-DD format to search for events/deadlines happening on that date!");
        }
    }

    public Commands getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }
}
