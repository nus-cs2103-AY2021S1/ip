package duke.command;

import duke.*;

public class FindCommand extends Command {

    public FindCommand(String command, String extra) {
        super(command, extra);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        int counter = 0;
        ui.printFind(tasks);
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().contains(extra)) {
                System.out.println(i + 1 + "." + tasks.getTask(i));
                counter++;
            }
        }
        if (counter == 0 ) {
            ui.printNoMatch(extra);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FindCommand;
    }
}
