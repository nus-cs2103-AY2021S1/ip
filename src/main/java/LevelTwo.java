package main.java;

import java.util.Scanner;

public class LevelTwo {
    static String[] tasks = new String[100];
    static int numberOfTasks = 0;

    public static void interact() {
        Scanner scanner = new Scanner(System.in);



        String next = scanner.nextLine();
        while(!next.equals("bye")) {
            if(next.equals("list")) {
                excecuteList();
            } else {
                excecuteAdd(next);
            }
            next = scanner.nextLine();
        }
        //System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
        excecuteExit();
    }

    public static void excecuteList() {
        String result = "";
        for(int i = 0; i < numberOfTasks; i = i + 1) {
            result = result + tasks[i] + "\n";
        }
        System.out.println(Duke.makeBlock(result));
    }

    public static void excecuteAdd(String item) {
        if(numberOfTasks < 100){
            System.out.println(Duke.makeBlock("added: " + item));
            tasks[numberOfTasks] = item;
            numberOfTasks = numberOfTasks + 1;
        } else if(numberOfTasks >= 100) {
            System.out.println(Duke.makeBlock("The task list cannot have a length of more than 100"));
        }
    }

    public static void excecuteExit() {
        System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
    }
}
