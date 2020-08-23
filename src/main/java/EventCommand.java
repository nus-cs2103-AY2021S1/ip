package main.java;

import java.util.ArrayList;

public class EventCommand extends Command {

    String[] commandDetails;

    public EventCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        String at = commandDetails[1].split(" ", 2)[1];
        System.out.println("~ \n Got it. I've added this task: ");
        Event event = new Event(commandDetails[0], at);
        tasks.add(event);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. \n~",
                event, tasks.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
