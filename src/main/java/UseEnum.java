package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UseEnum {
    public static ArrayList<Task> tasks = new ArrayList<>();
    //static int numberOfTasks = 0;

    public static void interact() {
        /*
        for(String string: ("a    b").split("b")) {
            System.out.println("." + string);
        }
        */
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();

        while (!next.equals("bye")) {
            //System.out.println(next);
            try {
                if (parsecommand(next).get("type").equals(Command.LIST)) {
                    excecuteList();
                } else if (parsecommand(next).get("type").equals(Command.DONE)) {
                    excecuteDone(parsecommand(next));
                } else if (parsecommand(next).get("type").equals(Command.DELETE)) {
                    excecuteDelete(parsecommand(next));
                } else if (parsecommand(next).get("type").equals(Command.ADD_TODO)) {
                    excecuteToDo(parsecommand(next));
                } else if (parsecommand(next).get("type").equals(Command.ADD_DEADLINE)) {
                    excecuteDeadline(parsecommand(next));
                } else if (parsecommand(next).get("type").equals(Command.ADD_EVENT)) {
                    excecuteEvent(parsecommand(next));
                } else {
                    excecuteHandle(new Exception("The command is not found"));
                }
            } catch (CommandNotFoundException commandNotFoundexException) {
                //System.out.println(commandNotFoundexException.getMessage());
                excecuteHandle(commandNotFoundexException);
            }
            next = scanner.nextLine();
        }
        //System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
        excecuteExit();

    }

    public static void excecuteList() {
        String result = "";
        for(int i = 0; i < tasks.size(); i = i + 1) {
            result = result + String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        System.out.println(Duke.makeBlock(result));
    }


    public static void excecuteAdd(Task task){


        tasks.add(task);

        String result = "Got it. I have added this task:\n  " + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
        System.out.println(Duke.makeBlock(result));

    }


    static void excecuteToDo(HashMap<String, Object> map) {
        //String result = "Got it. I have added this task:\n";

        excecuteAdd(new ToDo((String) map.get("description")));
    }

    static void excecuteDeadline(HashMap<String, Object> map){
        excecuteAdd(new Deadline((String) map.get("description"), (String) map.get("time")));
    }

    static void excecuteEvent(HashMap<String, Object> map) {
        //String result = "Got it. I have added this task:\n";
        excecuteAdd(new Event((String) map.get("description"), (String) map.get("time")));
    }

    static void excecuteHandle(Exception exception) {
        //String result = "Got it. I have added this task:\n";
        System.out.println(Duke.makeBlock(exception.getMessage()));
    }

    public static void excecuteDone(HashMap<String, Object> map) {
        int count = Integer.parseInt((String) map.get("number"));
        if(count > tasks.size() || count <= 0) {
            System.out.println(Duke.makeBlock("There is no such task"));
        } else {
            tasks.get(count - 1).markAsCompleted();
            System.out.println(Duke.makeBlock("Nice! I have marked this task as done:\n" + String.valueOf(count) + "." + tasks.get(count - 1).toString()));
        }

    }

    public static void excecuteDelete(HashMap<String, Object> map) {
        int count = Integer.parseInt((String) map.get("number"));
        if(count > tasks.size() || count <= 0) {
            System.out.println(Duke.makeBlock("There is no such task"));
        } else {
            Task task = tasks.remove(count - 1);
            System.out.println(Duke.makeBlock("Noted. I have removed this task:\n" +
                    String.valueOf(count) +
                    "." + task.toString() +
                    "Now you have " + tasks.size() + " tasks in the list."));
        }
    }

    public static void excecuteExit() {
        System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
    }

    public static HashMap<String, Object> parsecommand(String command) throws CommandNotFoundException {

        HashMap<String, Object> map = new HashMap<>();
        //map.put("type", "add");
        command = command.strip();
        if (command.equals("bye")) {
            map.put("type", "exit");
        } else if (command.equals("list")) {
            map.put("type", Command.LIST);
        } else if (command.split(" ")[0].equals("done")) {
            evaluateCompleteOrDelete(map, command, Command.DONE);

        } else if (command.split(" ")[0].equals("delete")) {
            evaluateCompleteOrDelete(map, command, Command.DELETE);
        }  else if (command.split(" ")[0].equals("todo")) {
            if (command.strip().split(" ").length == 1) {
                throw new CommandNotFoundException("The description for todo should not be empty");
            } else {
                map.put("type", Command.ADD_TODO);
                map.put("description", command.split("\\s+", 2)[1]);
            }

        } else if (command.split(" ")[0].equals("deadline")) {
            evaluateAddCommand(map, " /by ", command, Command.ADD_DEADLINE);

        } else if (command.split(" ")[0].equals("event")) {
            evaluateAddCommand(map, " /at ", command, Command.ADD_EVENT);
        } else {
            throw new CommandNotFoundException("The command is not found");
        }

        return map;
    }
    public static void evaluateAddCommand(HashMap<String, Object> map, String string, String command, Command commandtype) throws CommandNotFoundException {
        if (command.split(" ").length == 1) {
            throw new CommandNotFoundException("The description for event should not be empty");
        } else if (command.split(" ")[0].equals("event") || command.split(" ")[0].equals("deadline")) {


            String content = (command.split("\\s+", 2)[1]);

            if (content.strip().split(string).length <= 1) {
                throw new CommandNotFoundException("The description should be in the format:\n" + command.split(" ")[0] + " description" + string + "time");
            } else {
                String time;
                String description = content.split(string)[0];
                time = content.strip().split(string, 2)[1];
                map.put("type", commandtype);
                map.put("description", description);
                map.put("time", time);
            }
        } else {
            throw new CommandNotFoundException("The command is not found");
        }
    }

    public static void evaluateCompleteOrDelete(HashMap<String, Object> map, String command, Command commandType) throws CommandNotFoundException {
        String type = command.split(" ")[0];
        //System.out.println(type);
        try {

            if(command.split(type).length == 0) {
                throw new CommandNotFoundException("The " + type + " command needs to contain the number label of the task to be completed");
            }
            int number = Integer.parseInt(command.split("\\s+", 2)[1]);
            map.put("type", commandType);
            map.put("number", String.valueOf(number));

        } catch (NumberFormatException exception) {
            throw new CommandNotFoundException("The " + type + " command should be in the format:\n" + type + " number label of the task to be completed");
        }
    }
}
