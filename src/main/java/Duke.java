public class Duke {

    Ui ui;
    Storage storage;
    TaskList tasks;
    Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser(tasks);
    }

    public void run(){
        ui.read(parser);
        storage.save(tasks.getTodoList());
    }

    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }
}
