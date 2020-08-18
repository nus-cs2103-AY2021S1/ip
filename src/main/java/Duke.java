package main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static void printBorder(){
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        printBorder();
        System.out.println("Hello I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printBorder();
        String input = userInput.nextLine();

        while(!input.equals("bye")){
            if(input.equals("list")){
                printBorder();
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++){
                    System.out.println((i+1) + ". " + tasks.get(i).printTask());
                }
                printBorder();
            }
            else if(input.contains("done")){
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.get(index).markAsDone();
                printBorder();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index).printTask());
                printBorder();
            }
            else {
                printBorder();
                Task t = new Task(input);
                tasks.add(t);
                System.out.println("added: " + input + "\n");
                printBorder();
            }
            input = userInput.nextLine();
        }
        printBorder();
        System.out.println("Bye. Hope to see you again soon!");
        printBorder();
    }
}