import java.util.ArrayList;

public class Echo {
    private ArrayList<String> commands;
    private boolean shouldExit;

    Echo() {
        this.commands = new ArrayList<>();
        this.shouldExit = false;
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public String replyUser() {
        String command = this.commands.get(this.commands.size() - 1);
        if (command.equals("bye")) {
            this.shouldExit = true;
            return Exit.getExitMessage();
        } else if (command.equals("list")) {
            commands.remove(commands.size() - 1);
            String response = "";
            for (int i = 0; i < commands.size(); i++) {
                response += String.format("%d. %s%n", i + 1, commands.get(i));
            }
            return response;
        } else {
            return String.format("added: %s%n", command);
        }
    }

    public boolean toExit() {
        return this.shouldExit;
    }
}
