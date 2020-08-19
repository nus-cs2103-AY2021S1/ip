public class AddList {
    String intro = "Hello I am Duke!\nWhat can I help you with?";
    String goodbye = "Goodbye. See you soon!";
    String line = "---------------------------------------------";

    public List() {
        System.out.println(this.line);
        System.out.println(this.intro);
        System.out.println(this.line);
    }

    public void echo(String input) {
        System.out.println(this.line);
        if (input.equals("bye")) {
            System.out.println(this.goodbye);
        }
        System.out.println("    " + input);
        System.out.println(this.line);
    }
}