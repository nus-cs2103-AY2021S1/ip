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
        else {
            // TODO: add check here to ensure no gibberish is passed to todolist.
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
