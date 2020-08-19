public class Echo {
    private String command;
    private boolean shouldExit;

    Echo(String command) {
        this.command = command;
        this.shouldExit = false;
    }

    public String getResponse() {
        if (this.command.equals("bye")) {
            this.shouldExit = true;
            return Exit.getExitMessage();
        } else {
            return this.command;
        }
    }

    public boolean toExit() {
        return this.shouldExit;
    }
}
