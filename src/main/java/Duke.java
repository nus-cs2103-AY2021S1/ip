import java.io.IOException;


public class Duke {
    private TaskList taskList;
    private UI userInterface;
    
    public Duke() {
        try {
            this.userInterface = new UI();
            this.taskList = new TaskList(Storage.loadFile());
        } catch (IOException e) {
            System.out.println("An error has occurred: " + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
    
    public void run() {
        userInterface.greetUser();
        do{
            try {
                String input = Parser.getUserCommand();
                String command = input.toLowerCase();

                if (command.equals("bye")) {

                    Storage.writeToFile(taskList);
                    userInterface.goodbye();
                    break;

                } else if (command.equals("list")) {

                    userInterface.listTasks(taskList);

                } else if (command.split("\\s+")[0].equals("done")) {
                    Task task = taskList.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
                    task.setDone();
                    userInterface.taskCompletedMessage(task);
                }else if (command.split("\\s+")[0].equals("delete")){
                    if (command.split("\\s+").length != 2) {
                        throw new IllegalUserInputException("PLease specify the correct argument number");
                    }
                    try {
                        int i = Integer.parseInt(command.split("\\s+")[1]);
                        Task.decrementTask();
                        Task task = taskList.get(i-1);
                        taskList.deleteTaskIndex(i-1);
                        userInterface.taskDeletedMessage(task);
                    } catch (TaskListError e) {
                        System.out.println(e.getDetails());
                        continue;
                    }

                } else {
                    storeInput(input, taskList, userInterface);
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println(e);
                continue;
            }
        } while (true);
        
    }
    
    
    
    public static void storeInput(String command, TaskList taskList,UI userInterface) {
        String cmd1 = command.split("\\s+")[0];
        String cmd2;
        Task task =null;
        if (cmd1.equals("deadline")) {
            cmd2 =  command.substring(cmd1.length()+1);
            try {
                task = Parser.parseDeadline(cmd2);
            }catch (Exception e) {
               System.out.println( "Please specify time in format YYYY-MM-DD hh:hh \n for instance : 2019-10-15 18:00");
            }
        } else if (cmd1.equals("todo")) {
            if (command.split("\\s+").length == 1) {
                throw new IllegalUserInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            cmd2 =  command.substring(cmd1.length()+1);
            task = new Todo(cmd2,false);
        } else if (cmd1.equals("event")) {
            cmd2 =  command.substring(cmd1.length()+1);
            task = Parser.parseEvent(cmd2);
        } else {
            throw new IllegalUserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (task != null) {
            taskList.addTask(task);
            userInterface.taskAddedMessage(task);
        }
        return;
    }
}
