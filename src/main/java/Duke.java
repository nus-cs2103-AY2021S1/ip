public class Duke {

    private DukeSaver saver;
    private TaskList taskList;
    private UI ui;
    private Parser parser;

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public Duke(String savePath) {
        saver = new DukeSaver(savePath);
        taskList = new TaskList();
        ui = new UI();
        parser = new Parser(ui, taskList, saver);
    }

    private void run() {
        saver.loadData(taskList);
        ui.greet();
        while (true) {
            String response = ui.prompt();
            try {
                parser.handleResponse(response);
            } catch (DukeException ex) {
                ui.print(ex.toString());
            }
        }
    }
}
