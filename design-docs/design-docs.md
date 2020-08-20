# Week 2 (17 AUG - 23 AUG)

## Preliminary Design

The task is to build an extensible terminal chat application named duke.runner.Runner. Having some experience with Android, FullStack web development, and system design, I decided to glance over the future specs and enhancements and read up a bit about JavaFX FXML (the GUI library) before starting. This gives me a feel of the scope and scale of project duke.runner.Runner so I can choose an architectural pattern to roll with. For now, the MVVM (Model-View-ViewModel) pattern seems promising, along with a DAO (Data Access Object) to encapsulate communication with the data layer.

Since I like microservice architecture and modular applications a lot, my goal is to make the transition from CLI to GUI or file-based storage to DB-based storage as simple as "plug-n-play". Perhaps my design may evolve or change over the next few weeks. We'll see.

## Project Management

Just like in AGILE, project milestones are issued and checked in weekly sprints. This is nice because I plan to learn GitHub project board to manage the development process with Epics, User Stories, and issues.

## Gradle and JUnit

I think I'll start by migrating the project over to Gradle and conduct testing with JUnit 5 rather than running the `.sh` script over and over. I'll also use gradle to build the `.jar`. If I have time I might set up CircleCI/Travis CI pipeline to automatically test and build the `.jar` and release it using semantic versioning. This too is a great opportunity for me to practice TDD (Test-Driven Development) as well, where unit tests are written before the actual code.

## Packages

To be updated.

# Week 3 (24 AUG - 30 AUG)

```
     August 2020
Mo Tu We Th Fr Sa Su
                1  2
 3  4  5  6  7  8  9
10 11 12 13 14 15 16
17 18 19 20 21 22 23
24 25 26 27 28 29 30
31
```
