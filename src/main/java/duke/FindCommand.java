package duke;

import java.util.List;

public class FindCommand {
    private String keyWord;
    private TaskList tasks;
    private Ui userInteract = new Ui();

    FindCommand(String keyWord, TaskList tasks) {
        this.keyWord = keyWord;
        this.tasks = tasks;
    }

    /**
     * Returns a string showing list of matching tasks.
     *
     * @return String which is the response for users showing the list of matching tasks
     * @throws DukeException
     */

    public String handleFind() throws DukeException {
        List<Task> matchedTasks = this.tasks.findMatchingTask(keyWord);
        return this.userInteract.showFind(matchedTasks);
    }
}
