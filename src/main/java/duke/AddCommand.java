package duke;

import java.io.IOException;

/**
 * Command when a new Task is to be added to TaskList
 */
public class AddCommand extends Command {
    String[] words;

    /**
     * Constructor
     * @param words Array that contains the different segments of a Task input
     */
    public AddCommand(String[] words) {
        this.words = words;
    }
    
    private void printAddedOutput(Task t, TaskList tasks, Ui ui) {
        ui.printDivider();
        ui.printMsg("Mr Camel has added: " + t);
        ui.printMsg("\tNumber of tasks: " + tasks.getTasklist().size());
        ui.printDivider();
    }

    /**
     * Adds a Todo, Event or Deadline object to Tasklist
     * @param tasks TaskList containing Tasks
     * @param ui Ui object that handles printing of any necessary output
     * @param storage Storage object that handles saving Tasks to hard disk
     * @throws DukeException
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
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
