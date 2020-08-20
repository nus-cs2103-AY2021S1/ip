package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LevelSix {
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
                if (next.equals("list")) {
                    excecuteList();
                } else if (parsecommand(next).get("type").equals("done")) {
                    excecuteDone(parsecommand(next));
                } else if (parsecommand(next).get("type").equals("delete")) {
                    excecuteDelete(parsecommand(next));
                } else if (parsecommand(next).get("type").equals("todo")) {
                    excecuteToDo(parsecommand(next));
                } else if (parsecommand(next).get("type").equals("deadline")) {
                    excecuteDeadline(parsecommand(next));
                } else if (parsecommand(next).get("type").equals("event")) {
                    excecuteEvent(parsecommand(next));
                } else {
                    excecuteHandle(new Exception("The command is not found"));
                }
            } catch (CommandNotFoundException commandNotFoundexException) {
                //System.out.println(commandNotFoundexException.getMessage());
                excecuteHandle(commandNotFoundexException);
            } catch (TaskNotFoundException taskNotFoundException) {
                excecuteHandle(taskNotFoundException);
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


    static void excecuteToDo(HashMap<String, String> map) {
        //String result = "Got it. I have added this task:\n";

        excecuteAdd(new ToDo(map.get("description")));
    }

    static void excecuteDeadline(HashMap<String, String> map){
        excecuteAdd(new Deadline(map.get("description"), map.get("time")));
    }

    static void excecuteEvent(HashMap<String, String> map) {
        //String result = "Got it. I have added this task:\n";
        excecuteAdd(new Event(map.get("description"), map.get("time")));
    }

    static void excecuteHandle(Exception exception) {
        //String result = "Got it. I have added this task:\n";
        System.out.println(Duke.makeBlock(exception.getMessage()));
    }

    public static void excecuteDone(HashMap<String, String> map) throws TaskNotFoundException {
        int count = Integer.parseInt(map.get("number"));
        if(count > tasks.size() || count <= 0) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task");
        } else {
            tasks.get(count - 1).markAsCompleted();
            System.out.println(Duke.makeBlock("Nice! I have marked this task as done:\n" + String.valueOf(count) + "." + tasks.get(count - 1).toString()));
        }

    }

    public static void excecuteDelete(HashMap<String, String> map) throws TaskNotFoundException {
        int count = Integer.parseInt(map.get("number"));
        if(count > tasks.size() || count <= 0) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task");
        } else {
            Task task = tasks.remove(count - 1);
            System.out.println(Duke.makeBlock("Noted. I have removed this task:\n" +
                    String.valueOf(count) +
                    "." + task.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list."));
        }
    }

    public static void excecuteExit() {
        System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
    }

    public static HashMap<String, String> parsecommand(String command) throws CommandNotFoundException {

        HashMap<String, String> map = new HashMap<>();
        //map.put("type", "add");
        command = command.strip();
        if (command.equals("bye")) {
            map.put("type", "exit");
        } else if (command.equals("list")) {
            map.put("type", "list");
        } else if (command.split(" ")[0].equals("done")) {
            evaluateCompleteOrDelete(map, command);

        } else if (command.split(" ")[0].equals("delete")) {
            evaluateCompleteOrDelete(map, command);
        }  else if (command.split(" ")[0].equals("todo")) {
            if (command.strip().split(" ").length == 1) {
                throw new CommandNotFoundException("The description for todo should not be empty");
            } else {
                map.put("type", "todo");
                map.put("description", command.split("\\s+", 2)[1]);
            }

        } else if (command.split(" ")[0].equals("deadline")) {
            evaluateAddCommand(map, " /by ", command);

        } else if (command.split(" ")[0].equals("event")) {
            evaluateAddCommand(map, " /at ", command);
        } else {
            throw new CommandNotFoundException("The command is not found");
        }

        return map;
    }
    public static void evaluateAddCommand(HashMap<String, String> map, String string, String command) throws CommandNotFoundException {
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
                map.put("type", command.split(" ")[0]);
                map.put("description", description);
                map.put("time", time);
            }
        } else {
            throw new CommandNotFoundException("The command is not found");
        }
    }

    public static void evaluateCompleteOrDelete(HashMap<String, String> map, String command) throws CommandNotFoundException {
        String type = command.split(" ")[0];
        //System.out.println(type);
        try {
            if(command.split(type).length == 0) {
                throw new CommandNotFoundException("The " + type + " command needs to contain the number label of the task to be completed");
            }
            int number = Integer.parseInt(command.split("\\s+", 2)[1]);
            map.put("type", type);
            map.put("number", String.valueOf(number));

        } catch (NumberFormatException exception) {
            throw new CommandNotFoundException("The " + type + " command should be in the format:\n" + type + " number label of the task to be completed");
        }
    }
}
