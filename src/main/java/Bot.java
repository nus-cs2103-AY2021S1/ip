package main.java;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Bot {
    public Bot() {
    }

    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static ArrayList<Task> taskList = new ArrayList<>();
    enum Action {
        ADD,
        DONE,
        DELETE
    }

    public static void printTask(Task task, Action action) {
        switch (action) {
            case ADD:
                System.out.println("Got it. I've added this task:");
                break;
            case DONE:
                System.out.println("Nice! I've marked this task as done:");
                break;
            case DELETE:
                System.out.println("Noted. I've removed this task:");
                break;
            default:
                break;
        }
        int num = taskList.size();
        System.out.println(task);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    public void doneTask(String taskNum) {
        int index = Integer.parseInt(taskNum) - 1;
        Task curr = taskList.get(index);
        curr.markAsDone();
        printTask(curr, Action.DONE);
    }
    public void deleteTask(String taskNum) {
        int index = Integer.parseInt(taskNum) - 1;
        Task curr = taskList.get(index);
        taskList.remove(curr);
        printTask(curr, Action.DELETE);
    }
    public void addTask(String command) throws DukeException {
        String[] cmdLine = command.split(" ");
        String taskType = cmdLine[0];
        String[] split;

        switch(taskType) {
            case "todo":
                if (cmdLine.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                String tasking = command.substring(5);
                Task todo = new Todo(tasking);
                taskList.add(todo);
                printTask(todo, Action.ADD);
                break;

            case "deadline":
                if (cmdLine.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                split = command.substring(9).split("/by", 2);
                Task deadline = new Deadline(split[0], split[1]);
                taskList.add(deadline);
                printTask(deadline, Action.ADD);
                break;

            case "event":
                if (cmdLine.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                split = command.substring(6).split("/at", 2);
                Task event = new Event(split[0], split[1]);
                taskList.add(event);
                printTask(event, Action.ADD);
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public void start() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        loadData();
        while (scan.hasNext()) {
            String input = scan.nextLine();
            try {
                if (input.startsWith("done")) {
                    if (input.length() == 4) {
                        throw new DukeException("☹ OOPS!!! Please select a task to be done.");
                    }
                    doneTask(input.substring(5));
                } else if (input.startsWith("delete")) {
                    if (input.length() == 6) {
                        throw new DukeException("☹ OOPS!!! Please select a task to be deleted.");
                    }
                    deleteTask(input.substring(7));
                } else if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    saveData();
                    return;
                } else if (input.equals("list")) {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + "." + taskList.get(i));
                    }
                } else {
                    try {
                        addTask(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                saveData();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void saveData() {
        File saved = new File("ip_data.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saved));
            for (Task t: taskList) {
                bw.write(t.getState());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            File saved = new File("ip_data.txt");
            if (!saved.exists()) {
                try {
                    saved.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            BufferedReader br = new BufferedReader(new FileReader(saved));
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] readLine = currLine.split("\\|");
                boolean taskDone = readLine[1].equals("1");
                Task newTask = new Task("");
                String type = readLine[0];
                switch(type) {
                    case "T":
                        newTask = new Todo(readLine[2]);
                        break;
                    case "D":
                        newTask = new Deadline(readLine[2], readLine[3]);
                        break;
                    case "E":
                        newTask = new Event(readLine[2], readLine[3]);
                        break;
                    default:
                        break;
                }
                taskList.add(newTask);
                if (taskDone) {
                    newTask.markAsDone();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
