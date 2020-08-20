package main.java;

import java.util.*;

public class Duke {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    static ArrayList<Task> listOfTasks = new ArrayList<>();

    public static void greet(){
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String command){
        System.out.println(command);
    }


    public static void main(String[] args) {

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

                }else if(command.equals("list")){

                    System.out.println("Here are the tasks in your list:");

                    int number = listOfTasks.size();

                    for(int i = 0; i < number; i++){

                        listOfTasks.get(i).showTask(i+1);

                    }
                    continue;
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

                        if(listOfTasks.size() < index){
                            throw new InvalidRequestException("I could not find this task, please enter a valid task index.");
                        }
                        if(index < 0){
                            throw new InvalidRequestException("Task index is invalid, please enter a valid one.");
                        }

                        listOfTasks.get(index - 1).markAsDone();

                    }else{

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

                            newTask = new Deadline(name, dueDate);

                        }else if(words[0].equals("event")){

                            if(numberOfWords == 1){
                                throw new InvalidEventException("What is the event that you would like to be added to list?");
                            }

                            String content = command.split(" ", 2)[1];

                            if(content.split(" /by ").length < 2){
                                throw new InvalidEventException("Please enter both the name as well as the due date of the deadline!");
                            }

                            String name = content.split(" /by ")[0];

                            String timePeriod = content.split(" /by ")[1];

                            newTask = new Deadline(name, timePeriod);

                        }else{

                            throw new InvalidRequestException("Sorry, I could not understand your command. Please say another one!");

                        }

                        listOfTasks.add(newTask);

                        int taskSize = listOfTasks.size();

                        System.out.println("Got it. I've added this task: ");

                        System.out.println(newTask.toString());

                        System.out.println("Now you have" + taskSize + "tasks in the list.");

                    }
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

        }

    }
}
