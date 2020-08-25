package duke;

import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        // if length is not 2, nothing was passed in after 'find'
        if (parsedCommand.length != 2) {
            throw new DukeException("NANI??! Tell us something to find!\n");
        }

        String inputDescription = parsedCommand[1].trim();
        boolean taskFound = false;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i)
                    .getDescription()
                    .contains(inputDescription)) {
                taskFound = true;
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
        if (!taskFound) {
            System.out.println("Nothing matches your search!\n");
        } else {
            System.out.println(); // print empty line for easier visualisation
        }
    }
}
