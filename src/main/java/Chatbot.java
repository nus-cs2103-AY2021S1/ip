import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {
    public String line = "____________________________________________________________";
    public List<Task> list = new ArrayList<>();

    public void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println(line);
    }

    public void addTask(Task t) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.printTask());
        System.out.println("Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " on the list");
        System.out.println(line);
    }
    public void generateList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println((i + 1) + ". " + t.printTask());
        }
        System.out.println(line);
    }

    public void delete(int i) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(i - 1).printTask());
        list.remove(i - 1);
        System.out.println("Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " on the list");
        System.out.println(line);
    }

    public boolean chat(String s) throws IncorrectInputException, IOException {
        int j = s.indexOf(' ');
        String firstWord = "";
        if (j > -1) {
            firstWord = s.substring(0, j);
        } else {
            firstWord = s;
        }

        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(
                    "src/main/java/tasklist.txt", true));
            switch (firstWord) {
                case "bye":
                    exit();
                    return false;
//            break;
                case "list":
                    generateList();
                    return true;
                case "done":
                    char x = s.charAt(s.length() - 1);
                    int i = Character.getNumericValue(x);
                    Task t = list.get(i - 1);
                    t.markAsDone();
                    list.set(i - 1, t);
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.printTask());
                    System.out.println(line);

                    return true;
                case "delete":
                    char y = s.charAt(s.length() - 1);
                    int k = Character.getNumericValue(y);
                    delete(k);
                    return true;
                case "todo":
                    if (s.length() != "todo".length()) {
                        ToDo toDo = new ToDo(s.replace("todo ", ""));
                        list.add(toDo);
                        addTask(toDo);
                        file.newLine();
                        file.write(toDo.toSave());
                        file.close();
                        return true;
                    } else {
                        throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                case "event":
                    if (s.length() != "event".length()) {
                        String[] value = s.split(" /at ");
                        Event event = new Event(value[0].replace("event ", ""), value[1]);
                        list.add(event);
                        addTask(event);
                        file.newLine();
                        file.write(event.toSave());
                        file.close();
                        return true;
                    } else {
                        throw new IncorrectInputException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                case "deadline":
                    if (s.length() != "deadline".length()) {
                        String[] value = s.split(" /by ");
                        Deadline deadline = new Deadline(value[0].replace("deadline ", ""), value[1]);
                        list.add(deadline);
                        addTask(deadline);
                        file.newLine();
                        file.write(deadline.toSave());
                        file.close();
                        return true;
                    } else {
                        throw new IncorrectInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                default:
                    throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
