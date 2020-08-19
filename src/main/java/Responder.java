public class Responder {
    public static void responder() {
        String input = ReadIn.readIn();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(TaskManager.returnList());
            } else if (input.substring(0,5).equals("done ")) {
                int i = Integer.valueOf(input.substring(5));
                System.out.println(TaskManager.markAsDone(i));
            } else {
                System.out.println(TaskManager.addTask(input));
            }
            input = ReadIn.readIn();
        }
        System.out.println(Exit.exit());
    }
}
