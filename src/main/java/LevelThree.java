package main.java;

import java.util.HashMap;
import java.util.Scanner;

public class LevelThree {
    public static Task[] tasks = new Task[100];
    static int numberOfTasks = 0;

    public static void interact() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();

        while(!next.equals("bye")) {
            //System.out.println(next);
            if(next.equals("list")) {
                excecuteList();
            } else if(parseCommand(next).get("type").equals("done")){
                excecuteDone(parseCommand(next));
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
            result = result + String.valueOf(i + 1) + "." + tasks[i].toString() + "\n";
        }
        System.out.println(Duke.makeBlock(result));
    }

    public static void excecuteAdd(String item) {
        if(numberOfTasks < 100){
            System.out.println(Duke.makeBlock("added: " + item));
            tasks[numberOfTasks] = new Task(item);
            numberOfTasks = numberOfTasks + 1;
        } else if(numberOfTasks >= 100) {
            System.out.println(Duke.makeBlock("The task list cannot have a length of more than 100"));
        }
    }

    public static void excecuteDone(HashMap<String, String> map) {
        int count = Integer.parseInt(map.get("number"));
        if(count > numberOfTasks || count <= 0) {
            System.out.println(Duke.makeBlock("There is no such task"));
        } else {
            tasks[count - 1].markAsCompleted();
            System.out.println(Duke.makeBlock("Nice! I have marked this task as done:\n" + String.valueOf(count) + "." + tasks[count - 1].toString()));
        }

    }

    public static void excecuteExit() {
        System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
    }

    public static HashMap<String, String> parseCommand(String command) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "add");

        if(command.equals("bye")) {
            map.put("type", "exit");
        } else if(command.equals("list")) {
            map.put("type", "list");
        } else if(command.split(" ")[0].equals("done") && command.split(" ").length == 2) {
            try {
                int number = Integer.parseInt(command.split(" ")[1]);
                map.put("type", "done");
                map.put("number", String.valueOf(number));
            } catch (NumberFormatException exception) {
                map.put("type", "add");
                map.put("content", command);
            }
        } else {
            map.put("content", command);
        }
        return map;
    }
}
