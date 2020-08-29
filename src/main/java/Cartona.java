public class Cartona {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    Cartona() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        this.ui = new Ui();
        this.storage = new Storage("./saved/tasklist.txt");
    }

    public static void main(String[] args) {
        Cartona cartona = new Cartona();
        Ui ui = new Ui();
        ui.printWelcomeMessage();

        cartona.taskList = cartona.storage.getListFromStorage();

        String nextInput = "";
        while (true) {
            String nextLine = ui.getNextLineInput();

            try {
                Command nextCommand = cartona.parser.parseCommand(nextLine);
                nextCommand.execute(cartona.taskList, cartona.ui, cartona.storage);

                if (nextCommand.isExitCmd())  {
                    break;
                }
            } catch (InvalidInputException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (CartonaException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }
}