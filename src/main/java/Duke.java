public class Duke {
    
    static final String filepath = "duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    
    public Duke (String filepath) {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            storage.pullList(taskList);
        } catch (Exception e) {
            ui.printError(e);
        }
    }
    
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String nextCommand = ui.readCommand();
                Command c = Parser.parse(nextCommand);
                c.execute(taskList, ui, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }
    
    public static void main (String[] args) {
        new Duke(filepath).run();
    }

    
    
    

//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    
}