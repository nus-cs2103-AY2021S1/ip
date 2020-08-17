package dependencies.parser;

import dependencies.executable.CommandType.*;
import dependencies.executable.Executable;

class Checker {
    private String s;
    private Executable command;
    private String task;
    private boolean containsTask;


    Checker(String s) {
        this.s = s;

        if (s.contains("list") || s.contains("List")) {
            this.containsTask = false;
            this.command = "list";

        }
        else if (s.contains("done ") || s.contains("Done ")) {
            this.command = "done";
            int x = s.indexOf("done");
            int y = s.indexOf("done");
            String[] sArr = s.substring((x >= 0 ? x : y) + 5).split("[\\D]+");
            this.task = sArr[0];
            this.containsTask = true;

        }
        else if (s.contains("todo ") || s.contains("Todo ")) {
            this.command = "todo";
            this.containsTask = true;
            this.task = s;

        } else if (s.contains("event ") || s.contains("Event ")) {
            this.command = "event";
            this.containsTask = true;

        } else  if (s.contains("deadline ") || s.contains("Deadline ")) {
            this.command = "deadline";
            this.containsTask = true;
        }
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
