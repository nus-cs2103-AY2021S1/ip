public class Duke {

    private static TaskList taskList = new TaskList();

    /**
     * Initialises the application by attempting to load a savefile and assign a new taskList from it.
     */
    public void initialise() {
        try {
            taskList = Storage.loadFromMem();
        } catch (DukeException e) {
            Ui.printWithLines(e.toString() + "\n");
        }
    }

    public void getResponse() {
        Ui.startInput(taskList);
    }

}
