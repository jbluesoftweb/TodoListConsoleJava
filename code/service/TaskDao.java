package service;
import java.util.List;
import java.util.ArrayList;
import model.Task;

public class TaskDao {

 private List<Task> tasks = new ArrayList<>();

 public List<Task> list(){
        return tasks; 
 }

 public void add(Task task){
        tasks.add(task); 
 }

 public void update(int index, Task task){
            tasks.set(index, task); 
 }

public void delete(int index){
            tasks.remove(index); 
}

public Task findById(int index){
            return tasks.get(index); 
}

}