package command;

import duke.DukeExceptions;
import duke.Parser;
import duke.TaskList;

public class DelAliasCommand extends Command{

    public DelAliasCommand(String ...parameters){
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser) {
        String message;
        try {
            String newMapping = parser.deleteAlias(this.parameters);
            message = "Master new mapping has been created: \n" + newMapping;
            return new Result(message, executedSuccessfully);
        } catch (ArrayIndexOutOfBoundsException e){
            message = "Master please enter an alias to delete!";
            return new Result(message, executedUnsuccessfully);
        } catch (DukeExceptions.AliasDoesNotExistException e){
            message = "Master the alias " + e.getMessage() + " does not exist!";
            return  new Result(message, executedUnsuccessfully);
        }
    }

}
