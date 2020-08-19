public class Responder {
    public static void responder() {
        String input = ReadIn.readIn();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                System.out.println(MyList.addTask(input));
            } else {
                System.out.println(MyList.returnList());
            }
            input = ReadIn.readIn();
        }
        System.out.println(Exit.exit());
    }
}
