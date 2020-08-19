public class Responder {
    public static void responder() {
        String input = ReadIn.readIn();
        while (true) {
            if(input.equals("bye")) {
                System.out.println(Exit.exit());
                break;
            } else if (input.equals("list")) {
                System.out.println(TaskManager.returnList());
            } else if (input.substring(0,5).equals("done ")) {
                int i = Integer.valueOf(input.substring(5));
                System.out.println(TaskManager.markAsDone(i));
            } else if (input.substring(0,7).equals("delete ")) {
                int i = Integer.valueOf(input.substring(7));
                System.out.println(TaskManager.deleteTask(i));
            }
            else {
                System.out.println(TaskManager.addTask(input));
            }
            input = ReadIn.readIn();
        }
    }
}
