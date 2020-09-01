public class FindCommand extends Command {
    private String command;

    FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasklist, UI ui) throws DukeEmptyFindException, DukeNoMatchesExcpetion {
        try {
            String[] findParts = command.split(" ");
            if (findParts.length == 1) {
                throw new DukeEmptyFindException(command);
            } else {
                ui.printKeywordTasks(findParts[1], tasklist.getTasks());
            }
        } catch (DukeEmptyFindException e) {
            ui.printFormattedMessage("OOPS!!! Empty find body!");
        } catch (DukeNoMatchesExcpetion e) {
            ui.printFormattedMessage("OOPS!!! No matches found!");
        }
    }
}
