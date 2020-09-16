# JavaFX Tutorial Part 1 – Introduction

## JavaFX lifecycle of an application

Imagine yourself as a director of a play. First you provision the props that you will feature in your play. These can be hand props for your actors to interact with or even set dressings just to liven up the background. You then decide where to layout the props for every scene. With a proper script in hand, you can finally approach a theatre and request for a stage. There on, it’s just a matter of pulling the curtains on your masterpiece.

![Hierarchy of Objects in JavaFX](assets/JavaFxHierarchy.png)

A JavaFX application is like a play you are directing. Instead of creating props, you create `Nodes` (`Nodes` are the fundamental building blocks of a JavaFX application), and place them onto a `Scene` (a scene is a graph of `Node`s). Then, you set your `Scene` on a `Stage` provided by JavaFX. When you call `Stage#show()` method, JavaFX renders a window with your `Stage` on it.

## Setting up Java FX

### If you are not using Gradle

1. Download the [JavaFX 11 SDK](https://gluonhq.com/products/javafx/) and unzip it.

1. Import the `libs` folder from the SDK into your project (note: `JAVAFX_HOME` is the directory in which you have unzipped the JavaFX SDK). 

   `File` > `Project Structure` > `Libraries` > `+` > `Java` > `{JAVAFX_HOME}/lib`

1. From `Run` > `Edit Configurations`, add the following line into your `VM options` for each of the `main` classes.

   `--module-path {JAVAFX_HOME}/lib --add-modules javafx.controls,javafx.fxml`<br>
   e.g., `--module-path C:/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml`

### If you are using Gradle

Update your `build.gradle` to include the following lines:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    String javaFxVersion = '11'

    implementation group: 'org.openjfx', name: 'javafx-base', version: javaFxVersion, classifier: 'win'
    implementation group: 'org.openjfx', name: 'javafx-base', version: javaFxVersion, classifier: 'mac'
    implementation group: 'org.openjfx', name: 'javafx-base', version: javaFxVersion, classifier: 'linux'
    implementation group: 'org.openjfx', name: 'javafx-controls', version: javaFxVersion, classifier: 'win'
    implementation group: 'org.openjfx', name: 'javafx-controls', version: javaFxVersion, classifier: 'mac'
    implementation group: 'org.openjfx', name: 'javafx-controls', version: javaFxVersion, classifier: 'linux'
    implementation group: 'org.openjfx', name: 'javafx-fxml', version: javaFxVersion, classifier: 'win'
    implementation group: 'org.openjfx', name: 'javafx-fxml', version: javaFxVersion, classifier: 'mac'
    implementation group: 'org.openjfx', name: 'javafx-fxml', version: javaFxVersion, classifier: 'linux'
    implementation group: 'org.openjfx', name: 'javafx-graphics', version: javaFxVersion, classifier: 'win'
    implementation group: 'org.openjfx', name: 'javafx-graphics', version: javaFxVersion, classifier: 'mac'
    implementation group: 'org.openjfx', name: 'javafx-graphics', version: javaFxVersion, classifier: 'linux'
}
```

## Writing your first program

As customary, let’s start off with a simple “Hello World” program. Modify your `Duke` class to extend `javafx.application.Application`. This requires you to override the `Application#start()` method and provide a concrete implementation. Notice that the method signature for `Application#start()` has a parameter `Stage`. This is the _primary stage_ that JavaFX provides.

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    
    // ...

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
```

Note how we have created a `Label` to contain the text that we want to show. We then create the `Scene` and set its content. Finally, we set the stage and show it. 

Next, we create another Java class, `Launcher`, as an entry point to our application.
The `Launcher` class is reproduced below in its entirety.

```java
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
```

Run `Launcher` and you should see something like this:

![Hello World](assets/HelloWorld.png) 

Congratulations! You have created your first GUI application!

## Exercises

1. We mentioned that `Node`s are the fundamental building blocks of JavaFX and used a `Label` as our root node in the HelloWorld application.
   1. What are some of the other types of `Node`s?
   1. How does JavaFX group them?

1. `Node`s can be interacted with like Plain Old Java Objects (POJO).
   1. What properties of a `Label` can you change programmatically?
   1. Try changing the `Label` to have a font of Arial at size 50.

1. You’ve learnt that a `Stage` can be thought of as a window. 
   1. Can you have more than one `Stage` an application?
   1. Try creating another stage and showing it! What happens?

--------------------------------------------------------------------------------
**Authors:**
* Initial Version: Jeffry Lum
