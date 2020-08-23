package main.java.duke.command;

import main.java.duke.*;
import main.java.duke.task.Event;

public class EventCommand extends Command {

    String[] commandDetails;

    public EventCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String at = commandDetails[1].split(" ", 2)[1];
        System.out.println(" Got it. I've added this task: ");
        Event event = new Event(commandDetails[0], at);
        tasks.getTasks().add(event);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. ",
                event, tasks.getTasks().size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
