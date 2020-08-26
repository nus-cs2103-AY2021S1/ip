import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private enum type {
        Todo {
            public String toString() {
                return "todo";
            }
        },
        Deadline {
            public String toString() {
                return "deadline";
            }
        },
        Event {
            @Override
            public String toString() {
                return "event";
            }
        },
        Delete {
            @Override
            public String toString() {
                return "delete";
            }
        },
    }

    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.equals(("bye"))) {
            storage.Save();
            return false;
        } else if (input.equals("list")) {
            ui.printTaskList(tasks);
        } else if (input.contains("done")) {
            doneCommand(input, tasks, storage, ui);
        } else {
            ui.printBorder();
            if (input.contains(type.Todo.toString())) {
                toDoCommand(input, tasks, storage, ui);
            } else if (input.contains(type.Deadline.toString())) {
                deadlineCommand(input, tasks, storage, ui);
            } else if (input.contains(type.Event.toString())) {
                eventCommand(input, tasks, storage, ui);
            } else if (input.contains(type.Delete.toString())) {
                deleteCommand(input, tasks, storage, ui);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return true;
    }

    private static void doneCommand(String input, TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            if (input.length() > 5) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(index).markAsDone();
                storage.Save();
                ui.doneTask(tasks.get(index));
            } else {
                throw new DukeException(" ☹ OOPS!!! What task did you complete? Please specific a valid task index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! You went out of the valid values, please specific a valid task index.");
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }
    }

    private static void toDoCommand(String input, TaskList tasks, Storage storage, Ui ui) {
        try {
            if (input.length() > 5) {
                Todo t = new Todo(input.substring(5));
                tasks.addTask(t);
                storage.Save();
                ui.addTask(t, tasks);
            } else {
                throw new DukeException("☹ Insufficient details! The description of a todo cannot be empty.");
            }
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }
    }

    private static void deadlineCommand(String input, TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            String[] s = input.split("/by ", 2);
            if (s[0].length() > 9) {
                String[] s2 = s[1].split("-", 3);
                LocalDate by = LocalDate.parse(s2[2] + "-" + s2[1] + "-" + s2[0]);
                String[] desc = s[0].split(" ", 2);
                Deadline dl = new Deadline(desc[1], by);
                tasks.addTask(dl);
                storage.Save();
                ui.addTask(dl, tasks);
            } else {
                throw new DukeException(" ☹ Insufficient details! The description of a deadline cannot be empty.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Date not/wrongly entered! (Please provide in this format: deadline description /by dd-mm-yyyy");
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ Date wrongly entered, please remember to format date in dd-MM-yyyy!");
        }
    }

    private static void eventCommand(String input, TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            String[] s = input.split("/at ", 2);
            if (s[0].length() > 6) {
                String[] s2 = s[1].split("-", 3);
                LocalDate at = LocalDate.parse(s2[2] + "-" + s2[1] + "-" + s2[0]);
                String[] desc = s[0].split(" ", 2);
                Event e = new Event(desc[1], at);
                tasks.addTask(e);
                storage.Save();
                ui.addTask(e, tasks);
            } else {
                throw new DukeException(" ☹ Insufficient details! The description of an event cannot be empty.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Date not/wrongly entered! (Please provide in this format: event description /at dd-mm-yyyy");
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ Date wrongly entered, please remember to format date in dd-MM-yyyy!");
        }
    }

    private static void deleteCommand(String input, TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            if (input.length() > 7) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task t = tasks.get(index);
                tasks.deleteTask(index);
                storage.Save();
                ui.deleteTask(t, tasks);
            } else {
                throw new DukeException("Please provide the index of the task you would like to remove.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index you specified is not valid. Please try again.");
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }
    }
}


