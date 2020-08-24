public class Ui {
    public static void greet() {
        System.out.println(TextFormatter.LOGO);
        System.out.println(TextFormatter
                .getFormattedText(Message.WELCOME.toString()));
    }

    public static void bye() {
        System.out.println(TextFormatter
                .getFormattedText(Message.FAREWELL.toString()));
    }

    public static void displayTasks(TaskList list) {
        StringBuilder sb = new StringBuilder(Message.LIST.toString());
        for (int i = 0; i < list.size(); i++) {
            sb.append(" ").append(i + 1).append(". ")
                    .append(list.get(i).toString()).append("\n");
        }
        System.out.println(TextFormatter.getFormattedText(sb.toString()));
    }


    public static void displayTasks(TaskList list, int[] listIndex, String date) {
        StringBuilder sb = new StringBuilder("Here are the task on " + date + ":\n");
        for (int index : listIndex) {
            sb.append(" ").append(index + 1).append(". ")
                    .append(list.get(index).toString()).append("\n");
        }
        System.out.println(TextFormatter.getFormattedText(sb.toString()));
    }
}
