package command;

import java.io.IOException;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * Command to delete an alias to command map
 *
 * @author Ryanl Lim
 */
public class DelAliasCommand extends Command{

    public DelAliasCommand(String ...parameters){
        super(parameters);
    }

    @Override
    public Result execute(TaskList taskList, Parser parser, Storage aliasStorage, Storage taskStorage, Ui ui) {
        String message;
        try {
            String alias = parser.deleteAlias(this.parameters);
            aliasStorage.save(parser);
            return new Result(ui.deletedAliasMessage(alias), executedSuccessfully);
        } catch (ArrayIndexOutOfBoundsException e){
            return new Result(ui.noAliasMessage(), executedUnsuccessfully);
        } catch (DukeExceptions.AliasDoesNotExistException e){
            return  new Result(ui.aliasDoesNotExistMessage(e.getMessage()), executedUnsuccessfully);
        } catch (IOException e) {
            return new Result(ui.fileIssueMessage(), executedUnsuccessfully);
        }
    }

}
