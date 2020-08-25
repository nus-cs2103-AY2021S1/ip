import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.ToDo;

import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String border = "\n^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n";
        Scanner sc = new Scanner(System.in);
        String command;
        String availableCommands = "Available commands: bye, list, done, delete, todo, deadline, event";
        ArrayList<Task> tList = readListFromFile();

        String logo = " _       _ \n"
                + "| |  _  | |_   _ ____ ___\n"
                + "| | | | | | |_/ |  _ \\  _ \\ \n"
                + "| |_| |_| |\\___ |    <  __/\n"
                + "\\___/\\___/ \\____|_| \\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(border + "Hi! I'm Wyre, your Personal Assistant Chatbot! :>\nWhat can I do for you today?"
                + border);
        while(sc.hasNextLine()) {
            try {
                command = sc.nextLine();
                //command = original.replaceAll("\\s", ""); // remove all spaces from command

                if (command.equals("bye")) {
                    System.out.println(border + "Bye. Hope to see you again!" + border);
                    break;
                } else if (command.equals("list")) {
                    System.out.println(border + "Here are the task(s) in your list:\n");
                    for (int i = 0; i < tList.size(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + tList.get(i));
                    }
                    System.out.println(border);
                } else if (command.substring(0, 4).equals("done")) {
                    int index = Integer.parseInt(command.substring(5)) - 1;
                    tList.get(index).setStatus(true);
                    System.out.println(border + "Well done! I've marked this task as done:");
                    System.out.println("\t" + tList.get(index) + border);
                    writeListToFile(tList);
                } else if (command.substring(0, 6).equals("delete")) {
                    int index = Integer.parseInt(command.substring(7)) - 1;
                    if(index < tList.size()) {
                        System.out.println(border + "Ooookay! I've removed this task:");
                        System.out.println("\t" + tList.get(index));
                        tList.remove(index);
                        System.out.println("Now you have " + tList.size() + " task(s) in the list." + border);
                        writeListToFile(tList);
                    } else {
                        System.out.println(border + "Sorry fam, you can't delete a nonexistent index!" + border);
                    }
                } else if (command.substring(0, 4).equals("todo")) {
                    String name = command.substring(5);
                    if (name.isEmpty()) {
                        System.out.println("Naw, you can't have a todo with an empty name!");
                    } else {
                        ToDo t = new ToDo(name, false);
                        tList.add(t);
                        System.out.println(border + "Wyre at your service. I've added the task:\n\t" + t);
                        System.out.println("Now you have " + tList.size() + " task(s) in the list." + border);
                        writeListToFile(tList);
                    }
                } else if (command.substring(0, 5).equals("event")) {
                    int escapeIndex = command.lastIndexOf("/");
                    String name = command.substring(6, escapeIndex - 1);

                    if (name.isEmpty()) {
                        System.out.println("Naw, you can't have a event with an empty name!");
                    } else {
                        Event e = new Event(name, false, command.substring(escapeIndex + 4));
                        tList.add(e);
                        System.out.println(border + "Wyre at your service. I've added the task:\n\t" + e);
                        System.out.println("Now you have " + tList.size() + " task(s) in the list." + border);
                        writeListToFile(tList);
                    }
                } else if (command.substring(0, 8).equals("deadline")) {
                    int escapeIndex = command.lastIndexOf("/");
                    String name = command.substring(9, escapeIndex - 1);

                    if (name.isEmpty()) {
                        System.out.println("Naw, you can't have a deadline with an empty name!");
                    } else {
                        Deadline d = new Deadline(name, false, command.substring(escapeIndex + 4));
                        tList.add(d);
                        System.out.println(border + "Wyre at your service. I've added the task:\n\t" + d);
                        System.out.println("Now you have " + tList.size() + " task(s) in the list." + border);
                        writeListToFile(tList);
                    }
                } else {
                    System.out.println(border + "Naw, this isn't an accepted command!\n" + availableCommands + border);
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(border + "Naw fam, not a legal command. :'(\n" + availableCommands + border);
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    public static ArrayList<Task> readListFromFile() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("task_list.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Task> lst = (ArrayList<Task>) ois.readObject();
            ois.close();
            return lst;
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        } catch (IOException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        }

    }

    public static void writeListToFile(ArrayList<Task> lst) throws IOException {
        FileOutputStream fos = new FileOutputStream("task_list.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(lst);
        oos.close();
    }

}
