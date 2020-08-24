package Ultron.Commands;

import Ultron.Exceptions.ExceptionType;
import Ultron.Parser;
import Ultron.TaskList;
import Ultron.Tasks.Task;
import Ultron.UI;
import Ultron.Storage;
import Ultron.Exceptions.UltronException;


public class DeleteCommand extends Command{

    public DeleteCommand(String arguments){
        super(false, arguments);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws UltronException{

        //Initialise index
        int index = Parser.parseInteger(this.getArguments());

        //Check if the index is out of range
        if (index >= taskList.size()) {

            //Throw an Ultron exception if it is out of range
            throw new UltronException("delete",
                    Integer.toString(index + 1),
                    ExceptionType.INVALID_ARGUMENT);
        }

        //Get the task
        Task tsk = taskList.get(index);

        //Remove the task
        taskList.remove(index);

        //Print the delete message
        System.out.printf("What are you doing removing this?!?!\n  "
                        + "%s\nNow you have %d burdens%n",
                tsk,
                taskList.size());
    }
}
