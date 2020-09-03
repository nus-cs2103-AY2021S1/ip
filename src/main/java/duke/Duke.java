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
    public void run() throws Exception {
        ui.start();
    }

    public static void main(String[] args) throws Exception {
        new Duke("/Users/juanlie/cs2103t/pw2/ip/data").run();
    }
}
