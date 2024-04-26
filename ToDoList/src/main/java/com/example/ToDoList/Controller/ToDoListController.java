package com.example.ToDoList.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.ToDoList.Entity.ToDoList;
import com.example.ToDoList.Service.ToDoService;

@Controller
@RequestMapping("/")

public class ToDoListController {

    @Autowired
    private ToDoService objService;

    @GetMapping
    private String showAll(Model model){
        List<ToDoList> listToDo = objService.findAll();
        model.addAttribute("listToDo", listToDo);


    return "viewToDo";

    }

    



}
