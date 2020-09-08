package duke.logic;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.MocoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

public class Parser {
    private enum type {
        DEADLINE {
            public String toString() {
                return "deadline";
            }
        },
        DELETE {
            @Override
            public String toString() {
                return "delete";
            }
        },
        EVENT {
            @Override
            public String toString() {
                return "event";
            }
        },
        FIND {
            @Override
            public String toString() {
                return "find";
            }
        },
        TODO {
            public String toString() {
                return "todo";
            }
        },
    }

    /**
     * Processes/parses commands input to Moco
     * If the command is invalid, Exception is thrown and
     * user is prompted for a different command.
     *
     * @param input   user input (command)
     * @param tasks   TaskList to keep tasks
     * @param ui      ui
     * @param storage to save tasks from tasklist
     * @return whether or not a command/userInput is valid
     * @throws MocoException If command is not valid.
     */
    public static String parse(String input, TaskList tasks, Ui ui, Storage storage) throws MocoException {
        String result;
        if (input.equals(("bye"))) {
            storage.Save();
            result = ui.stopBot();
            return result;
        } else if (input.equals("hi") || input.equals("hello")) {
            return ui.printGreeting();
        } else if (input.equals("list")) {
            //ui.printTaskList(tasks);
            if (tasks.size() > 0) {
                assert tasks.size() == 0;
                result = ui.showTaskList(tasks);
            } else {
                result = "You have no tasks";
            }
        } else if (input.contains("done")) {
            result = doneCommand(input, tasks, storage, ui);
        } else {
            //ui.printBorder();
            if (input.contains(type.TODO.toString())) {
                result = toDoCommand(input, tasks, storage, ui);
            } else if (input.contains(type.DEADLINE.toString())) {
                result = deadlineCommand(input, tasks, storage, ui);
            } else if (input.contains(type.EVENT.toString())) {
                result = eventCommand(input, tasks, storage, ui);
            } else if (input.contains(type.DELETE.toString())) {
                result = deleteCommand(input, tasks, storage, ui);
            } else if (input.contains(type.FIND.toString())) {
                result = findCommand(input, tasks, storage, ui);
            } else {
                throw new MocoException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return result;
    }

    private static String findCommand(String input, TaskList tasks, Storage storage, Ui ui) throws MocoException {
        try {
            if (input.length() > 5) {
                String keyword = input.substring(5);
                TaskList tl = tasks.findTasks(keyword);
                if (tl.size() > 0) {
                    return ui.findTasks(tl);
                } else {
                    throw new MocoException(" ☹ OOPS!!! You have no task that contains the keyword " + keyword + "!");
                }
            } else {
                throw new MocoException(" ☹ OOPS! What task are you looking for? Please specify a keyword.");
            }
        } catch (MocoException e) {
            throw new MocoException(e.getMessage());
        }
    }

    private static String doneCommand(String input, TaskList tasks, Storage storage, Ui ui) throws MocoException {
        try {
            if (input.length() > 5) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(index).markAsDone();
                storage.Save();
                return ui.doneTask(tasks.get(index));
            } else {
                throw new MocoException(" ☹ OOPS!!! What task did you complete? Please specific a valid task index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MocoException(" ☹ OOPS!!! You went out of the valid values, please specific a valid task index.");
        } catch (MocoException e) {
            throw new MocoException(e.getMessage());
        }
    }

    private static String toDoCommand(String input, TaskList tasks, Storage storage, Ui ui) throws MocoException {
        try {
            if (input.length() > 5) {
                Todo t = new Todo(input.substring(5));
                tasks.addTask(t);
                storage.Save();
                return ui.addTask(t, tasks);
            } else {
                throw new MocoException("☹ Insufficient details! The description of a todo cannot be empty.");
            }
        } catch (Exception e) {
            throw new MocoException(e.getMessage());
        }
    }

    private static String deadlineCommand(String input, TaskList tasks, Storage storage, Ui ui) throws MocoException {
        try {
            String[] s = input.split("/by ", 2);
            if (s[0].length() > 9) {
                String[] s2 = s[1].split("-", 3);
                LocalDate by = LocalDate.parse(s2[2] + "-" + s2[1] + "-" + s2[0]);
                String[] desc = s[0].split(" ", 2);
                Deadline dl = new Deadline(desc[1], by);
                tasks.addTask(dl);
                storage.Save();
                return ui.addTask(dl, tasks);
            } else {
                throw new MocoException(" ☹ Insufficient details! The description of a deadline cannot be empty.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MocoException("Date not/wrongly entered! "
                    + "(Please provide in this format: deadline description /by dd-mm-yyyy");
        } catch (DateTimeParseException e) {
            throw new MocoException(" ☹ Date wrongly entered, please remember to format date in dd-MM-yyyy");
        }
    }

    private static String eventCommand(String input, TaskList tasks, Storage storage, Ui ui) throws MocoException {
        try {
            String[] s = input.split("/at ", 2);
            if (s[0].length() > 6) {
                String[] s2 = s[1].split("-", 3);
                LocalDate at = LocalDate.parse(s2[2] + "-" + s2[1] + "-" + s2[0]);
                String[] desc = s[0].split(" ", 2);
                Event e = new Event(desc[1], at);
                tasks.addTask(e);
                storage.Save();
                return ui.addTask(e, tasks);
            } else {
                throw new MocoException(" ☹ Insufficient details! The description of an event cannot be empty.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MocoException("Date not/wrongly entered! (Please provide in this format: "
                    + "event description /at dd-mm-yyyy");
        } catch (DateTimeParseException e) {
            throw new MocoException(" ☹ Date wrongly entered, please remember to format date in dd-MM-yyyy!");
        }
    }

    private static String deleteCommand(String input, TaskList tasks, Storage storage, Ui ui) throws MocoException {
        try {
            if (input.length() > 7) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task t = tasks.get(index);
                tasks.deleteTask(index);
                storage.Save();
                return ui.deleteTask(t);
            } else {
                throw new MocoException("Please provide the index of the task you would like to remove.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MocoException("The index you specified is not valid. Please try again.");
        } catch (Exception e) {
            throw new MocoException(e.getMessage());
        }
    }
}


