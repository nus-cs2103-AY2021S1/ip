public class AddCommand extends Command {

    private String input;
    private InputCommand inputCommand;

    public AddCommand(String input, InputCommand inputCommand) {
        this.exit = false;
        this.input = input;
        this.inputCommand = inputCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        switch (inputCommand) {
        case TODO:
            try {
                tasks.addList(new Todo(Parser.getTodoDescription(input)));
                ui.addedMessage(new Todo(Parser.getTodoDescription(input)), tasks.getListSize());
                storage.saveListToFile(tasks.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the todo description...");
            }
            break;
        case DEADLINE:
            try {
                tasks.addList(new Deadline(Parser.getDeadlineStrings(input)[0],
                        Parser.getDeadlineStrings(input)[1]));
                ui.addedMessage(new Deadline(Parser.getDeadlineStrings(input)[0],
                        Parser.getDeadlineStrings(input)[1]), tasks.getListSize());
                storage.saveListToFile(tasks.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the deadline description...");
            }
            break;
        case EVENT:
            try {
                tasks.addList(new Event(Parser.getEventTimeStrings(input)[0],
                        Parser.getEventTimeStrings(input)[1]));
                ui.addedMessage(new Event(Parser.getEventTimeStrings(input)[0],
                        Parser.getEventTimeStrings(input)[1]), tasks.getListSize());
                storage.saveListToFile(tasks.getList());
            } catch (Exception e) {
                throw new DukeException("Banana! Something's wrong with the event description...");
            }
            break;
        default:
            throw new DukeException("Give me a valid banana (input)!");
        }
    }

}