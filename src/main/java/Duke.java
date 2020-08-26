import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> lists = new ArrayList<Task>();
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] task = line.split(" ");
                if (task[0].equals("T")) {
                    Task todo = new Todo(task[4]);
                    if (task[2].equals("✓")) {
                        todo.markDone();
                    }
                    lists.add(todo);
                } else if (task[0].equals("D")) {
                    Task deadline = new Deadline(task[4], LocalDateTime.parse(task[6]));
                    if (task[2].equals("✓")) {
                        deadline.markDone();
                    }
                    lists.add(deadline);
                } else if (task[0].equals("E")) {
                    Task event = new Event(task[4], LocalDateTime.parse(task[6]));
                    if (task[2].equals("✓")) {
                        event.markDone();
                    }
                    lists.add(event);
                } else {
                    continue;
                }
            }
            Scanner sc = new Scanner(System.in);
            String logo = "      ___                __     \n"
                    + "     |  _ \\   ____      |  |    \n"
                    + "     | |_| | / __  \\  __|  |__  \n"
                    + "     |  _ / | |  |  ||__    __| \n"
                    + "     | |    | |_/   |   |  |    \n"
                    + "     |_|     \\___/|_|   |__|    \n";
            String[] greetingTexts = {"Hello! I'm Pat", "What can I do for you?"};
            Response greeting = new Response(greetingTexts);
            System.out.println("     Hello from\n" + logo);
            System.out.println(greeting.getResponse());
            while (sc.hasNext()) {
                String received = sc.nextLine();
                if (received.equals("bye")) {
                    String[] byeMessage = {"Bye. Hope to see you again soon!"};
                    Response bye = new Response(byeMessage);
                    System.out.println(bye.getResponse());
                    break;
                } else if (received.equals("list")) {
                    Response list = new Response(lists.toArray(new Task[0]), Response.Tag.LIST);
                    System.out.println(list.getResponse());
                } else {
                    String[] test = received.split(" ");
                    if (test[0].equals("done")) {
                        try {
                            if (lists.size() == 0) {
                                throw new IndexOutOfBoundsException();
                            }
                            if (test.length == 1) {
                                throw new MissingNumberException();
                            }
                            int idx = Integer.parseInt(test[1]) - 1;
                            Task target = lists.get(idx);
                            target.markDone();
                            Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + target});
                            System.out.println(msg.getResponse());
                        } catch (IndexOutOfBoundsException e) {
                            Response msg = new Response(new String[]{"The number does not exist!"});
                            System.out.println(msg.getResponse());
                        } catch (MissingNumberException e) {
                            Response msg = new Response(new String[]{"Please select the task that you want to mark done!"});
                        }
                    } else if (test[0].equals("deadline")) {
                        try {
                            String[] str = received.split("deadline ")[1].split(" /");
                            if (str.length == 1) {
                                throw new MissingDeadlineException();
                            } else {
                                try {
                                    String description = str[0];
                                    String by = str[1].split("by ")[1];
                                    LocalDateTime byTime = LocalDateTime.parse(by);
                                    Deadline deadline = new Deadline(description, byTime);
                                    lists.add(deadline);
                                    Response msg = new Response(new Task[]{deadline}, Response.Tag.ADD, lists.size());
                                    System.out.println(msg.getResponse());
                                } catch (DateTimeParseException e) {
                                    Response msg = new Response(new String[]{"Format of date and time is incorrect! Please fill in the date and time following the format below.",
                                            "YYYY-MM-DDTHH:MM:SS"});
                                    System.out.println(msg.getResponse());
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            MissingDescriptionException realError = new MissingDescriptionException("a deadline");
                            Response msg = new Response(new String[]{String.valueOf(realError)});
                            System.out.println(msg.getResponse());
                        } catch (MissingDeadlineException e) {
                            Response msg = new Response(new String[]{String.valueOf(e)});
                            System.out.println(msg.getResponse());
                        }
                    } else if (test[0].equals("delete")) {
                        try {
                            if (lists.size() == 0) {
                                throw new IndexOutOfBoundsException();
                            }
                            if (test.length == 1) {
                                throw new MissingNumberException();
                            }
                            int idx = Integer.parseInt(test[1]) - 1;
                            Response msg = new Response(new Task[]{lists.get(idx)}, Response.Tag.REMOVE, lists.size() - 1);
                            System.out.println(msg.getResponse());
                            lists.remove(idx);
                        } catch (IndexOutOfBoundsException e) {
                            Response msg = new Response(new String[]{"The number does not exist!"});
                            System.out.println(msg.getResponse());
                        } catch (MissingNumberException e) {
                            Response msg = new Response(new String[]{"Please select the task that you want to delete!"});
                            System.out.println(msg.getResponse());
                        }
                    } else if (test[0].equals("find")) {
                        try {
                            if (test.length == 1) {
                                throw new DukeException();
                            }
                            ArrayList<Task> satisfiedTasks = new ArrayList<>();
                            for (int i = 0; i < lists.size(); i++) {
                                if (lists.get(i).task.contains(test[1])) {
                                    satisfiedTasks.add(lists.get(i));
                                }
                            }
                            Response msg = new Response(satisfiedTasks.toArray(new Task[0]), Response.Tag.FIND);
                            System.out.println(msg.getResponse());
                        } catch (DukeException e) {
                            Response msg = new Response(new String[]{"Please include the keyword!"});
                            System.out.println(msg.getResponse());
                        }
                    } else {
                        if (test[0].equals("todo")) {
                            try {
                                String description = received.split("todo ")[1];
                                Todo todo = new Todo(description);
                                lists.add(todo);
                                Response msg = new Response(new Task[]{todo}, Response.Tag.ADD, lists.size());
                                System.out.println(msg.getResponse());
                            } catch (ArrayIndexOutOfBoundsException err) {
                                MissingDescriptionException realError = new MissingDescriptionException("a todo");
                                Response msg = new Response(new String[]{String.valueOf(realError)});
                                System.out.println(msg.getResponse());
                            }
                        } else if (test[0].equals("deadline")) {
                            try {
                                String[] str = received.split("deadline ")[1].split(" /by ");
                                if (str.length == 1) {
                                    throw new MissingDeadlineException();
                                } else {
                                    try {
                                        String description = str[0];
                                        String by = str[1];
                                        Deadline deadline = new Deadline(description, LocalDateTime.parse(by));
                                        lists.add(deadline);
                                        Response msg = new Response(new Task[]{deadline}, Response.Tag.ADD, lists.size());
                                        System.out.println(msg.getResponse());
                                    } catch (DateTimeParseException e) {
                                        Response msg = new Response(new String[]{"Format of date and time is incorrect! Please fill in the date and time following the format below.",
                                                "YYYY-MM-DDTHH:MM:SS"});
                                        System.out.println(msg.getResponse());
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                MissingDescriptionException realError = new MissingDescriptionException("a deadline");
                                Response msg = new Response(new String[]{String.valueOf(realError)});
                                System.out.println(msg.getResponse());
                            } catch (MissingDeadlineException e) {
                                Response msg = new Response(new String[]{String.valueOf(e)});
                                System.out.println(msg.getResponse());
                            }
                        } else if (test[0].equals("event")) {
                            try {
                                String[] str = received.split("event ")[1].split(" /at ");
                                if (str.length == 1) {
                                    throw new MissingTimeException();
                                } else {
                                    try {
                                        String description = str[0];
                                        String at = str[1];
                                        Event event = new Event(description, LocalDateTime.parse(at));
                                        lists.add(event);
                                        Response msg = new Response(new Task[]{event}, Response.Tag.ADD, lists.size());
                                        System.out.println(msg.getResponse());
                                    } catch (DateTimeParseException e) {
                                        Response msg = new Response(new String[]{"Format of date and time is incorrect! Please fill in the date and time following the format below.",
                                                "YYYY-MM-DDTHH:MM:SS"});
                                        System.out.println(msg.getResponse());
                                    }
                                }
                            } catch (ArrayIndexOutOfBoundsException e) {
                                MissingDescriptionException realError = new MissingDescriptionException("an event");
                                Response msg = new Response(new String[]{String.valueOf(realError)});
                                System.out.println(msg.getResponse());
                            } catch (MissingTimeException e) {
                                Response msg = new Response(new String[]{String.valueOf(e)});
                                System.out.println(msg.getResponse());
                            }
                        } else {
                            UnknownInputException ue = new UnknownInputException();
                            Response msg = new Response(new String[]{String.valueOf(ue)});
                            System.out.println(msg.getResponse());
                        }
                    }
                }
                try {
                    String tasks = "";
                    for (int i = 0; i < lists.size(); i++) {
                        Task t = lists.get(i);
                        tasks += t.writeMessage() + "\n";
                    }
                    FileWriter fw = new FileWriter("data/duke.txt", false);
                    fw.write(tasks);
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Folder or file does not exist yet! Please make sure you have data/duke.txt in ip directory. ");
        }
    }
}
