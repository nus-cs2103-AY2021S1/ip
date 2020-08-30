package duke.parser;

import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidKeyException;
import duke.exceptions.InvalidRequestException;
import duke.tasks.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interpret the command and do the instruction.
 */
public class Parser {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    static TaskList taskList;

    /** Construct a new paerser.
     * No parameter. Assign a new TaskList to taskList.
     */
    public Parser(){
        taskList = new TaskList();
    }

    /**
     * Print out the greetings.
     */
    public static void greet(){

        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");

    }

    /**
     * Print out the goodbye words.
     */
    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print out the command received.
     * @param command Take in a command.
     */
    public static void echo(String command){
        System.out.println(command);
    }

    /**
     * Process a command sentence
     * @param command Take in the command to be processed.
     * @throws Exception Throws an exception if the command can not be interpreted.
     */
    public static void processCommand(String command) throws Exception {

        if(command.equals("list")){

            taskList.printTaskList();

        }else{

            String[] words = command.split(" ");

            int numberOfWords = words.length;

            if(numberOfWords == 0){
                throw new InvalidRequestException("Command is empty. Please enter a valid command.");
            }

            if(words[0].equals("done")){

                if(numberOfWords == 1){
                    throw new InvalidRequestException("Which task would you like to mark as done?");
                }
                if(numberOfWords > 2){
                    throw new InvalidRequestException("Sorry. I can only handle one task at a time.");
                }

                Integer index = Integer.parseInt(words[1]);

                if(taskList.findListSize() < index){
                    throw new InvalidRequestException("I could not find this task, please enter a valid task index.");
                }
                if(index < 0){
                    throw new InvalidRequestException("Task index is invalid, please enter a valid one.");
                }

                taskList.markAsDone(index);

            }else if(words[0].equals("delete")){

                if (numberOfWords == 1) {
                    throw new InvalidRequestException("What task would you like to delete from the list?");
                }

                if(numberOfWords > 2){
                    throw new InvalidRequestException("Sorry, I can only handle one task at a time.");
                }

                Integer index = Integer.parseInt(words[1]);

                if(taskList.findListSize() < index){
                    throw new InvalidRequestException("I could not find this task, please enter a valid task index.");
                }
                if(index < 0){
                    throw new InvalidRequestException("Task index is invalid, please enter a valid one.");
                }

                taskList.deleteTask(index);

                int size = taskList.findListSize();


                if(size == 0){
                    System.out.println("Now your task list is empty.");
                }
                else if(size == 1){
                    System.out.println("Now you have 1 task in the list.");
                }
                else{
                    System.out.println("Now you have " + size + " tasks in the list");
                }

            }else if(words[0].equals("find")){

                if(words.length>2){
                    throw new InvalidKeyException("Sorry, I can only handle one keyword.");
                }

                ArrayList<Task> tasksFound = taskList.findTask(words[1]);
                if(tasksFound.size()==0){
                    System.out.println("Sorry, no task related to "+words[1]+ " is found.");
                }else{
                    System.out.println("Here are the tasks found: ");
                    for(Task task: tasksFound){
                        System.out.println(task.toString());
                    }
                }

            } else{

                Task newTask;

                if(words[0].equals("todo")){

                    if(numberOfWords == 1){
                        throw new InvalidRequestException("What is the Todo that you would like to be added to list?");
                    }

                    String name = command.split(" ", 2)[1];

                    newTask = new Todo(name);

                }else if(words[0].equals("deadline")){

                    if(numberOfWords == 1){
                        throw new InvalidDeadlineException("What is the Deadline that you would like to be added to list?");
                    }

                    String content = command.split(" ", 2)[1];

                    if(content.split(" /by ").length < 2){
                        throw new InvalidDeadlineException("Please enter both the name as well as the due date of the deadline!");
                    }

                    String name = content.split(" /by ")[0];

                    String dueDate = content.split(" /by ")[1];

                    TimeConverter timeConverter = new TimeConverter();

                    String formattedDueDate = timeConverter.convertTime(dueDate);

                    newTask = new Deadline(name, formattedDueDate);

                }else if(words[0].equals("event")){

                    if(numberOfWords == 1){
                        throw new InvalidEventException("What is the event that you would like to be added to list?");
                    }

                    String content = command.split(" ", 2)[1];

                    if(content.split(" /at ").length < 2){
                        throw new InvalidEventException("Please enter both the name as well as the due date of the deadline!");
                    }

                    String name = content.split(" /at ")[0];

                    String timePeriod = content.split(" /at ")[1];

                    newTask = new Event(name, timePeriod);

                }else{

                    throw new InvalidRequestException("Sorry, I could not understand your command. Please say another one!");

                }

                taskList.addTask(newTask);

            }
        }
    }

    /**
     * Process the whole chunk of input command.
     */
    public void processInput(){

        greet();

        Scanner input = new Scanner(System.in);

        String command;

        while(input.hasNext()){

            try{
                command = input.nextLine();

                if(command.equals("bye")){

                    exit();

                    input.close();

                    break;

                }else{

                    processCommand(command);

                }
            } catch(Exception e){

                System.out.println(e.getMessage());

            }

        }
    }


}
