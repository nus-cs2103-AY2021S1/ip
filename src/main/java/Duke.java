package main.java;

import java.util.*;

public class Duke {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

//    static ArrayList<Task> listOfTasks = new ArrayList<>();
//
    static Scanner input = new Scanner(System.in);
//
//    static boolean isExited = false;
//
    public static void greet(){
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }


    public static void main(String[] args) {

        greet();

        String command;

        while(input.hasNext()){
            command = input.nextLine();
            if(command.equals("bye")){
                exit();
                break;
            }else{
                System.out.println(command);
                continue;
            }
        }
        
    }
}
