package duke;

public class AddCommand extends Command {
    String[] words;

    public AddCommand(String[] words) {
        this.words = words;
    }
    
    private void printAddedOutput(Task t, TaskList tasks, Ui ui) {
        ui.printDivider();
        ui.printMsg("Mr Camel has added: " + t);
        ui.printMsg("\tNumber of tasks: " + tasks.getTasklist().size());
        ui.printDivider();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String command = this.words[0];
        Task t = new Task("null");
        try {
            switch (command) {
                case "todo":
                    t = tasks.addToDo(this.words[1]);
                    break;
                case "deadline":
                    t = tasks.addDeadline(this.words[1], this.words[2]);
                    break;
                case "event":
                    t = tasks.addEvent(this.words[1], this.words[2]);
                    break;
            }
        } catch (DukeException e) {
            ui.printError(e);
        }
        
        printAddedOutput(t, tasks, ui);
        super.execute(tasks, ui, storage);
    }
}
