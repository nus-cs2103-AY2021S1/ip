package command;

import duke.DukeExceptions;
import duke.Parser;
import duke.TaskList;

public class CreateNewAliasCommand extends Command{

    public CreateNewAliasCommand(String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser) {
        String message;
        try {
            String newMapping = parser.createNewAlias(this.parameters);
            message = "Master new mapping has been created: \n" + newMapping;
            return new Result(message, executedSuccessfully);
        } catch (ArrayIndexOutOfBoundsException e){
            message = "Master please fill up the command fully : alias <Alias name> <Command name>";
            return new Result(message, executedSuccessfully);
        } catch (DukeExceptions.AliasAlreadyExistException e) {
            message = "Master the alias " + e.getMessage() + " already exist!";
            return  new Result(message, executedUnsuccessfully);
        }
    }
}
