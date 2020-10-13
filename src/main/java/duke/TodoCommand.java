package duke;

public class TodoCommand {
    private Ui userInteract = new Ui();
    private String[] words;
    private TaskList tasks;

    TodoCommand(String[] words, TaskList tasks) {
        this.words = words;
        this.tasks = tasks;
    }

    /**
     * Handles todo task
     *
     * @return String which is duke response for Todo command
     */
    public String handleToDO() throws DukeException {
        if (words.length == 1) {
            throw DukeException.emptyDescriptionException();
        }
        String todoTask = "";
        for (int i = 1; i < words.length; i++) {
            if (i != words.length - 1) {
                todoTask = todoTask + words[i] + " ";
            } else {
                todoTask = todoTask + words[i];
            }
        }
        ToDo newToDo = new ToDo(todoTask);
        this.tasks.add(newToDo);
        return this.userInteract.showAdd(newToDo, this.tasks);
    }
}
