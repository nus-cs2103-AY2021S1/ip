package main.java;

import java.util.HashMap;
import java.util.Scanner;

public class LevelFour extends LevelThree {
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
            } else if(parsecommand(next).get("type").equals("todo")){
                excecuteToDo(parsecommand(next));
            } else if(parsecommand(next).get("type").equals("deadline")) {
                excecuteDeadline(parsecommand(next));
            } else if(parsecommand(next).get("type").equals("event")) {
                excecuteEvent(parsecommand(next));
            } else {
                excecuteNotFound();
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


    public static void excecuteAdd(Task task) {
        if(numberOfTasks < 100){

            tasks[numberOfTasks] = task;
            numberOfTasks = numberOfTasks + 1;
            String result = "Got it. I have added this task:\n  " + task.toString() + "\nNow you have " + numberOfTasks + " tasks in the list. ";
            System.out.println(Duke.makeBlock(result));
        } else if(numberOfTasks >= 100) {
            System.out.println(Duke.makeBlock("The task list cannot have a length of more than 100"));
        }
    }


    static void excecuteToDo(HashMap<String, String> map) {
        //String result = "Got it. I have added this task:\n";
        excecuteAdd(new ToDo(map.get("description")));
    }

    static void excecuteDeadline(HashMap<String, String> map) {
        excecuteAdd(new Deadline(map.get("description"), map.get("time")));
    }

    static void excecuteEvent(HashMap<String, String> map) {
        //String result = "Got it. I have added this task:\n";
        excecuteAdd(new Event(map.get("description"), map.get("time")));
    }

    static void excecuteNotFound() {
        //String result = "Got it. I have added this task:\n";
        System.out.println(Duke.makeBlock("The command is not found"));
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

    public static HashMap<String, String> parsecommand(String command) {
        HashMap<String, String> map = new HashMap<>();
        //map.put("type", "add");

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
                map.put("type", "null");
                map.put("description", command);
            }
        }  else if (command.split(" ")[0].equals("todo")) {
            map.put("type", "todo");
            map.put("description", command.split(" ", 2)[1]);
        } else if (command.split(" ")[0].equals("deadline") || command.split(" ")[0].equals("event")) {

            map.put("type", command.split(" ")[0]);
            String content = (command.split(" ", 2)[1]);
            String description = content.split(" /")[0];
            String time;
            if(command.split(" ")[0].equals("deadline")) {
                time = content.split(" /by ", 2)[1];
            } else {
                time = content.split(" /at ", 2)[1];
            }
            map.put("description", description);
            map.put("time", time);
        } else {
            map.put("type", "null");
        }
        return map;
    }
}
