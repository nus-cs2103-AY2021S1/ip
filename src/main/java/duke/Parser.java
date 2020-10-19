package duke;


public class Parser {
    protected boolean isEnd;
    protected TaskList tasks;
    private UserInput currentType = null;
    private Ui userInteract = new Ui();

    /**
     * Returns a parser which interprets the user's command
     * This is a constructor of parser which takes in two arguments of user interact class and task list
     * Utilizes user interact class to return a response string and adds new tasks into the task list
     *
     * @param userInteract A class that return different types of duke response
     * @param tasks        List of tasks
     * @return A parser
     */
    public Parser(Ui userInteract, TaskList tasks) {
        this.userInteract = userInteract;
        this.tasks = tasks;
        this.isEnd = false;
    }

    enum UserInput {
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE,
        BYE,
        LIST,
        FIND,
        HELP
    }


    /**
     * Returns a string of response from duke based on user's input
     *
     * @param userCommand User's command scanned by Ui.
     * @return A string of response from duke.
     * @throws DukeException Exceptions occur when the response is not ideal.
     */

    public String parse (String userCommand) throws DukeException {
        String[] words = userCommand.split(" ");
        String dukeOutput = "";
        if (userCommand.contains("@")) {
            throw DukeException.noReservedWordException();
        } else {
            String key = words[0].toLowerCase();
            if (key.equals("deadline")) {
                this.currentType = UserInput.DEADLINE;
                dukeOutput = new DeadlineCommand(words, tasks).handleDeadline();
            } else if (key.equals("todo")) {
                this.currentType = UserInput.TODO;
                dukeOutput = new TodoCommand(words, tasks).handleToDO();
            } else if (key.equals("event")) {
                this.currentType = UserInput.EVENT;
                dukeOutput = new EventCommand(words, tasks).handleEvent();
            } else if (key.equals("done")) {
                this.currentType = UserInput.DONE;
                dukeOutput = new DoneCommand(words, tasks).handleDone();
            } else if (key.equals("delete")) {
                this.currentType = UserInput.DELETE;
                dukeOutput = new DeleteCommand(words, tasks).handleDelete();
            } else if (key.equals("bye")) {
                this.currentType = UserInput.BYE;
                this.isEnd = true;
                dukeOutput = this.userInteract.showBye();
            } else if (key.equals("list")) {
                this.currentType = UserInput.LIST;
                dukeOutput = this.userInteract.showList(tasks);
            } else if (key.equals("find")) {
                this.currentType = UserInput.FIND;
                dukeOutput = new FindCommand(words[1], tasks).handleFind();
            } else if (key.equals("help")) {
                this.currentType = UserInput.HELP;
                dukeOutput = this.userInteract.showHelp();
            } else {
                throw DukeException.noResponseException();
            }
        }
        return dukeOutput;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }



}
