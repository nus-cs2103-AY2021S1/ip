import java.io.IOException;

import java.util.Scanner;

public class Ui {

    private static String LINE = "____________________________________________________________";
    private TaskList list;
    private Storage storage;

    public Ui(TaskList taskList, Storage storage) {
        this.list = taskList;
        this.storage = storage;
    }

    public void startProgram() throws IOException {
        greet();

        Scanner sc = new Scanner(System.in);
        String commandType = "";
        String taskDetails = "";
        String date = "";

        while (sc.hasNext()) {
            System.out.println(LINE);

            try {
                Parser parser = new Parser(sc.nextLine());
                parser.parse();
                commandType = parser.getCommandType();
                taskDetails = parser.getTaskDetails();
                date = parser.getDate();

                if (commandType.equals("bye")) {
                    break;

                } else if (commandType.equals("list")) {
                    list();

                } else if (commandType.equals("todo")) {
                    todo(taskDetails);

                } else if (commandType.equals("event")) {
                    event(taskDetails, date);

                } else if (commandType.equals("deadline")) {
                    deadline(taskDetails, date);

                } else if (commandType.equals("done")) {
                    done(Integer.parseInt(taskDetails));

                } else if (commandType.equals("delete")) {
                    delete(Integer.parseInt(taskDetails));

                } else { }

                storage.addData(list.getList());
                System.out.println(LINE);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }

        sc.close();
        end();
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String start = "Hello! I'm Duke, your personal assistant. \nWhat can I do for you?";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(start);
        System.out.println(LINE);
    }

    public void end() {
        String end = "Goodbye! Hope to see you again soon. :)";
        System.out.println(LINE);
        System.out.println(end);
        System.out.println(LINE);
    }

    public void list() {
        if (list.isEmpty()) {
            System.out.println("You have no tasks, go out and have fun! ~");
        } else {
            System.out.println("Here is your to-do list:\n");
            for (int i = 1; i <= list.getTotalTasks(); i++) {
                System.out.println(i + ". " + list.get(i - 1).toString());
            }
        }
    }

    public void done(int taskNumber) {
        list.getList().get(taskNumber - 1).completed();
        System.out.println("You've completed this task:");
        System.out.println((list.getList().get(taskNumber - 1)).toString());
    }

    public void delete(int taskNumber) {
        String deleted = (list.getList().get(taskNumber - 1)).toString();
        list.remove(taskNumber - 1);
        System.out.println("Ok, this task has been kicked off your to-do list:");
        System.out.println(deleted);
        System.out.println(list.toString());
    }

    public void todo(String task) {
        Todo todo = new Todo(task, false);
        list.add(todo);
        System.out.println("I've added this task:\n");
        System.out.println(todo.toString());
        System.out.println(list.toString());
    }

    public void event(String task, String date) {
        Event event = new Event(task, date, false);
        list.add(event);
        System.out.println("I've added this task:\n");
        System.out.println(event.toString());
        System.out.println(list.toString());
    }

    public void deadline(String task, String date) {
        Deadline deadline = new Deadline(task, date, false);
        list.add(deadline);
        System.out.println("I've added this task:\n");
        System.out.println(deadline.toString());
        System.out.println(list.toString());
    }

}
