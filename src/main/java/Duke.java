public class Duke {

    private TaskList taskList;
    private Parser parser;
    private Storage storage;
    private Ui ui;

    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser(taskList);
        this.ui = new Ui();
        try{
            this.storage = new Storage(taskList,parser);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.printStarting();
        while(taskList.isUpdating()) {
            String[] fullCommand = ui.readCommand();
            parser.ParseCommand(fullCommand);
            ui.printLine();
        }
    }

    public void stop() {
        try{
            storage.saveFile();
        }catch(DukeException e){
            ui.showSavingError();
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
        duke.stop();
    }

}