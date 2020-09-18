import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Parser {
    /**
     * Helps to parse commands from the scanner, that tells the bot what actions
     * to take
     *
     * @param list
     * @param store
     * @throws DukeException
     * @throws FileNotFoundException
     */

    public String commandParser(String command, TaskList list, Storage store, Command action) {
        ArrayList<Task> storage = list.getTasks();
        try {
            assert !command.equals("") : "Please type in your command.";
            if (command.equals("list")) {
                return action.list(command, list, storage);
            } else if (command.startsWith("done")) {
                return action.done(command, store, storage);
            } else if (command.startsWith("todo")) {
                return action.todo(command, store, storage);
            } else if (command.startsWith("deadline")) {
                return action.deadline(command, store, storage);
            } else if (command.startsWith("event")) {
                return action.event(command, store, storage);
            } else if (command.equals("bye")) {
                return action.bye();
            } else if (command.startsWith("delete")) {
                return action.delete(command, store, storage);
            } else if (command.startsWith("find")) {
                return action.find(command, storage);
            } else if (command.startsWith("undo")) {
                return action.undo(store, storage);
            } else {
                throw new DukeException(command);
            }
        } catch (DukeException | FileNotFoundException e) {
            return e.getMessage();
        }
    }
}
