package duke;

import java.util.ArrayList;

public class Parser {

    public static int CONTINUE = 1, BYE = 2;
    public static int TODO = 0, DEADLINE = 1, EVENT = 2;
    protected Ui ui;

    /**
     * Construct a Parser object
     */
    public Parser(){
        ui = new Ui();
    }

    /**
     * Parses the input command
     * @param inputCommand the command inputed by user
     * @param list the TaskList object
     * @return an integer indicating whether to continue to run the app or stop the app
     */
    public int parse(String inputCommand, TaskList list){
        String[] command = inputCommand.split(" ");
        int ptr = 0;

        // if the user input is empty, continue the loop
        if(command.length <= 0 || inputCommand.equals("")){
            ui.showInputEmtyError();
            return CONTINUE;
        }

        while(command[ptr].equals("")) ptr++;

        if(command[ptr].equals("bye")){
            ui.showByeMessage();
            return BYE;
        }
        else if(command[ptr].equals("list")){
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            list.printList();
            System.out.println("____________________________________________________________");
            return CONTINUE;
        }
        else if(command[ptr].equals("done")){
            try{
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                System.out.println("____________________________________________________________");
                Task t = list.markTaskDone(taskNumber);
                if(t != null){
                    ui.showTaskMarkAsDone(t);
                }
                else{
                    ui.showTaskNumberExceed(taskNumber, list.getSize());
                }
                System.out.println("____________________________________________________________");
            }
            catch (Exception e){
                ui.wrongFormatAfterDone();
            }
            return CONTINUE;
        }
        else if(command[ptr].equals("delete")){
            try{
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                System.out.println("____________________________________________________________");
                Task t = list.delete(taskNumber);
                if(t == null){
                    ui.showTaskNumberExceed(taskNumber, list.getSize());
                }
                else{
                    ui.showDeleteMessage(t, list);
                }
                System.out.println("____________________________________________________________");
            }
            catch (Exception e){
                ui.wrongFormatAfterDelete();
            }
            return CONTINUE;
        }
        else if(command[ptr].equals("todo")){
            Todo newTodo = Todo.of(inputCommand);
            if(newTodo == null){
                ui.descriptionEmpty();
                return CONTINUE;
            }
            list.add(newTodo);
            ui.MessageAfterAdd(newTodo, TODO, list.getSize());
            return CONTINUE;
        }
        else if(command[ptr].equals("deadline")){
            Deadline deadline = Deadline.of(inputCommand);
            if(deadline == null){
                ui.wrongDeadlineFormat();
                return CONTINUE;
            }
            list.add(deadline);
            System.out.println("____________________________________________________________");
            if(deadline.getDate() == null){
                ui.wrongDateFormat();
            }
            if(deadline.getTime() == null){
                ui.wrongTimeFormat();
            }
            ui.MessageAfterAdd(deadline, DEADLINE, list.getSize());
            return CONTINUE;
        }
        else if(command[ptr].equals("event")){
            Event event = Event.of(inputCommand);
            if(event == null){
                ui.wrongEventFormat();
                return CONTINUE;
            }
            System.out.println("____________________________________________________________");
            list.add(event);
            if(event.getDate() == null){
                ui.wrongDateFormat();
            }
            if(event.getTime() == null){
                ui.wrongTimeFormat();
            }
            ui.MessageAfterAdd(event, EVENT, list.getSize());
            return CONTINUE;
        }
        else{
            ui.noSuchCommand();
            return CONTINUE;
        }
    }

}
