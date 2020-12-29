package duke;

import java.io.IOException;

public class Duke{

    private static final String GREET_TEXT = "Hello World!";
    private TaskList taskList;
    private UI userInterface = new UI();
    
    private String getGreetText() {
        return GREET_TEXT;
    }
    
    String getResponse(String input)  {
        String command = parseInput(input);
        
        if (isExitCommand(command)) {
            writeDataToFile(taskList);
            return getGoodbyeMessage();
        } 
        
        if (isListCommand(command)) {
            return listAllTasks();
        } 
        
        if (isFindCommand(command)) {
            String keyword = getKeyWord(command);
            return listAllSearchResults(keyword);
        }
        
        if (isCompletedCommand(command)) {
            Task task = getTask(command);
            task.setDone();
            return getTaskCompletionMessage(task);
        } 
        
        if (isAddTagCommand(command)) {
            addTag(command);
            return "ok tagged";
        }
        
        if (isDeleteCommand(command)){
            if (isInvalidDeleteCommand(command)) {
                throw new IllegalUserInputException("Please specify the correct argument number");
            }
            return deleteTask(command);
        } else {
            return storeInput(input, taskList, userInterface);
        }
    }
    
    public Duke() {
        try {
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

                } else if (command.split("\\s+")[0].equals("find"))  {
                    String keyword = Parser.parseKeyWord(command);
                    UI.listTasks(taskList.search(keyword));
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
    
    
    
    public static String storeInput(String command, TaskList taskList,UI userInterface) {
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
        
        assert task!=null : "Task should not be null";
        taskList.addTask(task);
        return userInterface.taskAddedMessage(task);
    }
    
    private String parseInput(String input) {
        return UI.parseInput(input);
    }
    private boolean isExitCommand(String command) {
        return command.equals("bye");
    }
    
    private void writeDataToFile(TaskList taskList) {
        try {
            Storage.writeToFile(taskList);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e);
        }
    }
    
    private String getGoodbyeMessage() {
        return userInterface.goodbye();
    }
    
    private boolean isListCommand(String command) {
        return command.equals("list");
    }
    
    private String listAllTasks() {
        return userInterface.listTasks(taskList);
    }
    
    private boolean isFindCommand(String command) {
        return command.split("\\s+")[0].equals("find");
    }
    
    private String getKeyWord (String command) {
        return Parser.parseKeyWord(command);
    }
    
    private String listAllSearchResults(String keyword) {
        return UI.listTasks(taskList.search(keyword));
    }
    
    private boolean isCompletedCommand(String command) {
        return command.split("\\s+")[0].equals("done");
    }
    
    private Task getTask(String command) {
        return taskList.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
    }
    
    private String getTaskCompletionMessage(Task task) {
        return userInterface.taskCompletedMessage(task);
    }
    
    private boolean isDeleteCommand(String command) {
        return command.split("\\s+")[0].equals("delete");
    }
    
    private boolean isAddTagCommand(String command) {
        return command.split("\\s+")[0].equals("tag");
    }
    
    private boolean isInvalidDeleteCommand(String command) {
        return command.split("\\s+").length != 2;
    }
    
    private String deleteTask(String command) {
        try {
            int i = Integer.parseInt(command.split("\\s+")[1]);
            Task.decrementTask();
            Task task = taskList.get(i-1);
            taskList.deleteTaskIndex(i-1);
            return userInterface.taskDeletedMessage(task);
        } catch (TaskListError e) {
            return e.getDetails();
        }
    }
    
    private void addTag(String command) {
        try {
            int i = Integer.parseInt(command.split("\\s+")[1]);
            String tag = command.split("\\s+")[2];
            Task task = taskList.get(i - 1);
            task.addTag(tag);
        } catch (TaskListError e) {
            System.out.println("Invalid");
        } catch (Exception e) {
            System.out.println("Invalid output format");
        }
    }
}
