package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LevelSeven {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static final String FILE_PATH = "data/duke.txt";
    //static int numberOfTasks = 0;

    public static void interact() {
        try {
            File record = new File(FILE_PATH);

            if (record.createNewFile()) {
                excecuteHandle(new FileNotFoundException("Creating new file for record"));
            }

            try {
                readRecord(record);
            } catch (FileNotFoundException fileNotFoundException) {
                writeRecord();
                excecuteHandle(new FileNotFoundException("The previous record cannot be read. Cleaning the content for record"));
            } catch (IllegalArgumentException illegalArgumentException) {
                writeRecord();
                excecuteHandle(new FileNotFoundException("The previous record cannot be read. Cleaning the content for record"));
            }

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
                } catch (TaskNotFoundException taskNotFoundException) {
                    excecuteHandle(taskNotFoundException);
                } catch (IOException ioException) {
                    excecuteHandle(ioException);
                }
                next = scanner.nextLine();
            }
            //System.out.println(Duke.makeBlock(Duke.EXITMESSAGE));
            excecuteExit();
        } catch (IOException ioException) {
            excecuteHandle(ioException);
        }
    }

    public static void excecuteList() {
        String result = "";
        for(int i = 0; i < tasks.size(); i = i + 1) {
            result = result + String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        System.out.println(Duke.makeBlock(result));
    }

    public static void excecuteAdd(Task task) throws IOException {
        tasks.add(task);
        String result = "Got it. I have added this task:\n  " + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
        System.out.println(Duke.makeBlock(result));
        writeRecord();
    }


    static void excecuteToDo(HashMap<String, Object> map) throws IOException {
        //String result = "Got it. I have added this task:\n";
        excecuteAdd(new ToDo((String) map.get("description")));
    }

    static void excecuteDeadline(HashMap<String, Object> map) throws IOException {
        excecuteAdd(new Deadline((String) map.get("description"), (String) map.get("time")));
    }

    static void excecuteEvent(HashMap<String, Object> map) throws IOException {
        //String result = "Got it. I have added this task:\n";
        excecuteAdd(new Event((String) map.get("description"), (String) map.get("time")));
    }

    static void excecuteHandle(Exception exception) {
        //String result = "Got it. I have added this task:\n";
        System.out.println(Duke.makeBlock(exception.getMessage()));
    }

    public static void excecuteDone(HashMap<String, Object> map) throws TaskNotFoundException, IOException {
        int count = Integer.parseInt((String) map.get("number"));
        if(count > tasks.size() || count <= 0) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task");
        } else {
            tasks.get(count - 1).markAsCompleted();
            System.out.println(Duke.makeBlock("Nice! I have marked this task as done:\n" + String.valueOf(count) + "." + tasks.get(count - 1).toString()));
            writeRecord();
        }

    }

    public static void excecuteDelete(HashMap<String, Object> map) throws TaskNotFoundException, IOException {
        int count = Integer.parseInt((String) map.get("number"));
        if(count > tasks.size() || count <= 0) {
            //System.out.println(Duke.makeBlock("There is no such task"));
            throw new TaskNotFoundException("There is no such task");
        } else {
            Task task = tasks.remove(count - 1);
            System.out.println(Duke.makeBlock("Noted. I have removed this task:\n" +
                    String.valueOf(count) +
                    "." + task.toString() +
                    "\nNow you have " + tasks.size() + " tasks in the list."));
            writeRecord();
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
            throw new CommandNotFoundException("The description should not be empty");
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

    public static void readRecord(File record) throws FileNotFoundException, IllegalArgumentException {
        Scanner scanner = new Scanner(record);
        String next;
        while(scanner.hasNextLine()) {
            next = scanner.nextLine();
            String[] strings = next.split(" \\| ");
            /*
            for(int i = 0; i < strings.length; i = i + 1) {
                System.out.println(strings[i]);
            }
            */
            if(strings[1].equals("0") || strings[1].equals("1")) {
                if(strings[0].equals("T") && strings.length == 3) {
                    ToDo todo = new ToDo(strings[2]);
                    if(strings[1].equals("1")) {
                        todo.markAsCompleted();
                    }
                    tasks.add(todo);
                } else if (strings[0].equals("D") && strings.length == 4) {
                    Deadline deadline = new Deadline(strings[2], strings[3]);
                    if(strings[1].equals("1")) {
                        deadline.markAsCompleted();
                    }
                    tasks.add(deadline);
                } else if (strings[0].equals("E") && strings.length == 4) {
                    Event event = new Event(strings[2], strings[3]);
                    if(strings[1].equals("1")) {
                        event.markAsCompleted();
                    }
                    tasks.add(event);
                } else {
                    throw new IllegalArgumentException("The record cannot be read becuase the length is incorrect");
                }
            } else {
                throw new IllegalArgumentException("The record cannot be read because the number is incorrect");
            }

        }
        scanner.close();
    }

    public static void writeRecord() throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        String string = "";
        for(int i = 0; i < tasks.size(); i = i + 1) {
            string = string.concat(tasks.get(i).record() + System.lineSeparator());
        }
        fileWriter.write(string);
        fileWriter.close();
    }
}