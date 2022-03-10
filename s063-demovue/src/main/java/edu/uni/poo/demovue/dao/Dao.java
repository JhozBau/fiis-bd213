package edu.uni.poo.demovue.dao;

import java.util.List;

public interface Dao<T> {
    List<T> obtenerTodos();
    void agregar (T t);    
    void actualizar(T t);
    void eliminar(T t);
}
