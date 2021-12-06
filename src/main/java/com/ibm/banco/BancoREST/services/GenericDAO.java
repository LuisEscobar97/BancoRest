package com.ibm.banco.BancoREST.services;

import java.util.Optional;

public interface GenericDAO<E> {

    public Optional<E> buscarPorID(Integer id);
    public E guardar(E entidad);
    public Iterable<E>buscarTodos();
    public void eliminarPorId(Integer id);
}
