package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> taskList;
        File historyFile = new File("./data/duke.txt");
        if (!historyFile.exists()) {
            File dir = new File(historyFile.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                historyFile.createNewFile();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
            taskList = new ArrayList<>();
        } else {
            taskList = readHistory(historyFile);
        }

        System.out.println("//////////\n" + "->Hello! I'm Duke\n" +
                "->What can I do for you?" + "\n");

        if (taskList.size() != 0) {
            System.out.println("\n-> Saved List:\n");
            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(i + ". " + taskList.get(i - 1));
            }
            System.out.println("\n");
        }

        run(taskList, historyFile);
    }

    private static void run(List<Task> taskList, File historyFile) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = inputScanner.nextLine();

            if (userInput.equals("bye")) {

                System.out.println("\nBye. Hope to see you again soon!");

            } else if (userInput.equals("list")) {

                if (taskList.size() > 0) {
                    System.out.println("\n-> Current List:\n");
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i - 1));
                    }
                    System.out.println("\n");
                } else {
                    System.out.println("\n//////There is NO task in your list now//////\n");
                }

            } else if (userInput.startsWith("done")) {

                try {
                    int doneNo = Integer.parseInt(userInput.substring(5));
                    if (doneNo <= 0 || doneNo - 1 >= taskList.size()) {
                        System.out.println("\n-> Sorry, this task does not exist...\n");
                    } else {
                        taskList.get(doneNo - 1).done();
                        updateFile(historyFile, taskList);
                        System.out.println("\n-> Good job! I have marked this task as done:\n" + taskList.get(doneNo - 1).toString() + "\n");
                    }
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"done\" order\n" +
                            "-> done {order of task in task list}\n");
                }

            } else if (userInput.startsWith("todo")) {

                try {
                    if (userInput.substring(5).length() == 0) {
                        throw new Exception();
                    }
                    Todo newTodo = new Todo(userInput.substring(5));
                    taskList.add(newTodo);
                    updateFile(historyFile, taskList);
                    System.out.println(
                            "\n-> I have added a Todo:\n" +
                                    newTodo.toString() +
                                    "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                    );
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"todo\" order\n" +
                            "-> todo {task content}\n");
                }

            } else if (userInput.startsWith("deadline")) {

                try {
                    String[] splited = userInput.substring(9).split("/");
                    Deadline newDdl = new Deadline(splited[0], splited[1].replace(" ", ""));
                    taskList.add(newDdl);
                    updateFile(historyFile, taskList);
                    System.out.println(
                            "\n-> I have added a Deadline:\n" +
                                    newDdl.toString() +
                                    "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                    );
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"deadline\" order\n" +
                            "-> deadline {task content} /{yyyy-mm-dd}\n");
                }

            } else if (userInput.startsWith("event")) {

                try {
                    String[] splited = userInput.substring(6).split("/");
                    Event newEvent = new Event(splited[0], splited[1].replace(" ", ""));
                    taskList.add(newEvent);
                    updateFile(historyFile, taskList);
                    System.out.println(
                            "\n-> I have added an Event:\n" +
                                    newEvent.toString() +
                                    "\nYou have " + taskList.size() + " tasks in your list currently.\n"
                    );
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"event\" order\n" +
                            "-> event {task content} /{yyyy-mm-dd}\n");
                }

            } else if (userInput.startsWith("delete")) {

                try {
                    int rmNo = Integer.parseInt(userInput.substring(7));
                    if (rmNo <= 0 || rmNo - 1 >= taskList.size()) {
                        System.out.println("\n-> Sorry, this task does not exist...\n");
                    } else {
                        System.out.println("\n-> I have removed this task:\n" + taskList.get(rmNo - 1).toString() + "\n");
                        taskList.remove(rmNo - 1);
                        updateFile(historyFile, taskList);
                        System.out.println("Now you have " + taskList.size() + " tasks in your list." + "\n");
                    }
                } catch (Exception e) {
                    System.out.println("\n-> Oops, there is an error...\n" +
                            "-> please add correct description to \"done\" order\n" +
                            "-> done {order of task in task list}\n");
                }

            } else {

                System.out.println(
                        "\n-> Sorry I cannot understand, please tap in your order correctly." +
                                "\n-> todo {task content}   || add a todo task" +
                                "\n-> deadline {task content} /{time}   || add a deadline task" +
                                "\n-> event {task content} /{time} || add an event task" +
                                "\n-> list   || list all tasks" +
                                "\n-> done {order of task in task list}   || mark a task as done\n");

            }
        }
        inputScanner.close();
    }

    private static List<Task> readHistory(File historyFile) {
        List<Task> result = new ArrayList<>();
        Scanner history;
        try{
            history = new Scanner(historyFile);

            while (history.hasNext()) {
                String type = history.nextLine();
                boolean status = history.nextLine().equals("1");
                String content = history.nextLine();
                if (type.equals("T")) {
                    result.add(new Todo(status, content));
                } else if (type.equals("E")) {
                    String time = history.nextLine().replace(" ", "");
                    result.add(new Event(status, content, time));
                } else if (type.equals("D")) {
                    String time = history.nextLine().replace(" ", "");
                    result.add(new Deadline(status, content, time));
                }
            }
            history.close();
        } catch (FileNotFoundException e) {

        }
        return result;
    }

    private static void updateFile(File historyFile, List<Task> content) {
        try{
            FileWriter fileWriter = new FileWriter(historyFile);

            String data = "";
            for (int i = 0; i < content.size(); i++) {
                String temp = "";
                if (content.get(i) instanceof Todo) {
                    Todo holding = (Todo) content.get(i);
                    temp += "T\n";
                    temp += (holding.isDone() ? "1" : "0") + "\n";
                    temp += holding.getContent() + "\n";
                    data += temp;
                    temp = "";
                } else if (content.get(i) instanceof Event) {
                    Event holding = (Event) content.get(i);
                    temp += "E\n";
                    temp += (holding.isDone() ? "1" : "0") + "\n";
                    temp += holding.getContent() + "\n";
                    temp += holding.getTime() + "\n";
                    data += temp;
                    temp = "";
                } else if (content.get(i) instanceof Deadline) {
                    Deadline holding = (Deadline) content.get(i);
                    temp += "D\n";
                    temp += (holding.isDone() ? "1" : "0") + "\n";
                    temp += holding.getContent() + "\n";
                    temp += holding.getTime() + "\n";
                    data += temp;
                    temp = "";
                }
            }
            fileWriter.write(data);

            fileWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
