public class FindCommand extends Command {
    private String command;

    FindCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyFindException, DukeNoMatchesExcpetion {
        String message = "";
        try {
            String[] findParts = command.split(" ");
            if (findParts.length == 1) {
                throw new DukeEmptyFindException(command);
            } else {
                message = ui.printKeywordTasks(findParts[1], tasklist.getTasks());
            }
        } catch (DukeEmptyFindException e) {
            message = e.getMessage();
        } catch (DukeNoMatchesExcpetion e) {
            message = e.getMessage();
        }
        return message;
    }
}
