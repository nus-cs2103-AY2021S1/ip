package duke;

public class DoneCommand {
    private String[] words;
    private TaskList tasks;
    private Ui userInteract = new Ui();

    DoneCommand(String[] words, TaskList tasks) {
        this.tasks = tasks;
        this.words = words;
    }

    /**
     * Returns a response of the done task.
     * @return  String which is the response for user.
     * @throws DukeException exception occurs when the user did not provide an index.
     */
    public String handleDone() throws DukeException {
        if (words.length == 1) {
            throw DukeException.operationException();
        }
        int number = Integer.parseInt(words[1]) - 1;
        if (number + 1 > tasks.size() || number < 0) {
            throw DukeException.invalidIndexException();
        } else {
            return this.userInteract.showDone(number, this.tasks);
        }
    }
}
