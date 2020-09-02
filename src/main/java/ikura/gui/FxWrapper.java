// FxWrapper.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui;

import ikura.task.Task;
import ikura.gui.GuiFrontend;
import ikura.gui.components.MainView;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Application;

public class FxWrapper extends Application {

    // this is kind of hack; javafx insists on "taking over" main(), so we can't control
    // constructors or anything like that. in the run() function then we just set a
    // static field so that we can use it from the javafx start() method.
    private static GuiFrontend frontend;

    public static void run(GuiFrontend frontend) {
        FxWrapper.frontend = frontend;

        Application.launch(new String[] { });
    }

    @Override
    public void start(Stage stage) {

        var main = new MainView(this.frontend, 1280, 800);
        var scene = new Scene(main);

        stage.setTitle(GuiFrontend.BOT_NAME);

        stage.setScene(scene);
        stage.requestFocus();
        stage.show();
    }
}
