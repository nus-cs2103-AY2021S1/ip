package main.java;

public class EventCommand extends Command {

    public EventCommand() {
        names = new String[] { "event" };
    }

    @Override
    public void execute(String str) {
        String[] splitStr = str.split(" /at ", 2);
        String description = splitStr[0];
        String time = splitStr[1];
        Event newEvent = new Event(UIPrint.eventIcon, description, time);

        Duke.tasks.add(newEvent);
        Duke.reportNewTask(newEvent);
    }
}
