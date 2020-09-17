package duke;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Duke extends Application{

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label(getGreetText()); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    
    private String getGreetText() {
        return "Hello World!";
    }

    private TaskList taskList;
    private UI userInterface = new UI();
    
    

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input)  {
        String command = input.toLowerCase();
        try {
            if (command.equals("bye")) {
                Storage.writeToFile(taskList);
                return userInterface.goodbye();
            } else if (command.equals("list")) {

               return userInterface.listTasks(taskList);

            } else if (command.split("\\s+")[0].equals("find")) {
                String keyword = Parser.parseKeyWord(command);
                return UI.listTasks(taskList.search(keyword));
                
            } else if (command.split("\\s+")[0].equals("done")) {
                Task task = taskList.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
                task.setDone();
                return userInterface.taskCompletedMessage(task);
                
            }else if (command.split("\\s+")[0].equals("delete")){
                if (command.split("\\s+").length != 2) {
                    throw new IllegalUserInputException("PLease specify the correct argument number");
                }
                try {
                    int i = Integer.parseInt(command.split("\\s+")[1]);
                    Task.decrementTask();
                    Task task = taskList.get(i-1);
                    taskList.deleteTaskIndex(i-1);
                    return userInterface.taskDeletedMessage(task);
                } catch (TaskListError e) {
                    System.out.println(e.getDetails());
                }
            } else {
                   return storeInput(input, taskList, userInterface);
            }
            
        }catch (IOException | IllegalArgumentException e) {
            System.out.println(e);
        }
        return "Something went wrong!";
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
}
