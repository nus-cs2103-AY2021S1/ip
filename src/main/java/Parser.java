public class Parser {
    // all valid actions
    private enum Action {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        DELETE
    }
    
    private final TaskList lst;
    private final Ui ui;
    
    public Parser(TaskList lst, Ui ui) {
        this.lst = lst;
        this.ui = ui;
    }
    
    private void validateCommandDesc(String desc, Action type) throws DukeException {
        String result = desc.trim();
        if (result.isEmpty()) {
            throw new DukeException("Command description cannot be empty");
        }
        if (type == Action.DEADLINE) {
            if (!result.contains("/by") || result.split("/by").length <= 1 || result.split("/by")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
        if (type == Action.EVENT) {
            if (!result.contains("/at") || result.split("/at").length <= 1 || result.split("/at")[0].isEmpty()) {
                throw new DukeException("Be sure to include a task description and date in the correct format.");
            }
        }
    }
    
    private void validateTaskNum(int taskNum) throws DukeException {
        if (taskNum > lst.size() || taskNum <= 0) {
            throw new DukeException("You have no such task. Please check your task number.");
        }
    }
    
    public Command parse(String command) {
        Command resultantCommand = null;
        
        try {
            String[] splitCommand = command.split(" ", 2);
            Action action = Action.valueOf(splitCommand[0].toUpperCase());
            String value = splitCommand.length > 1 ? splitCommand[1] : "";
            switch (action) {
                case LIST:
                    resultantCommand = new ListCommand();
                    break;
                case TODO:
                    this.validateCommandDesc(value, Action.TODO);
                    resultantCommand = new AddCommand("TODO", value);
                    break;
                case DEADLINE:
                case EVENT:
                    String[] splitValue;
                    if (action == Action.DEADLINE) {
                        this.validateCommandDesc(value, Action.DEADLINE);
                        splitValue = value.split("/by ");
                        resultantCommand = new AddCommand("DEADLINE", splitValue[0], splitValue[1]);
                    } else {
                        this.validateCommandDesc(value, Action.EVENT);
                        splitValue = value.split("/at ");
                        resultantCommand = new AddCommand("EVENT", splitValue[0], splitValue[1]);
                    }
                    break;
                case DONE:
                case DELETE:    
                    int taskNum = Integer.parseInt(value);
                    this.validateTaskNum(taskNum);
                    resultantCommand = action == Action.DONE ? new DoneCommand(taskNum) : new DeleteCommand(taskNum);
                    break;
                case BYE:
                    resultantCommand = new ExitCommand();
                    break;
            }
        } catch (IllegalArgumentException ex) {
            ui.showError("I can't help you with that request, try something else.");
        } catch (DukeException ex) {
            ui.showError(ex.getMessage());
        }

        return resultantCommand;
    }
}
