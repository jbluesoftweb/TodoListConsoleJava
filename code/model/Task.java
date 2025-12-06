package model;

public class Task {

private String name;

public Task(String name) {
    this.name = name;
}
public Task(){}

// Getter and Setter
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}

// toString method
@Override
public String toString() {
    return "Task{" +
            "name='" + name + '\'' +
            '}';
}


}
