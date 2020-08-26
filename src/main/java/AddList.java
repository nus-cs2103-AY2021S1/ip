import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AddList {
    private final String intro = "Hello I am Duke!\nWhat can I help you with?";
    private final String goodbye = "Goodbye. See you soon!";
    private final String line = "---------------------------------------------";
    private ArrayList<Task> items;
    private int total;
    Storage storage;

    public AddList() {
        this.addLines(this.intro);
        this.storage = new Storage();
        this.items = storage.readData();
        this.total = items.size();
    }

    public void addLines(String input) {
        System.out.println(this.line);
        System.out.println(input);
        System.out.println(this.line);
    }

    public void bye() {
        try {
            this.addLines(this.goodbye);
            storage.writeData(this.items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task handleInput(String input) throws InvalidDescriptionException, InvalidTypeException {
        String type = input.split(" ")[0];
        if (type.equals("todo")) {
            if (input.substring(4).equals("") || input.substring(5).equals("")) {
                throw new InvalidDescriptionException();
            }
            return new Todo(input.substring(5));
        } else if (type.equals("deadline")) {
            String[] dl = input.split(" /by ");
            return new Deadline(dl[0].substring(9), dl[1]);
        } else if (type.equals("event")) {
            String[] e = input.split(" /at ");
            return new Event(e[0].substring(6), e[1]);
        } else {
            throw new InvalidTypeException();
        }
    }

    public void add(String input) {
        try {
            Task toAdd = this.handleInput(input);
            this.items.add(toAdd);
            this.total++;
            this.addLines(String.format("    Got it. I've added this task:\n    %s\n    Now you have %d tasks in the list.", toAdd, this.total));
        } catch (InvalidDescriptionException e) {
            this.addLines(e.toString());
        } catch (InvalidTypeException e) {
            this.addLines(e.toString());
        }
    }

    public void display() {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < this.total; i++) {
            res += String.format("    %d.%s\n", i + 1, this.items.get(i));
        }
        this.addLines(res);
    }

    public void completeTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.total) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        t.complete();
        this.addLines(String.format("    Nice! I've marked this task as done:\n    %s", t));
    }

    public void deleteTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.total) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        this.items.remove(idx);
        this.total--;
        this.addLines(String.format("    Nice! I've removed this task:\n    %s\n    Now you have %d tasks in the list.", t, this.total));
    }

    public void allocate(String input) {
        String[] arr = input.split(" ");

        if (arr[0].equals("bye")) {
            this.bye();
        } else if (arr[0].equals("list")) {
            this.display();
        } else if (arr[0].equals("done")) {
            int idx = Integer.parseInt(arr[1]) - 1;
            try {
                this.completeTask(idx);
            } catch (InvalidIndexException e) {
                this.addLines(e.toString());
            }
        } else if (arr[0].equals("delete")) {
            int idx = Integer.parseInt(arr[1]) - 1;
            try {
                this.deleteTask(idx);
            } catch (InvalidIndexException e) {
                this.addLines(e.toString());
            }
        } else {
            this.add(input);
        }
    }
}