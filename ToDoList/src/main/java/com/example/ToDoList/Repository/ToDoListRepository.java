package com.example.ToDoList.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ToDoList.Entity.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

    
    

}
    


    
