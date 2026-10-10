package com.praveen.taskmanager.repository;

//What does this do? JpaRepository<Task, Long> 
//gives you built-in database operations, including saving, finding, updating, and deleting tasks, without writing SQL manually.
//package com.praveen.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.praveen.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
