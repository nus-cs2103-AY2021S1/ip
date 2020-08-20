import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> list = new ArrayList<>();

    public static void printList() {
        int i = 1;
        for(Task t : list) {
            System.out.println(i + "." + t);
            i++;
        }
    }

    public static void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
    }

    public static void printDeleteTask(Task task) {
        System.out.println("Noted. I've deleted this task:\n" + task);
        System.out.println("Now you have " + (list.size() - 1) + " tasks in the list." );
    }

    public static void main(String[] args) {
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            try {
                File dir = new File("data");
                dir.mkdir();
                file.createNewFile();
            } catch (IOException k) {
                k.printStackTrace();
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String fileLine;
            while((fileLine = reader.readLine()) != null) {
                String[] str = fileLine.split(" \\| ");
                switch(str[0]) {
                    case "T":
                        list.add(new Todo(str[2]));
                        break;
                    case "D":
                        list.add(new Deadline(str[2], str[3]));
                        break;
                    case "E":
                        list.add(new Event(str[2], str[3]));
                        break;
                }
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Hello! I'm Duke");
            System.out.println("What can I do for you?");
            while (sc.hasNext()) {
                try {
                    String command = sc.nextLine();
                    int index;
                    if (command.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (command.equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        printList();
                    } else {
                        String line = command.split(" ", 2)[0];
                        switch (line) {
                            case "done":
                                if (command.split(" ", 2).length == 1) {
                                    throw new EmptyDescriptionException("The description of " + line + " cannot be empty, Please re-enter");
                                }
                                index = Integer.parseInt(command.split(" ", 2)[1]) - 1;
                                list.get(index).markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(list.get(index));
                                break;
                            case "delete":
                                if (command.split(" ", 2).length == 1) {
                                    throw new EmptyDescriptionException("The description of " + line + " cannot be empty, Please re-enter");
                                }
                                index = Integer.parseInt(command.split(" ", 2)[1]) - 1;
                                printDeleteTask(list.get(index));
                                list.remove(index);
                                break;
                            case "todo": {
                                if (command.split(" ", 2).length == 1) {
                                    throw new EmptyDescriptionException("The description of " + line + " cannot be empty. Please re-enter");
                                }
                                Task task = new Todo(command.split(" ", 2)[1]);
                                list.add(task);
                                printAddTask(task);
                                writer.write(task.saveText() + "\n");
                                break;
                            }
                            case "deadline": {
                                if (command.split(" ", 2).length == 1) {
                                    throw new EmptyDescriptionException("The description of " + line + " cannot be empty. Please re-enter");
                                }
                                String str = command.split(" ", 2)[1].split(" /", 2)[0];
                                String date = command.split(" ", 2)[1].split("/", 2)[1].split(" ", 2)[1];
                                Task task = new Deadline(str, Parser.DateParser(date));
                                list.add(task);
                                printAddTask(task);
                                writer.write(task.saveText() + "\n");
                                break;
                            }
                            case "event": {
                                if (command.split(" ", 2).length == 1) {
                                    throw new EmptyDescriptionException("The description of " + line + " cannot be empty. Please re-enter");
                                }
                                String str = command.split(" ", 2)[1].split(" /", 2)[0];
                                String date = command.split(" ", 2)[1].split("/", 2)[1].split(" ", 2)[1];
                                Task task = new Event(str, Parser.DateParser(date));
                                list.add(task);
                                printAddTask(task);
                                writer.write(task.saveText() + "\n");
                                break;
                            }
                            default:
                                throw new CommandException("Im sorry, I do not understand what you mean. Please re-enter:");
                        }
                    }
                } catch (CommandException | EmptyDescriptionException e) {
                    System.err.println(e.getMessage());
                }
            }
            reader.close();
            writer.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
