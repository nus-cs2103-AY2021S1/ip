public class GreetingsFarewell {

    public GreetingsFarewell() {
    }

    String line = "____________________________________________________________";
    String greeting = "Hello! I'm Duke\n" +
            "     What can I do for you?";
    String goodbye = "Bye. Hope to see you again soon!";

    protected void greeting() {
        String s = "";
        s = line + "\n" + greeting + "\n" + line;
        System.out.println(s);
    }

    protected void farewell() {
        String s = "";
        s = line + "\n" + goodbye + "\n" + line;
        System.out.println(s);
    }


}
