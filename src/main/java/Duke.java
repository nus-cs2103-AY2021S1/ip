public class Duke {
    private Ui ui;

    Duke(String filepath) {
        Storage storage = new Storage(filepath);
        this.ui = new Ui(new TaskList(storage.load()), storage);
    }

    public void run() {
        ui.getInput();
    }

    public static void main(String[] args) {
        new Duke("ip_data.txt").run();
    }
}
