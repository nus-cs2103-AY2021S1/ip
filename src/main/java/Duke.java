
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.LinkedList;
import java.util.List;

import main.java.*;

import java.util.Scanner;

public class Duke {
    private List<Task> taskList;

    public Duke() {
        taskList = new LinkedList<>();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        try {
            duke.loadFile();
        } catch (IOException e) {

        }
        duke.hello();
        Scanner sc = new Scanner(System.in);
        String[] next = sc.nextLine().split(" ",2);
        while (!next[0].equals("bye")) {
            if (next[0].equals("list")){
                duke.printList();
            } else if (next[0].equals("done")) {
                try {
                    Integer toBeChange = Integer.valueOf(next[1]);
                    Task cur = duke.taskList.get(toBeChange - 1);
                    cur.changeIsDone();
                    System.out.println("This task has been mark as done.");
                    System.out.println(cur);
                    System.out.println();
                    try {
                        duke.saveFile();
                    } catch (IOException e) {

                    }
                } catch (NumberFormatException e) {
                    System.err.println(new DoneUnknownException());
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(new DoneOutOfBoundException());
                }
            } else if (next[0].equals("delete")) {
                try {
                    Integer toBeChange = Integer.valueOf(next[1]);
                    Task cur = duke.taskList.get(toBeChange - 1);
                    duke.taskList.remove(toBeChange - 1);
//                    cur.changeIsDone();
                    System.out.println("This task has been deleted.");
                    System.out.println(cur);
                    System.out.println();
                    try {
                        duke.saveFile();
                    } catch (IOException e) {

                    }
                } catch (NumberFormatException e) {
                    System.err.println(new DeleteUnknownException());
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(new DeleteOutOfBoundException());
                }
            } else if (next[0].equals("event") || next[0].equals("todo") || next[0].equals("deadline")) {
                try {
                    duke.addTask(next);
                    try {
                        duke.saveFile();
                    } catch (IOException e) {

                    }
                } catch(DukeException e) {
                    System.err.println(e);
                } catch (DateTimeParseException e) {
                    System.err.println("Please input with the format dd/mm/yyyy HHmm");
                }
            } else {
                System.err.println(new CommandException());
            }
            next = sc.nextLine().split(" ",2);
        }

        duke.goodbyeMessage();
    }

    public void goodbyeMessage() {
        System.out.println("********************************************");
        System.out.println("GoodBye, Hope to see you back soon.");
        System.out.println("********************************************");
    }

    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }



    public void addTask(String[] next) throws TaskException, DeadlineException {
        if (next[0].equals("todo")) {
            try {
                Task temp = new Task(next[1]);
                this.taskList.add(temp);
                System.out.println("********************************************");
                System.out.println("Added new task " + temp);
                System.out.println("********************************************");
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                throw new TaskException();
            }
        } else if(next[0].equals("deadline")) {
            try {
                String[] str = next[1].split("/by ", 2);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime date = LocalDateTime.parse(str[1],dateFormatter);
                Task temp = new Deadline(str[0], date);
                this.taskList.add(temp);
                System.out.println("********************************************");
                System.out.println("Added new task " + temp);
                System.out.println("********************************************");
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                throw new DeadlineException();
            } catch (DateTimeParseException e) {
                throw e;
            }
        } else {
            try {
                String[] str = next[1].split("/at ", 2);
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                LocalDateTime date = LocalDateTime.parse(str[1],dateFormatter);
                Task temp = new Event(str[0], date);
                this.taskList.add(temp);
                System.out.println("********************************************");
                System.out.println("Added new task " + temp);
                System.out.println("********************************************");
                System.out.println();
            } catch (IndexOutOfBoundsException e) {
                throw new DeadlineException();
            } catch (DateTimeParseException e) {
                throw e;
            }
        }

    }



    private void printList() {
        for(int i = 0; i < this.taskList.size(); i++) {
            System.out.println( (i+1) + ". " + this.taskList.get(i));
        }
        System.out.println();
    }



    private void loadFile() throws IOException {
        File f = new File("data/duke.txt");
        if(f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String temp = sc.nextLine();
                String[] res = temp.split(" # ");
                if (res[0].equals("T")) {
                    boolean isDone = false;
                    if(res[1].equals("1")) {
                        isDone = true;
                    }
                    Task cur = new Task(res[2], isDone);
                    this.taskList.add(cur);
                } else if (res[0].equals("D")) {
                    boolean isDone = false;
                    if (res[1].equals("1")) {
                        isDone = true;
                    }
                    String datePattern = "dd/MM/yyyy HH:mm";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
                    LocalDateTime date =LocalDateTime.parse(res[3],dateFormatter);
                    Task cur = new Deadline(res[2], isDone, date);
                    this.taskList.add(cur);
                } else {
                    boolean isDone = false;
                    if (res[1].equals("1")) {
                        isDone = true;
                    }
                    String datePattern = "dd/MM/yyyy HH:mm";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
                    LocalDateTime date =LocalDateTime.parse(res[3],dateFormatter);
                    Task cur = new Event(res[2], isDone, date);
                    this.taskList.add(cur);
                }
            }
            sc.close();
        } else {
            f.createNewFile();
        }
    }

    public void saveFile() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        int len = this.taskList.size();
        for (int i = 0; i < len;i++) {
            Task current = this.taskList.get(i);
            fw.write(current.writeToFile());
            fw.write(System.lineSeparator());
        }
        fw.close();

    }


}
