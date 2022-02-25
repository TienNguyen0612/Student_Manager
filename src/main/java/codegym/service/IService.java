package codegym.service;

import java.util.ArrayList;

public interface IService<E> {
    ArrayList<E> findAll();

    E save(E e);

    void delete(int id);

    E findById(int id);
}
