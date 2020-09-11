# Duke Chat bot User Guide

## Introduction

Duke chat bot is for people _in need to manage their daily tasks via CLI_.

## Quick Start

* Ensure you have `Java 11` or above installed in your Computer.
* Download latest `duke.jar`.

## Features

1. Add a todo/event/deadline task
1. View the list of tasks
1. Mark certain tasks as completed
1. Delete a task
1. Find a task by keyword
1. Get reminder on upcoming dues

## Usage

### `Todo` - Add a todo task

Store a todo task which does not have time constraint.

Format: `todo TASK_NAME`

Example of usage: 

    * `todo sampleTask` creates the `sampleTask` in the task list.
    * ![todoImage](./todo.png)

### `Event` - Add an event task

Store an event task which needs to be done __at__ a scheduled time.

Format: `event TASK_NAME /at SCHEDULED_TIME` where `SCHEDULED_TIME` follows the format _'YYYY-MM-DD'_.

Example of usage: 

    * `even sampleEvent /at 2020-03-12` creates the `sampleEvent` in the task list.
    * The `sampleEvent` is to be done at __March 12 2020__.
    * ![eventImage](./event.png)

### `Deadline` - Add a deadline task

Store a deadline task which needs to be done __before__ a scheduled time.

Format: `deadline TASK_NAME /by SCHEDULED_TIME` where `SCHEDULED_TIME` follows the format _'YYYY-MM-DD'_.

Example of usage: 

    * `deadline sampleDeadline /by 2020-11-12` creates the `sampleDeadline` in the task list.
    * The `sampleDeadline` is to be done before __Nov 12 2020__.
    * ![deadlineImage](./deadline.png)