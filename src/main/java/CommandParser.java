import operation.Operation;
import operation.AddOperation;
import operation.ExitOperation;

public class CommandParser {
    public Operation parse(String command) {
        switch(command) {
            case "bye":
                return new ExitOperation(command);
            default :
                return new AddOperation(command);
        }
    }
}