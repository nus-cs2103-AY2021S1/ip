public class AddList {
    private final String intro = "Hello I am Duke!\nWhat can I help you with?";
    private final String goodbye = "Goodbye. See you soon!";
    private final String line = "---------------------------------------------";
    private Task[] items;
    private int idx;

    public AddList() {
        this.items = new Task[100];
        this.idx = 0;
        System.out.println(this.line);
        System.out.println(this.intro);
        System.out.println(this.line);
    }

    public void addLines(String input) {
        System.out.println(this.line);
        System.out.println(input);
        System.out.println(this.line);
    }

    public Task handleInput(String input) {
        String type = input.split(" ")[0];
        if (type.equals("todo")) {
            return new Todo(input.substring(5));
        } else if (type.equals("deadline")) {
            String[] dl = input.split(" /by ");
            return new Deadline(dl[0].substring(9), dl[1]);
        } else if (type.equals("event")) {
            String[] e = input.split(" /at ");
            return new Event(e[0].substring(6), e[1]);
        } else {
            System.out.println("Erroneous type");
            return new Task("");
        }
    }

    public void add(String input) {
        Task toAdd = this.handleInput(input);
        this.items[this.idx] = toAdd;
        this.idx++;
        this.addLines(String.format("    Got it. I've added this task:\n    %s\n    Now you have %d tasks in the list.", toAdd, this.idx));
    }

    public void display() {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < this.idx; i++) {
            res += String.format("    %d.%s\n", i + 1, this.items[i]);
        }
        this.addLines(res);
    }

    public void completeTask(int idx) {
        Task t = this.items[idx];
        t.complete();
        this.addLines(String.format("    Nice! I've marked this task as done:\n    %s", t));
    }

    public void allocate(String input) {
        String[] arr = input.split(" ");

        if (arr[0].equals("bye")) {
            this.addLines(this.goodbye);
        } else if (arr[0].equals("list")) {
            this.display();
        } else if (arr[0].equals("done")) {
            int idx = Integer.parseInt(arr[1]) - 1;
            if (idx >= 0 && idx < this.idx) {
                this.completeTask(idx);
            } else {
                this.addLines("Please choose a valid index");
            }
        } else {
            this.add(input);
        }
    }
}