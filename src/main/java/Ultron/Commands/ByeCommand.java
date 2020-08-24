package Ultron.Commands;

import Ultron.Exceptions.UltronException;
import Ultron.Exceptions.ExceptionType;
import Ultron.TaskList;
import Ultron.UI;
import Ultron.Storage;

public class ByeCommand extends Command{

    public ByeCommand(String arguments){
        super(true, arguments);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws UltronException {

        if (this.getArguments().trim().length() > 0){
            throw new UltronException("bye", ExceptionType.TOO_MUCH_ARGUMENTS);
        }

        //Save the storage
        storage.writeAll(taskList.getList());

        System.out.printf("Clearly you were not worth my time.\n");
    }
}
