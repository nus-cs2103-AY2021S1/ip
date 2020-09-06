public class FindTagCommand extends Command {
    private String command;

    FindTagCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyFindTagException, DukeNoMatchesTagExcpetion {
        String message = "";
        try {
            String[] findParts = command.split(" ");
            if (findParts.length == 1) {
                throw new DukeEmptyFindTagException(command);
            } else {
                message = ui.printFoundTagsTasks(findParts[1], tasklist.getTasks());
            }
        } catch (DukeEmptyFindTagException e) {
            message = e.getMessage();
        }
        assert tasklist.numOfTasks() > 0;
        return message;
    }
}
