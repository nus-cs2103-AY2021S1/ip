package duke.command;

import duke.Storage;
import duke.Task;
import duke.Tasklist;
import duke.UserInterface;
import duke.exception.DukeListException;

/**
 * FindCommand class to execute find command when given by user.
 * @author Kor Ming Soon
 */
public class FindCommand extends Command {

    private String wordToFind;

    /**
     * Constructor for FindCommand
     * @param wordToFind word give by user to filter out relevant tasks.
     */
    public FindCommand(String wordToFind) {
        this.wordToFind = wordToFind;
    }

    /**
     * Execution command to begin search for with relevant word given.
     * @param tasklist list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeListException When the input index does not match the list.
     */
    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeListException {
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
