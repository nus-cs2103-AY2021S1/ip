import command.Command;

public class CommandAgent {
    private final DukeBuffer buffer;

    public CommandAgent(DukeBuffer buffer) {
        this.buffer = buffer;
    }

    public String handleCommand(Command command) {
        switch (command.sendRequest()) {
        case "end":
            return "Bye. Hope to see you again soon!";
        case "create":
            String name = command.getContent().get(0);
            this.buffer.addTask(new Task(name));
            return "added: " + name;
        case "retrieval":
            return this.buffer.showTasks();
        default:
            return "";
        }
    }
}
