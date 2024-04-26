package com.example.ToDoList.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.ToDoList.Entity.ToDoList;
import com.example.ToDoList.Repository.ToDoListRepository;

@Service
public class ToDoService {
    @Autowired
    public ToDoListRepository objToDoListRepository;

    public List<ToDoList> findAll() {
        return this.objToDoListRepository.findAll();
    }

    public Page<ToDoList> findAllPaginate(int page, int size) {
        /* Validar que la página no sea menor a 0 */
        if (page < 0) {
            page = 0;
        }

        /* Crear la paginación */
        Pageable objPage = PageRequest.of(page, size);

        return this.objToDoListRepository.findAll(objPage);
    }

    /*
     * Método para crear un nuevo coder, se hace uso del repositorio y
     * del método save, el cual se encarga de insertar en la base de datos
     */
    public ToDoList create(ToDoList objToDoList) {
        return this.objToDoListRepository.save(objToDoList);
    }

    /**
     * 
     * Método para eliminar un coder (deleteById)
     */
    public void delete(Long id) {
        // Llamar al repositorio
        this.objToDoListRepository.deleteById(id);
    }

    /**
     * Método para obtener por Id
     */
    public ToDoList findById(Long id) {
        /* Busca un coder por ID y encaso de no ser encontrado devuelve un null */
        return this.objToDoListRepository.findById(id).orElse(null);
    }

    /**
     * Método para actulizar un coder
     */
    public ToDoList update(Long id, ToDoList coder) {
        /* 1. Buscar el coder por ID */
        ToDoList objToDoListDB = this.findById(id);

        /*
         * Verificamos que el coder exista
         */
        if (objToDoListDB == null)
            return null;

        /* Actualizar el coder viejo */
        objToDoListDB = coder;
        /**
         * El método save verifica, si el objeto tiene la llave primaria
         * llena entonces actualizar de lo contrario , crea un nuevo registro
         */
        return this.objToDoListRepository.save(objToDoListDB);
    }


}
