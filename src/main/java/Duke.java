public class Duke {
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        this.ui = new Ui();

        try {
            ui.greet();
            ui.getUserInput();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
