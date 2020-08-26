public class Duke {
    public static final String HORIZONTAL_LINE = "____________________________________________________________" + "\n";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }
    
    public void run() {
        Parser.echo(ui, taskList, storage);
    }

    public static void main(String[] args) {
        new Duke(".//.//.//savedTasks.txt").run();
    }
}