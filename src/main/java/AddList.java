public class AddList {
    String intro = "Hello I am Duke!\nWhat can I help you with?";
    String goodbye = "Goodbye. See you soon!";
    String line = "---------------------------------------------";
    Task[] items;
    int idx;

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

    public void add(String input) {
        this.items[this.idx] = new Task(input);
        this.idx++;
        this.addLines(String.format("   Added: %s", input));
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