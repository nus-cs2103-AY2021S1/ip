import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        list.add(task);
        System.out.printf("     Now you have %d tasks in the list.\n", list.size());
        System.out.println("    ____________________________________________________________");
    }

    // initAdd method for adding task to list during initialisation
    public void initAdd(Task task) {
        list.add(task);
    }

    public void listOut() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s \n", i + 1, list.get(i));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void delete(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter delete with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task: ");

            System.out.println("       " + task);
            System.out.printf("     Now you have %d tasks in the list.\n", list.size());
            System.out.println("    ____________________________________________________________");
            System.out.println();
        }
    }

    public void done(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter done with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                // if string after done cannot be parsed to integer
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            task.completed();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");

            System.out.println("       " + task);
            System.out.println("    ____________________________________________________________");
            System.out.println();
        }
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }
}
