package com.ibm.banco.BancoREST.services;

import com.ibm.banco.BancoREST.entities.Cliente;
import com.ibm.banco.BancoREST.exceptions.NotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class GenericDAOImpl <E,R extends CrudRepository<E,Integer>> implements  GenericDAO<E> {
    protected final R repository;

    public GenericDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public Optional<E> buscarPorID(Integer id) {
        Optional<E> objetoEncontrado=repository.findById(id);
        if (!objetoEncontrado.isPresent())
            throw new NotFoundException(String.format("El registro buscado con ID: %d no existe en la base de datos",id));
        return objetoEncontrado;
    }

    @Override
    public E guardar(E entidad) {
        return repository.save(entidad);
    }

    @Override
    public Iterable<E> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public void eliminarPorId(Integer id) {
        repository.deleteById(id);
    }
}
