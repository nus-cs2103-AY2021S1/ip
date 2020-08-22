import java.util.List;
import java.util.ArrayList;

public class ListOfItems {
    List<Task> list;
    int index;
    String divider = "____________________________________________________________";

    ListOfItems() {
        this.list = new ArrayList<>();
        this.index = 0;
    }

    void getList() throws DukeException {
        System.out.println(divider);
        if (list.size() == 0) {
            throw new DukeException("List is empty, you have free time (for now)! YAY :D" + "\n" + divider);
        } else {
            System.out.println("Here are the task(s) in your list: ");
            for (int i = 0; i < index; i++) {
                System.out.println(list.get(i).id + "." + list.get(i));
            }
        }
        System.out.println(divider);
    }

    void doneItem(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input.substring(5)); // retrieve number after "done "
            Task task = list.get(number - 1);
            if (task.isDone) {
                System.out.println("Task already done!");
            } else {
                task.markedDone();
                String message = "Good job! I've marked this task as done: ";
                System.out.println(divider + "\n" + message + "\n" +
                        task + "\n" + divider);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("\n" + divider + "\n" + "Sorry, you did not enter a valid number. Please try again." +
                    "\n" + divider);
        }
    }

    void deleteItem(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input.substring(7));
            Task task = list.get(number - 1);
            for (int i = number; i < list.size(); i++) {
                list.get(i).id = list.get(i).id - 1;
            }
            list.remove(task);
            index--;
            System.out.println(divider);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task);
            System.out.println("Now you have " + index + " tasks in the list.");
            System.out.println(divider);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("\n" + divider + "\n" + "Whoops, you did not enter a valid number." +
                    "\n" + divider);
        }
    }

    void addItem(String input) throws DukeException {
        String addedMessage = "Got it. I've added this task: ";
        String totalMessage = "Now you have " + (index + 1) + " task(s) in the list.";

        if (input.contains("todo")) {
            try {
                String description = input.substring(5);

                System.out.println(divider + "\n" + addedMessage);
                list.add(index, new Todo(description, index + 1));
                System.out.println(list.get(index));
                System.out.println(totalMessage + "\n" + divider);
                index++;
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\n" + divider + "\n" +
                        "Whoops, you did not fill in the details of the Todo properly :(" +
                        "\n" + divider);
            }
        } else if (input.contains("deadline")) {
            try {
                String[] info = input.split("/", 2);
                String description = info[0].substring(9);
                String dueDateTime = info[1];

                System.out.println(divider + "\n" + addedMessage);
                list.add(index, new Deadline(description, index + 1, dueDateTime));
                System.out.println(list.get(index));
                System.out.println(totalMessage + "\n" + divider);
                index++;
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\n" + divider + "\n" +
                        "Whoops, you did not fill in the details of the Deadline properly :(" +
                        "\n" + divider);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\n" + divider + "\n" +
                        "Whoops, you did not fill in the due date/time of the Deadline." +
                        "\n" + divider);
            }
        } else if (input.contains("event")) {
            try {
                String[] info = input.split("/", 2);
                String description = info[0].substring(6);
                String duration = info[1];

                System.out.println(divider + "\n" + addedMessage);
                list.add(index, new Event(description, index + 1, duration));
                System.out.println(list.get(index));
                System.out.println(totalMessage + "\n" + divider);
                index++;
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("\n" + divider + "\n" +
                        "Whoops, you did not fill in the details of the Event properly :(" +
                        "\n" + divider);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\n" + divider + "\n" +
                        "Whoops, you did not fill in the duration of the Event." +
                        "\n" + divider);
            }
        } else {
            throw new DukeException("\n" + divider + "\n" +
                    "Sorry, you did not enter a valid command! Please try again." +
                    "\n" + divider);
        }
    }
}
