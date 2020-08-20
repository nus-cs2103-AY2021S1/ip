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

                if(words[0].equals("done")){

                    Integer index = Integer.parseInt(words[1]);

                    listOfTasks.get(index - 1).markAsDone();

                }else{

                    Task newTask;

                    if(words[0].equals("todo")){

                        String name = command.split(" ", 2)[1];

                        newTask = new Todo(name);

                    }else if(words[0].equals("deadline")){

                        String content = command.split(" ", 2)[1];

                        String name = content.split(" /by ")[0];

                        String dueDate = content.split(" /by ")[1];

                        newTask = new Deadline(name, dueDate);

                    }else if(words[0].equals("event")){

                        String content = command.split(" ", 2)[1];

                        String name = content.split(" /by ")[0];

                        String timePeriod = content.split(" /by ")[1];

                        newTask = new Deadline(name, timePeriod);

                    }else{

                        newTask = new Task(command);

                    }

                    listOfTasks.add(newTask);

                    int taskSize = listOfTasks.size();

                    System.out.println("Got it. I've added this task: ");

                    System.out.println(newTask.toString());

                    System.out.println("Now you have" + taskSize + "tasks in the list.");

                }
            }
        }

    }
}
