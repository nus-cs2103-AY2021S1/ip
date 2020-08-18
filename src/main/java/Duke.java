package main.java;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String[] tasks = new String[100];
        int index = 0;
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n");
        System.out.println("Hello I'm Duke\n");
        System.out.println("What can I do for you?\n");
        System.out.println("____________________________________________________________\n");
        String input = userInput.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println("____________________________________________________________\n");
                for(int i = 0; i < index; i++){
                    System.out.println((i+1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________\n");
            }
            else {
                System.out.println("____________________________________________________________\n");
                tasks[index] = input;
                index++;
                System.out.println("added: " + input + "\n");
                System.out.println("____________________________________________________________\n");
            }
            input = userInput.nextLine();
        }
        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");

    }
}