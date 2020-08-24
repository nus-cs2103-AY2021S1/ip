import java.util.ArrayList;

//Posh Version of Duke
public class Duke {
    //Characteristics of Duke
    private boolean isChatting;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    //Constructor
    public Duke(String file) {
        //Characteristic of Duke
        this.isChatting = true;
        this.ui = new Ui();
        this.taskList = new TaskList(new ArrayList<>(), this.ui);
        this.storage = new Storage(file);
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }

    //Initialise Duke
    private void startChat() {
        //Initialisation Message
        this.ui.showGreeting();
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (DukeInvalidUserInputException e) {
            this.ui.showDukeError(e);
        }

        while (this.isChatting) {
            //Obtain user input
            String user_input = this.ui.getUserInput();

            try {
                Command user_command = this.parser.parseCommand(user_input);
                user_command.execute();
            } catch (DukeException e) {
                this.ui.showDukeError(e);
            }
        }
    }

    public static void main(String[] args) {
        //Initialise Duke
        Duke chatBot = new Duke("data/duke.txt");
        //Start chatting with Bot
        chatBot.startChat();
    }
}
