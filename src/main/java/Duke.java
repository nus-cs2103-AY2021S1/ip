package main.java;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Task {
    String name;
    boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public static Task fromData(String data) {
        String[] params = data.split(" \\| ");
        System.out.println(Arrays.toString(params));
        Task task;
        switch (params[0]) {
        case "T":
            task = new Todo(params[2]);
            break;
        case "D":
            task = new Deadline(params[2], params[3]);
            break;
        case "E":
            task = new Event(params[2], params[3]);
            break;
        default:
            System.out.println("☹ OOPS!!! Invalid data structure.");
            return null;
        }

        task.done = Boolean.parseBoolean(params[1]);
        return task;
    }

    public String toData() {
        return done + " | " + name;
    }

    @Override
    public String toString() {
        return "[" + (done ? "✓" : "✗") + "] " + name;
    }
}

class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    LocalDate by;

    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
    }

    public String toData() {
        return "D | " + super.toData() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

class Event extends Task {
    LocalDate at;

    public Event(String name, String at) {
        super(name);
        this.at = LocalDate.parse(at);
    }

    public String toData() {
        return "E | " + super.toData() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

public class Duke {
    public static void main(String[] args) throws IOException {
        // startup
        System.out.println("hi");
        Scanner s = new Scanner(System.in);
        Task task;

        File file= new File("data.txt");
        file.createNewFile();
        Scanner data = new Scanner(file);

        List<Task> list = new ArrayList<>();
        while (data.hasNextLine()) {
            list.add(Task.fromData(data.nextLine()));
        }

        // main loop
        mainLoop:
        while (true) {
            String input = s.next();

            switchLabel:
            switch (input) {
            case "bye":
                break mainLoop;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + "." + list.get(i));
                }
                break;
            case "done":
                task = list.get(s.nextInt() - 1);
                task.done = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
                break;
            case "delete":
                task = list.remove(s.nextInt() - 1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            default:
                String[] params;

                switch (input) {
                case "todo":
                    String param = s.nextLine();
                    if (param.isEmpty()){
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        break switchLabel;
                    }
                    task = new Todo(param);
                    break;
                case "deadline":
                    params = s.nextLine().split(" /by ");
                    task = new Deadline(params[0], params[1]);
                    break;
                case "event":
                    params = s.nextLine().split(" /at ");
                    task = new Event(params[0], params[1]);
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break switchLabel;
                }

                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            }
        }

        // end
        FileWriter fw = new FileWriter("data.txt");
        for (Task t : list) {
            fw.write(t.toData() + "\n");
        }
        fw.close();
        System.out.println("bye");
    }

}