public class Duke {

    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (Exception ex) {
                ui.show("\t " + ex.getMessage());
            }
        }

    }
}
