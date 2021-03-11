package com.codegym.service;

import com.codegym.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService<T> {
    Iterable<T> findALl();

    Page<T> findALl(Pageable pageable);

    T findById(Long id) throws NotFoundException;

    T save(T t);

    void deleteById(Long id);

    List<T> findProductName(String name);
}
