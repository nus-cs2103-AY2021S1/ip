package dependencies.parser;

import dependencies.executable.Command;
import dependencies.executable.CommandType.*;
import dependencies.executable.Executable;
import dependencies.task.Task;

class Checker {
    private Executable command;

    /**
     * Constructor for the checker object. Validates the command given/if and creates an Executable object
     * that can be passed to the executor.
     *
     * @param e string to be parsed into a command
     */
    private Checker(Executable e) {
        this.command = e;
    }

    public static Checker parseAndCheck(String s) {
        Executable e;
        if (s.contains("list") || s.contains("List")) {
            e = Command.createListCommand(null);
        }
        else if (s.contains("done ") || s.contains("Done ")) {
            int x = s.indexOf("done");
            String task = s.substring((x) + 5);
            Task t = Task.createMiscTask(task.trim());
            e = Command.createDoneCommand(t);
        }
        else if (s.contains("todo ") || s.contains("Todo ")) {
            int x = s.indexOf("todo");
            String task = s.substring(x + 5);
            Task t = Task.createTodo(task.trim());
            e = Command.createAddCommand(t);

        } else if (s.contains("event ") || s.contains("Event ")) {
            int x = s.indexOf("event");
            String task = s.substring(x + 6);
            String[] arr = task.split("/at");
            Task t = Task.createEvent(arr[0].trim(), arr[1].trim());
            e = Command.createAddCommand(t);

        } else  if (s.contains("deadline ") || s.contains("Deadline ")) {
            int x = s.indexOf("deadline");
            String task = s.substring(x + 9);
            String[] arr = task.split("/by");
            Task t = Task.createEvent(arr[0].trim(), arr[1].trim());
            e = Command.createAddCommand(t);

        } else if (s.contains("delete") || s.contains("Delete")) {
            int x = s.indexOf("delete");
            String task = s.substring(x + 7);
            Task t = Task.createMiscTask(task);
            e = Command.createDeleteCommand(t);
        } else {
            //TODO: Throw exceptions if command is unrecognised/invalid.
            e = null;
        }

        return new Checker(e);
    }

    private static boolean checkForError() {
        return false;
    }

    private boolean isEvent(String s) {
        return s.contains("/at");
    }

    private boolean isDeadline(String s) {
        return s.contains("/by");
    }

    private boolean isTodo(String s) {return false;}

    public Executable getExecutable() {
        return this.command;
    }

    public boolean isValidCommand(String s) {
        return false;
    }

}
