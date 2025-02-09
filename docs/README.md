# Chloe User Guide

## Introduction
Chloe is a simple interactive task manager that helps you keep track of your tasks, deadlines, and events efficiently.

## Adding Todos
To add a **todo**, use the `todo` command followed by the task description.

### Example:
```
todo Buy groceries
```

### Expected Outcome:
```
**********************************************
Added: [ ][T] Buy groceries
**********************************************
```

## Adding Deadlines
To add a **deadline**, use the `deadline` command followed by the task description and the due date/time, separated by `/by`.

### Example:
```
deadline Submit assignment /by Monday 9pm
```

### Expected Outcome:
```
**********************************************
Added: [ ][D] Submit assignment (by: Monday 9pm)
**********************************************
```

## Adding Events
To add an **event**, use the `event` command followed by the event description, start time (`/from`), and end time (`/to`).

### Example:
```
event Team meeting /from Friday 2pm /to 4pm
```

### Expected Outcome:
```
**********************************************
Added: [ ][E] Team meeting (from: Friday 2pm to: 4pm)
**********************************************
```

