import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static int total;
    static String s;
    static FileEditor f = new FileEditor();

    static String HOME = System.getProperty("user.home");
    static java.nio.file.Path path = java.nio.file.Paths.get(HOME, "ip", "data.txt");

    static void greet() throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("______________________");
        System.out.println(logo);
        System.out.println("welcome to my crib");
        System.out.println("______________________");

        Scanner myReader = new Scanner(path);
        total = myReader.nextInt();

        s = sc.nextLine();
    }

    static void adios() {
        System.out.println("ok u can leave lmao");
        System.out.println("______________________");
    }

    static void ls() throws IOException {
        f.readFile();
        s = sc.nextLine();
    }

    static void del() throws IOException {
        Scanner myReader = new Scanner(path);
        System.out.println("removed!! ^^");
        int taskNumber = Integer.parseInt(s.substring(7));
        String taskData = "";
        for (int i = 0; i <= taskNumber; i++) {
            taskData = myReader.nextLine();
        }
        Task t = f.convertText(taskData);
        System.out.println(t);

        f.deleteText(f.parseTask(t));
        total--;
        f.updateTotal(total);

        System.out.println("total task: " + total + "\n:o");
        System.out.println("______________________");
        s = sc.nextLine();
    }

    static void finish() throws IOException {
        Scanner myReader = new Scanner(path);

        System.out.println("gfy youve managed to finish the following...");
        int taskNumber = Integer.parseInt(s.substring(5));
        String taskData = "";
        for (int i = 0; i <= taskNumber; i++) {
            taskData = myReader.nextLine();
        }
        Task t = f.convertText(taskData);
        t = t.completeTask();
        replaceText(taskData, f.parseTask(t));
        System.out.println(t);
        System.out.println("______________________");
        s = sc.nextLine();
    }

    static void replaceText(String prevTask, String newTask) throws IOException {
        File fileToBeModified = path.toFile();
        String oldText = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        reader = new BufferedReader(new FileReader(fileToBeModified));
        String line = reader.readLine();

        while (line != null) {
            oldText = oldText + line + System.lineSeparator();
            line = reader.readLine();
        }

        String newText = oldText.replaceAll(prevTask, newTask);

        writer = new FileWriter(fileToBeModified);
        writer.write(newText);

        reader.close();
        writer.close();
    }

    static void handleTodo() throws IncompleteInputException, IOException {
         try {
             String name = s.substring(5);
             if (name.isBlank()) {
                 throw new IncompleteInputException();
             } else {
                 Task t = new Todo(name);

                 f.writeData(t);
                 total++;
                 f.updateTotal(total);

                 System.out.println("added!");
                 System.out.println(t);
                 System.out.println("total task: " + total + "\n:o");
                 System.out.println("______________________");

                 s = sc.nextLine();
             }
         } catch (StringIndexOutOfBoundsException e) {
             // command is incomplete
             throw new IncompleteInputException();
         }
    }

    static void handleDeadline() throws IncompleteInputException, IOException {
         try {
             String name = s.split("/")[0].substring(9).stripTrailing();
             String deadline = s.split("/")[1].substring(3);
             if (name.isBlank() || deadline.isBlank()) {
                 throw new IncompleteInputException();
             } else {
                 Task t = new Deadline(name, deadline);

                 f.writeData(t);
                 total++;
                 f.updateTotal(total);

                 System.out.println("added!");
                 System.out.println(t);
                 System.out.println("total task: " + total + "\n:o");
                 System.out.println("______________________");

                 s = sc.nextLine();
             }
         } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
             // command is incomplete
             throw new IncompleteInputException();
         }
    }

    static void handleEvent() throws IncompleteInputException, IOException {
        try {
            String name = s.split("/")[0].substring(6).stripTrailing();
            String time = s.split("/")[1].substring(3);
            if (name.isBlank() || time.isBlank()) {
                throw new IncompleteInputException();
            } else {
                Task t = new Event(name, time);

                f.writeData(t);
                total++;
                f.updateTotal(total);

                System.out.println("added!");
                System.out.println(t);
                System.out.println("total task: " + total + "\n:o");
                System.out.println("______________________");

                s = sc.nextLine();
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            // command is incomplete
            throw new IncompleteInputException();
        }
    }

    static void talk() {
        while (1 == 1) {
            try {
                if (s.equals("bye")) {
                    adios();
                    break;
                } else if (s.equals("list")) {
                    ls();
                } else if (s.startsWith("done")) {
                    finish();
                } else if (s.startsWith("delete")) {
                    del();
                } else if (s.startsWith("todo")) {
                    handleTodo();
                } else if (s.startsWith("deadline")) {
                    handleDeadline();
                } else if (s.startsWith("event")) {
                    handleEvent();
                } else { // unknown input
                    throw new UnknownInputException();
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("______________________");
                s = sc.nextLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        greet();

        talk();
    }
}
