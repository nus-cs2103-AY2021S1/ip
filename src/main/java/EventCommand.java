public class EventCommand extends Command {
    private String command;

    EventCommand(String command) {
        this.command = command;
    }


    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeEmptyEventTimeException, DukeEmptyEventException {
        try {

            if (this.command.split(" ").length == 1) {
                throw new DukeEmptyEventException(this.command);
            }
            String eventer = Parser.stringBuilder(command.split(" "), 1, command.split(" ").length - 1);
            String[] eventParts = eventer.split(" /at ");
            if (eventParts.length == 1) {
                throw new DukeEmptyEventTimeException(command);
            }
            Event eventTask = new Event(eventParts[0], eventParts[1]);
            tasklist.addTask(eventTask);
            UI.printTaskAdd(eventTask, tasklist.numOfTasks());
        } catch (DukeEmptyEventTimeException e) {
            UI.printFormattedMessage("OOPS!!! The description of a event time cannot be empty.");
        } catch (DukeEmptyEventException e) {
            UI.printFormattedMessage("OOPS!!! The description of a event cannot be empty.");
        }

    }

}
