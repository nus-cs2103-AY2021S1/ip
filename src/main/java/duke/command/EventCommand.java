package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command that creates an event task.
 */
public class EventCommand extends UserCommand {
    /**
     * @param userInput user's input.
     */
    public EventCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task> ls = taskList.getTasks();
        String[] eventArr = userInput.split("/", 2);
        String at = eventArr[1].substring(eventArr[1].indexOf("at") + 3);
        String eventString = eventArr[0].substring(6);
        LocalDate localEventDate = TimeFormatter.localDate(at);
        Event event = new Event(eventString, localEventDate);
        ls.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
        System.out.format("Now you have %d tasks in the list\n", ls.size());

    }
}
