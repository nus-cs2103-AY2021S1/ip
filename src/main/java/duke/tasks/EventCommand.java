package duke.tasks;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event Command. This command handles the event input
 * from users.
 */
public class EventCommand extends Command {
    private String eventTask;

    /**
     * Constructor that stores the event string.
     * @param event input from user.
     */
    public EventCommand(String event) {
        this.eventTask = event;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        try {
            //add event task to list of tasks
            tasks.event(this.eventTask);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return Event.invalidInput();
        }

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);

        return tasks.eventString(this.eventTask);
    }

}
