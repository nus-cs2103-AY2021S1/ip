package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final static String FILE_PATH = "data/duke.txt";

    public static void main(String[] args) {

        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "________________________________";

        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        List<Task> list = readFile();

        String output = sc.nextLine();
        boolean added = false;

        while (!output.equals("bye")) {

            System.out.println(line);

            if (output.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            }

            else if (output.indexOf("done ") == 0) {

                String[] arr = output.split(" ");

                try {

                    int index = Integer.parseInt(arr[1]) - 1;

                    if (index > -1 && index < list.size()) {
                        list.get(index).completeTask();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index));
                    }
                    else {
                        System.out.println("☹ OOPS!!! Invalid parameter given :-(");
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("☹ OOPS!!! Invalid parameter given :-(");
                }
            }

            else if (output.indexOf("delete ") == 0) {

                String[] arr = output.split(" ");

                try {

                    int index = Integer.parseInt(arr[1]) - 1;

                    if (index > -1 && index < list.size()) {
                        list.remove(index);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(index));
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                    else {
                        System.out.println("☹ OOPS!!! Invalid parameter given :-(");
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("☹ OOPS!!! Invalid parameter given :-(");
                }
            }

            else {

                if (output.indexOf("todo ") == 0 && output.length() > 5) {
                    list.add(new ToDo(output.substring(5)));
                    added = true;
                }

                else if (output.indexOf("deadline ") == 0) {

                    int deadlineIndex = output.indexOf("/by ");

                    if (deadlineIndex != -1 && output.length() > deadlineIndex + 4) {
                        list.add(new Deadline(output.substring(9, deadlineIndex - 1), output.substring(deadlineIndex + 4)));
                        added = true;
                    }
                }

                else if (output.indexOf("event ") == 0) {

                    int timeIndex = output.indexOf("/at ");

                    if (timeIndex != -1 && output.length() > timeIndex + 4) {
                        list.add(new Event(output.substring(6, timeIndex - 1), output.substring(timeIndex + 4)));
                        added = true;
                    }
                }

                if (added){
                    added = false;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(list.size() - 1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
                else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            System.out.println(line);
            output = sc.nextLine();
        }

        saveFile(list);

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    private static List<Task> readFile() {

        File file = new File(FILE_PATH);
        List<Task> output = new ArrayList<Task>();

        try {

            if (!file.exists()) {
                return output;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null){

                String[] lineArr = line.split("\\s(\\|)\\s");

                switch (lineArr[0]) {
                    case "T":
                        output.add(new ToDo(lineArr[2]));
                        break;
                    case "D":
                        output.add(new Deadline(lineArr[2], lineArr[3]));
                        break;
                    case "E":
                        output.add(new Event(lineArr[2], lineArr[3]));
                        break;
                }

                if (lineArr[1].equals("1")) {
                    output.get(output.size() - 1).completeTask();
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error occurred while reading data");
        }

        return output;
    }

    private static void saveFile(List<Task> tasks) {

        File file = new File(FILE_PATH);

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            int completed;

            for (Task task : tasks) {
                completed = task.isCompleted() ? 1 : 0;

                if (task instanceof ToDo) {
                    bw.write(String.format("T | %d | %s\n", completed, task.getMsg()));
                }
                else if (task instanceof Deadline) {
                    bw.write(String.format("D | %d | %s | %s\n", completed, task.getMsg(),
                            ((Deadline) task).getDeadline()));
                }
                else if (task instanceof Event) {
                    bw.write(String.format("E | %d | %s | %s\n", completed, task.getMsg(),
                            ((Event) task).getTime()));
                }
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Error occurred while saving data");
        }
    }
}
