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
//        else if (s.contains("add") || s.contains("Add")) {
//            this.command = "add";
//            int x = s.indexOf("add");
//            int y = s.indexOf("Add");
//            this.task = s.substring((x >= 0 ? x : y) + 4);
//            this.containsTask = true;
//        }
        else {
            this.command = "add";
            this.containsTask = true;
            this.task = s;
        }
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
