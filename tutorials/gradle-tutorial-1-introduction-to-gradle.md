# Gradle Tutorial 1 - Setting up Gradle

Gradle is a _build automation tool_ used to automate build processes.
There are many ways of integrating Gradle into a project.
In this guide, we will be using the _Gradle wrapper_.

## Primer to Gradle

As a developer, you write a build file that describes the project. 
 
A build file mainly consists of _plugins_, _tasks_ and _properties_. 

Plugins extend the functionality of Gradle. The `java` plugin adds support for `Java` projects.

Tasks are reusable blocks of logic. 
For example, the task `clean` simply deletes the project build directory. 
Tasks can be composed of other tasks or be dependent on another task. 
You might be surprised to find that a simple `check` will invoke another eight tasks at the very least!  

Properties change the behavior of tasks.
For instance, `mainClassName` of the `application` plugin is a compulsory property which tells Gradle which class is the entrypoint to your application.
As Gradle favors `convention over configuration`, there is not much to you need to configure if you follow the recommended directory structure. 

## Getting started 

1. Merge [this branch](https://github.com/se-edu/duke/tree/gradle). This will add the Gradle wrapper to your project.

2. Navigate to the root directory of your project and type `gradlew run`.

For users of IntelliJ IDEA, you can import the Gradle project by `Help > Find Action > Import Gradle Project`.

![Import Gradle](assets/ImportGradle.png)

After this, IntelliJ IDEA will identify your project as a Gradle project and you will gain access to the `Gradle Toolbar`.
Through the toolbar, you run Gradle tasks and view your project's dependencies.

## Using Gradle

Simply type `gradlew {taskName}` into the terminal and Gradle will run the task!
For example, you can type `gradlew tasks` and Gradle will show you a list of tasks available for your project.
Some plugins may add more helpful tasks so be sure to check the documentation!

If you're using IntelliJ IDEA, you can click on the Gradle icon in the Gradle toolbar and create a new run configuration. 

![Gradle icon](assets/GradleIcon.png)

Having a run configuration will save you a few keypresses in the long run.   

## Adding plugins

Gradle plugins are reusable units of build logic. 
Most common build tasks are provided as core plugins by Gradle. 
Given below are instructions on how to use some useful plugins:

### Checkstyle
To add support for _Checkstyle_, a tool to check if your code complies with coding standards.
Since Checkstyle is a core plugin, simply add the line `id 'checkstyle` into the `plugins` block.

Your build file should look something like this now 

```groovy
plugins {
    id 'java'
    id 'application'
    id 'checkstyle'
}
// ... code omitted for brevity ... 
```

Checkstyle expects configuration files to for checkstyle to be in `./config/checkstyle/` by convention.
You can find the configuration files used in later projects [here](https://github.com/se-edu/addressbook-level3/tree/master/config/checkstyle).

The plugin adds a few _tasks_ to your project.
Run `gradlew checkstyleMain checkstyleTest` to verify that you have set up Checkstyle properly.

To find out the full list of tasks available to you, you can run `gradlew tasks --all`.

### Shadow

Shadow is a plugin that helps you package your application into an executable jar file.
Add the following line to your Gradle build file:

```groovy
plugin {
    //...
    id 'com.github.johnrengelman.shadow' version '5.1.0'
    //...
}
```

The plugin can be configured by setting some properties.
Let's try to produce a jar file with the name in format of `{baseName}-{version}.jar`.

Add the following block to your build file:

```groovy
//Publishes an executable jar to ./build/libs/ 
shadowJar {
    archiveBaseName = "duke"
    archiveVersion = "0.1.3"
    archiveClassifier = null
    archiveAppendix = null
}
```

Now you can run the task `shadowJar` with the command `gradlew shadowJar`.
Are you able to execute your jar file with `java -jar {jarName}`?

## Adding dependencies 

### JUnit 5

JUnit is a testing framework for Java. 
It allows developers to write tests and run them.

Add the following dependency to your build file:

```groovy
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.5.0'
}
```

Then, configure Gradle to use JUnit by adding the following block to your build file:

```groovy

test {
    useJUnitPlatform()
}
```
By convention, tests belong in `src/test` folder. 
Create a new `test` folder in under `src`.

```
src
├─main
│  ├─java
│  │  └─seedu
│  │      └─duke
│  └─resources
│      └─view
└─test
    └─java
        └─seedu
            └─duke
```

If you have imported your Gradle project into IntelliJ IDEA, you will notice that IDEA is able to mark the test 
directory as the Test root (colored in green by default) automatically.

You can now write a test and run it with `gradlew test`.

## Further reading

Now that you have a general idea of how to accomplish basic tasks with Gradle, here's a list of material you can read
 to further your understanding.
 
- [Official Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- [Google's checkstyle file](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)
- [Shadow](https://imperceptiblethoughts.com/shadow/introduction/)
- [Official JUnit Documentation](https://junit.org/junit5/docs/current/user-guide/#writing-tests)
- [AddressBook Level-4's build file](https://github.com/se-edu/addressbook-level4/blob/master/build.gradle)