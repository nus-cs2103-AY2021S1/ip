package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;

import java.util.*;

public class FindCommand extends Command {

    public static String TASK_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static String NO_TASK_FOUND = "There are no matching task";


    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 4) {
            throw new InvalidInputException("Please select task to find!");
        }

        String keywords = super.input.substring(5);
        List<Task> tasksFound = new ArrayList<>();
        for (Task t: tasks.getTaskList()) {
            if (t.getWordsInTask().contains(keywords)) {
                tasksFound.add(t);
            }
        }
        if (tasksFound.size() == 0) {
            ui.printMessage(NO_TASK_FOUND);
        } else {
            for (int j = 0; j < tasksFound.size(); j++) {
                ui.printMessage((j + 1) + "." + tasksFound.get(j));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
