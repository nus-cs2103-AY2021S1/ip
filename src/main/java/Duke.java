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
                for(Task task: listOfTasks){
                    task.showName();
                }
                continue;
            }else{
                Task newTask = new Task(command);
                listOfTasks.add(newTask);
                System.out.println("added: " + command);
            }
        }

    }
}
