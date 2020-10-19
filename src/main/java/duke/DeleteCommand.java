package duke;

public class DeleteCommand {
    private String[] words;
    private TaskList tasks;
    private Ui userInteract = new Ui();

    DeleteCommand(String[] words, TaskList tasks) {
        this.tasks = tasks;
        this.words = words;
    }

    /**
     * Returns a response of the delete task.
     * @return  String which is the response for user.
     * @throws DukeException exception occurs when the user did not provide an index.
     */
    public String handleDelete() throws DukeException {
        if (words.length == 1) {
            throw DukeException.operationException();
        }
        int index = Integer.parseInt(words[1]) - 1;
        if (index + 1 > tasks.size()) {
            throw DukeException.invalidIndexException();
        } else {
            return this.userInteract.showDelete(index, this.tasks);
        }
    }
}
