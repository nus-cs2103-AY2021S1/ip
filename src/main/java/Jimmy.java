
public class Jimmy {
    
    //private Parser parser;
    private final Ui ui;
    private final Storage storage;
    private final TaskList planner;

    public Jimmy() {
        //this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage();
        this.planner = new TaskList();
    }

    public void run() {
        ui.print(ui.greet());
        storage.loadList(planner);
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.process();
                if (command.equals("bye"))
                    isExit = true;
                String reply = Parser.parse(planner, command);
                ui.print(reply);
            } catch (JimmyException e) {
                ui.print(e.getMessage());
            }
        }
        storage.updateList(planner);
    }

    public static void main (String[] args) {
        new Jimmy().run();
    }
}