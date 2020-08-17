package dependencies.parser;

class Checker {
    private String s;
    private String command;
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
            String[] sArr = s.substring((x >= 0 ? x : y) + 5).split("\\s+");
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

    private boolean checkEvent(String s) {
        return s.contains("/at");
    }

    private boolean checkDeadline(String s) {
        return s.contains("/by");
    }

    public String getCommand() {
        return this.command;
    }

    public String getTask() {
        return this.task;
    }

    public boolean containsTask() {
        return this.containsTask;
    }

    public boolean isValidCommand(String s) {
        return false;
    }




}
