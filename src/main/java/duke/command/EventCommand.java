package duke.command;

import duke.exceptions.DukeException;
import duke.storage.ArrayListToTextConverter;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.timeformatter.TimeFormatter;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.List;

public class EventCommand extends UserCommand{
    public EventCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        List<Task> ls = taskList.getTaskList();
        String[] eventArr = userInput.split("/", 2);
        String at = eventArr[1].substring(eventArr[1].indexOf("at") + 3);
        String eventString = eventArr[0].substring(6);
        LocalDate localEventDate = TimeFormatter.localDate(at);
        Event event = new Event(eventString, localEventDate);
        ls.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
        System.out.format("Now you have %d tasks in the list\n", ls.size());
//        ArrayListToTextConverter.convertArrayListToText(ls);

    }
}
