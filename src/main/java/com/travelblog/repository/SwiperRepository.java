package com.travelblog.repository;

import com.travelblog.model.Swiper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwiperRepository extends CrudRepository<Swiper, Integer> {

    Iterable<Swiper> findAll();

}
