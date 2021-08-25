public class Parser {

    private String taskName;
    private String command;
    private String deadlineOrTimeline;


    public Parser() {
    }

    public void parsing(String input) {
        String[] parsedInput = input.split(" ", 2);
        String eventType = parsedInput[0];
        this.command = eventType;

        if (this.command.equals("deadline")){
            String[] parsedAgain = parsedInput[1].split(" /by ", 2);
            this.taskName = parsedAgain[0];
            this.deadlineOrTimeline = parsedAgain[1];
        } else if (this.command.equals("event")) {
            String[] parsedAgain = parsedInput[1].split(" /at ", 2);
            this.taskName = parsedAgain[0];
            this.deadlineOrTimeline = parsedAgain[1];
        } else if (this.command.equals("todo")) {
            this.taskName = parsedInput[1];
        } else if (this.command.equals("bye")){

        } else if (this.command.equals("done")) {
            this.taskName = parsedInput[1];
        } else if (this.command.equals("remove")) {
            this.taskName = parsedInput[1];
        }
    }

    public String getCommand() {
        return this.command;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTimeline() {
        return this.deadlineOrTimeline;
    }
}
