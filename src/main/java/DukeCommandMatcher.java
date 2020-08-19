import Exceptions.*;
import Util.Constants;
import Util.UtilFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class DukeCommandMatcher {
    private static final List<String> commandList = new ArrayList<>(Arrays.asList(Constants.LISTPATTERN,
                                                                  Constants.EXITPATTERN, Constants.DONEPATTERN,
                                                                  Constants.TODOPATTERN, Constants.DEADLINEPATTERN,
                                                                  Constants.EVENTPATTERN));
    private List<Task> taskList = new ArrayList<>();

    public String matchCommand(String command) throws CommandNotFoundException, NullCommandException,
            LackOfTimeException, NullCommandContentException, DoneOutOfBoundException {
        if(Objects.equals(command, "")){
            throw new NullCommandException(command);
        }

        //get the first command
        String[] splitCommand = command.split("\\s+", 2);
        //check if the command is in the list
        for(String commandPattern: commandList){
            //the command is in the list
            if(UtilFunction.matchPattern(commandPattern, splitCommand[0])){
               switch(commandPattern){
                   case Constants.EXITPATTERN:
                       return handleExit();
                   case Constants.LISTPATTERN:
                       return handleList();
                   case Constants.DONEPATTERN:
                       return handleDone(splitCommand[1]);
                   case Constants.TODOPATTERN:
                       return handleTodo(splitCommand);
                   case Constants.DEADLINEPATTERN:
                       return handleDeadline(splitCommand);
                   case Constants.EVENTPATTERN:
                       return handleEvent(splitCommand);
               }
            }
        }
        throw new CommandNotFoundException(command);
    }

    private String handleExit(){
        System.out.println("Farewell/再見/さようなら～～");
        return "EXIT";
    }

    private String handleAdd(Task task){
        taskList.add(task);
        int numOfTask = taskList.size();
        System.out.println("Got it. I've added this task:\n" +
                "   " + task +  '\n' +
                "Now you have " + numOfTask + (numOfTask > 1 ? " tasks " : " task ") + "in the list.");
        return "Task added";
    }

    private String handleList(){
        if(taskList.size() == 0){
            System.out.println("😝You don't have any task in the schedule yet~~\n" +
                    "use todo/deadline/event command to create your tasks~");
        }
        for(int i =1; i< taskList.size()+1; i++){
            System.out.println(i + ". " + taskList.get(i-1));
        }
        return "List implemented";
    }

    private String handleDone(String targetTask) throws DoneOutOfBoundException {
        int targetTaskPos = Integer.parseInt(targetTask) - 1;
        if(targetTaskPos >= this.taskList.size() ){
            throw new DoneOutOfBoundException("Target number of task out of bound", targetTaskPos + 1);
        }
        Task task = this.taskList.get(targetTaskPos);
        task.setStatus(true);
        System.out.println("Très bien!I have helped you marked task " + targetTask + " as done\n"
            + task);
        return "Task " + targetTask + " has been done";
    }

    private String handleTodo(String[] todoStr) throws NullCommandContentException{
        if(todoStr.length < 2){
            throw new NullCommandContentException("Description cannot be null", "Todo" );
        }
        String todoContent = todoStr[1];
        ToDo todo = new ToDo(todoContent);
        return handleAdd(todo);
    }

    private String handleDeadline(String[] deadlineStr) throws NullCommandContentException, LackOfTimeException {
        if(deadlineStr.length < 2){
            throw new NullCommandContentException("Description cannot be null", "Deadline" );
        }
        String deadlineContent = deadlineStr[1];
        String[] splitDeadLineStr = deadlineContent.split("/", 2);
        if(splitDeadLineStr.length < 2){
            throw new LackOfTimeException("The time cannot be empty", "Deadline" );
        }
        Deadline deadline = new Deadline(splitDeadLineStr[0], splitDeadLineStr[1]);
        return handleAdd(deadline);
    }

    private String handleEvent(String[] eventStr) throws NullCommandContentException, LackOfTimeException {
        if(eventStr.length < 2){
            throw new NullCommandContentException("Description cannot be null", "Event" );
        }
        String eventContent = eventStr[1];
        String[] splitEventStr = eventContent.split("/", 2);
        if(splitEventStr.length < 2){
            throw new LackOfTimeException("The time cannot be empty", "Event" );
        }
        Event event = new Event(splitEventStr[0], splitEventStr[1]);
        return handleAdd(event);
    }

}
