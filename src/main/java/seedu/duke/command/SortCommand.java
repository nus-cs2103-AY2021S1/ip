package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.ui.Ui;

public class SortCommand extends Command {
    public SortCommand(String[] words) {
        super(words);
    }

    @Override
    public void execute(TaskList ls, Ui ui) {
        String[] input = words[1].split(" /by ");
        assert input.length > 1 : "Should have two parts to the command.";
        ui.printResult("lol");
    }
}
