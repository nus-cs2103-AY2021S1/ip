package duke;

public class Duke {
    private Storage s;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke class
     * @param filePath file path of the hard disk
     * @throws Exception when s.create faces an exception
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
     */
    public void run() {
        ui.start();
    }

    /**
     * Runs the command given by the user
     * @param command user input
     * @return String of Duke's reply
     * @throws Exception when processCommand can't parse the command
     */
    public String runCommand(String command) throws Exception {
        return Parser.processCommand(command, tasks, ui);
    }

    public static void main(String[] args) throws Exception {
        new Duke("data").run();
    }
}
