package seedu.duke;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] words) {
        super(words);
    }

    @Override
    public void execute(TaskList ls, Ui ui) throws DukeNotSureException {
        int number = Integer.parseInt(words[1]);
        if (number > ls.size()) {
            throw new DukeNotSureException("This task doesn't seem to exist? :s");
        } else {
            Task oldTask = ls.get(number - 1);
            ls.remove(number - 1);
            String thing = "Running away from your responsibilities huh. Deleted:" +
                    "\n" +
                    oldTask.getStatus().replaceAll("(?m)^", "\t") +
                    "\nNow you have " + ls.size() + " tasks in the list.";
            ui.printResult(thing);
        }
    }
}
