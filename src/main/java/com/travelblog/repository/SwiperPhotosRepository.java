package com.travelblog.repository;

import com.travelblog.model.SwiperPhoto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SwiperPhotosRepository extends CrudRepository<SwiperPhoto, Integer> {

}
