package command;

import java.io.IOException;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * Command to create a new alias to command map.
 *
 * @author Ryan Lim
 */
public class CreateNewAliasCommand extends Command{

    public CreateNewAliasCommand(String ...parameters) {
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        try {
            String mapping = parser.createNewAlias(this.parameters);
            aliasStorage.save(parser);
            return new Result(ui.newMappingMessage(mapping), executedSuccessfully);
        } catch (ArrayIndexOutOfBoundsException e){
            return new Result(ui.inCompleteAliasCommandMessage(), executedSuccessfully);
        } catch (DukeExceptions.AliasAlreadyExistException e) {
            return  new Result(ui.aliasAlreadyExistMessage(e.getMessage()), executedUnsuccessfully);
        } catch (IOException e) {
            return new Result(ui.fileIssueMessage(), executedUnsuccessfully);
        }
    }
}
