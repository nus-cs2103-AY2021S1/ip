import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println("________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            String s = sc.next();
            if (s.equals("bye")) {
                System.out.println("________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                System.exit(0);
            } else if (s.equals("list")) {
                System.out.println("________________________________________");
                System.out.println("Here is your current list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
                System.out.println("________________________________________");
            } else if (s.equals("done")) {
                try {
                    int index = Integer.parseInt(sc.next()) - 1;
                    if (index < tasks.size() && index >= 0) {
                        System.out.println("________________________________________");
                        System.out.println("Nice! I've marked this task as done: ");
                        tasks.get(index).markAsDone();
                        System.out.println(tasks.get(index).toString());
                    } else {
                        System.out.println("________________________________________");
                        System.out.println("Integer should have a corresponding task in the list.");
                    }
                } catch (Exception e) {
                    System.out.println("________________________________________");
                    System.out.println("Please key in this format: done <integer>");
                } finally {
                    System.out.println("________________________________________");
                }
            } else if (s.equals("delete")) {
                try {
                    int index = Integer.parseInt(sc.next()) - 1;
                    if (index < tasks.size() && index >= 0) {
                        System.out.println("________________________________________");
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(tasks.get(index).toString());
                        tasks.remove(index);
                        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                    } else {
                        System.out.println("________________________________________");
                        System.out.println("Integer should have a corresponding task in the list.");
                    }
                } catch (Exception e) {
                    System.out.println("________________________________________");
                    System.out.println("Please key in this format: done <integer>");
                } finally {
                    System.out.println("________________________________________");
                }
            } else {
                Task temp;
                try {
                    if (s.startsWith("todo")) {
                        s = sc.nextLine();
                        if (s.isEmpty()) {
                            throw new EmptyDescriptionException("Description cannot be empty");
                        } else {
                            temp = new ToDo(s);
                            tasks.add(temp);
                        }
                    } else if (s.startsWith("deadline")) {
                        s = sc.nextLine();
                        if (s.isEmpty()) {
                            throw new EmptyDescriptionException("Description cannot be empty");
                        } else {
                            String[] parts = s.split("\\s*/by\\s*");
                            temp = new Deadline(parts[0], parts[1]);
                            tasks.add(temp);
                        }
                    } else if (s.startsWith("event")) {
                        s = sc.nextLine();
                        if (s.isEmpty()) {
                            throw new EmptyDescriptionException("Description cannot be empty");
                        } else {
                            String[] parts = s.split("\\s*/at\\s*");
                            temp = new Event(parts[0], parts[1]);
                            tasks.add(temp);
                        }
                    } else {
                        sc.nextLine();
                        throw new Exception("Wrong command.");
                    }
                    System.out.println("________________________________________");
                    System.out.println("Yay! New task added: ");
                    System.out.println("   " + temp.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                } catch (EmptyDescriptionException e) {
                    System.out.println("________________________________________");
                    System.out.println("Description cannot be empty!");
                } catch (Exception e) {
                        System.out.println("________________________________________");
                        System.out.println("Start with either todo, deadline or event to add new task.");
                } finally {
                    System.out.println("________________________________________");
                }
            }
        }
    }
}
