package duke;

public class Duke{

    Ui ui;
    Storage storage, archiveStorage;
    TaskList tasks, archives;
    Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        archiveStorage = new Storage("data/duke.txt.archive");
        tasks = new TaskList(storage.load());
        archives = new TaskList(archiveStorage.load());
        parser = new Parser(tasks, archives);
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        archiveStorage = new Storage(filePath+".archive");
        tasks = new TaskList(storage.load());
        archives = new TaskList(archiveStorage.load());
        parser = new Parser(tasks, archives);
    }

    public String getResponse(String input){
        String tmp = ui.read(input, parser);
        storage.save(tasks.getTodoList());
        archiveStorage.save(archives.getTodoList());
        System.out.println("todo: " + tasks.getTodoList().size());
        System.out.println("archive: " + archives.getTodoList().size());
        return tmp;
    }

    public void save(){
        storage.save(tasks.getTodoList());
        archiveStorage.save(archives.getTodoList());
    }

    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.getResponse("");
        duke.save();
    }
}
