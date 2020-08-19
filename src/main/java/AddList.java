public class AddList {
    String intro = "Hello I am Duke!\nWhat can I help you with?";
    String goodbye = "Goodbye. See you soon!";
    String line = "---------------------------------------------";
    String[] items;
    int idx;

    public AddList() {
        this.items = new String[100];
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
        this.items[this.idx] = input;
        this.idx++;
        this.addLines(String.format("   Added: %s", input));
    }

    public void display() {
        System.out.println(this.line);
        for (int i = 0; i < this.idx; i++) {
            System.out.println(String.format("    %d. %s", i + 1, this.items[i]));
        }
        System.out.println(this.line);
    }

    public void allocate(String input) {
        if (input.equals("bye")) {
            this.addLines(this.goodbye);
        } else if (input.equals("list")) {
            this.display();
        } else {
            this.add(input);
        }
    }
}