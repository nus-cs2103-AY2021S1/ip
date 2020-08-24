class DateCommand extends Command {
    
    private final String argument;

    public DateCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {
        ui.printToConsole(taskList.taskListToDateFilteredString(argument));
    }
}
