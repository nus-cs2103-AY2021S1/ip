public class Duke {
    private Ui ui;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    private void run() {
        ui.greet();
        boolean isBye = false;
        while (!isBye) {
            String input = ui.receive();
            try {
                Handler.process(input, ui, tasks);
            } catch (DukeException e) {
                // ...
            } finally {
                isBye = Handler.isBye(input);
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}