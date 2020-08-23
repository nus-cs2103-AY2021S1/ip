public class Jimmy {
    
    private Parser parser;
    private Ui ui;

    public Jimmy() {
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public void run() {
        ui.print(ui.greet());
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.process();
                if (command.equals("bye"))
                    isExit = true;
                String reply = parser.parse(command);
                ui.print(reply);
            } catch (JimmyException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main (String[] args) {
        new Jimmy().run();
    }
}