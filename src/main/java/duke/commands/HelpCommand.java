package duke.commands;

import duke.DukeException;
import duke.util.OutputUi;
import duke.util.Storage;
import duke.util.TaskList;

public class HelpCommand extends Command {

    public String execute(TaskList tasks, OutputUi ui, Storage storage) throws DukeException {
        ui.reset();
        ui.addSentence("pingu help page!");
        ui.addSentence("commands to try:");

        ui.addSentence("\ttodo <task>");
        ui.addSentence("\t\tadds <task> to the list");

        ui.addSentence("\tevent <event> /at 2015-01-01");
        ui.addSentence("\t\tadds <event> on 1 Jan 2015");

        ui.addSentence("\tdeadline <task> /by 2015-01-01");
        ui.addSentence("\t\tadds <task> due on 1 Jan 2015");

        ui.addSentence("\tlist");
        ui.addSentence("\t\tlists out all tasks");

        ui.addSentence("\tdelete 3");
        ui.addSentence("\t\tdeletes the 3rd task in the list");

        ui.addSentence("\tdone 3");
        ui.addSentence("\t\tmarks the 3rd task as done");

        ui.addSentence("\tfind <word>");
        ui.addSentence("\t\tfinds tasks that contain <word>");

        ui.addSentence("\tbye");
        ui.addSentence("\t\texits the app");

        return ui.getResponse();
    }
}
