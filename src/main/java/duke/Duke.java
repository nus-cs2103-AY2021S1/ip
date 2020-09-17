package duke;

public class Duke {
    private Storage s;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke class
     * @param filePath
     * @throws Exception
     */
    public Duke(String filePath) throws Exception {

        s = new Storage(filePath);
        s.create();
        try {
            tasks = new TaskList(s.toArrayList());
            ui = new Ui(tasks, s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Runs the whole program.
     * @throws Exception
     */
    public void run() {
        ui.start();
    }

    public String runCommand(String command) throws Exception {
        return Parser.processCommand(command, tasks, ui);
    }

    public static void main(String[] args) throws Exception {
        new Duke("data").run();
    }
}
