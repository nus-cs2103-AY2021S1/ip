
import java.time.LocalDate;
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
                } catch (NumberFormatException e) {
                    System.err.println(new DeleteUnknownException());
                } catch (IndexOutOfBoundsException e) {
                    System.err.println(new DeleteOutOfBoundException());
                }
            } else if (next[0].equals("event") || next[0].equals("todo") || next[0].equals("deadline")) {
                try {
                    duke.addTask(next);
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

}
