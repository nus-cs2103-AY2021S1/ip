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

        ui.addSentence("\t- todo <task>");
        ui.addSentence("\t\tadds <task> to the list");

        ui.addSentence("\t- event <event> /at YYYY-MM-DD");
        ui.addSentence("\t\tadds <event> on <date>");

        ui.addSentence("\t- deadline <task> /by YYYY-MM-DD");
        ui.addSentence("\t\tadds <task> due on <date>");

        ui.addSentence("\t- list");
        ui.addSentence("\t\tlists out all tasks");

        ui.addSentence("\t- delete 3");
        ui.addSentence("\t\tdeletes the 3rd task in the list");

        ui.addSentence("\t- done 3");
        ui.addSentence("\t\tmarks the 3rd task as done");

        ui.addSentence("\t- find <word>");
        ui.addSentence("\t\tfinds tasks that contain <word>");

        ui.addSentence("\t- bye");
        ui.addSentence("\t\texits the app");

        return ui.getResponse();
    }
}
