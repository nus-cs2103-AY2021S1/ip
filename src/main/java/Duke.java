public class Duke {
    private TaskList tasks;
    private Ui ui;
    String filePath;

    /**
     * Initialise Duke with the filePath.
     */
    public Duke() {
        Ui ui = new Ui();
        Storage storage = new Storage("./duke.txt");
        TaskList tasks = storage.readFile();
        this.filePath = "./duke.txt";
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Runs the Duke by initialise the Parser.
     */
    public void run() {
        Parser parser = new Parser(tasks, ui);
        parser.parser(filePath);
    }

    /**
     * Generates response from Duke based on user input.
     *
     * @param input Input from user.
     * @return Response by Duke.
     */
    public String getResponse(String input){
        Parser parser = new Parser(tasks, this.ui);
        String output = parser.parser(input, this.tasks);
        return "Duke: " + "\n" + output;
    }

    public static void main(String[] args){
        new Duke().run();
    }

}
