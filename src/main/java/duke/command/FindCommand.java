package duke.command;

import duke.Storage;
import duke.Task;
import duke.Tasklist;
import duke.UserInterface;
import duke.exception.DukeIndexException;
import duke.exception.DukeListException;

import java.io.IOException;

public class FindCommand extends Command {

    private String wordToFind;

    public FindCommand(String wordToFind) {
        this.wordToFind = wordToFind;
    }

    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeListException, DukeIndexException, IOException {
        Tasklist tempTaskList = new Tasklist(new Storage());
        tempTaskList.clearList();
        for (int i = 0; i < tasklist.getTaskSize(); i++) {
            Task taskInCheck = tasklist.get(i);
            if (taskInCheck.getTask().contains(wordToFind)) {
                tempTaskList.addTask(taskInCheck);
            }
        }

        if (tempTaskList.getTaskSize() == 0) {
            throw new DukeListException("Your search result yields nothing.");
        }
        
        new ListCommand().execute(tempTaskList, new UserInterface());
    }
}
