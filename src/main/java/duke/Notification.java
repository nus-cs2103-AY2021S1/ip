package duke;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.List;

import duke.task.Task;

public class Notification {

    /**
     * Notify the user of incoming deadlines.
     * @param taskList Tasklist loaded
     * @throws AWTException If fails to add.
     */
    public static void notifyTime(TaskList taskList) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage(Notification.class.getResource("/img/peepoHappy.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Duke");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Duke Alert");
        tray.add(trayIcon);

        List<Task> list = taskList.getList();

        StringBuilder stringBuilder = new StringBuilder();
        boolean isNotifiable = false;
        for (Task task: list) {
            if (task.isComing(2)) {
                isNotifiable = true;
                stringBuilder.append(task.getNotification()).append("\n");
            }
        }

        if (isNotifiable) {
            trayIcon.displayMessage("Duke tasks incoming", "Incoming tasks: \n" + stringBuilder.toString(),
                    TrayIcon.MessageType.INFO);
        }

        tray.remove(trayIcon);
    }
}
