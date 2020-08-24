public class Parser {
    public Parser() {
        
    }
    
    public static Command parse(String nextCommand) throws DukeException {
        String[] nextCommandArr = nextCommand.split(" ", 2);
        Command next;
        EnumCommand enumCommand;
        
        String command = nextCommandArr[0];
        
        if (command.equals("bye")) {
            enumCommand = EnumCommand.BYE;
        } else if (command.equals("list")) {
            enumCommand = EnumCommand.LIST;
        } else if (command.equals("done")) {
            enumCommand = EnumCommand.DONE;
        } else if (command.equals("todo")) {
            enumCommand = EnumCommand.TODO;
        } else if (command.equals("deadline")) {
            enumCommand = EnumCommand.DEADLINE;
        } else if (command.equals("event")) {
            enumCommand = EnumCommand.EVENT;
        } else if (command.equals("delete")) {
            enumCommand = EnumCommand.DELETE;
        } else {
            throw new DukeException("Sorry, I don't know what that means~");
        }
        
        switch (enumCommand) {
            case BYE: 
                next = new ByeCommand();
                break;
            case LIST: 
                next = new ListCommand();
                break;
            case DONE: 
                next = new DoneCommand(nextCommandArr);
                break;
            case TODO: 
                next = new TodoCommand(nextCommandArr);
                break;
            case DEADLINE: 
                next = new DeadlineCommand(nextCommandArr);
                break;
            case EVENT: 
                next = new EventCommand(nextCommandArr);
                break;
            case DELETE: 
                next = new DeleteCommand(nextCommandArr);
                break;
            default:
                throw new DukeException("Sorry, I don't know what that means~");
        }
        
        return next;
        
        
    }
}
