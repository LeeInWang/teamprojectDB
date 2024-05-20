package com.camcokback.repository;

import com.camcokback.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository는 기본적인 CRUD기능을 제공
public interface DataRepository extends JpaRepository<DataEntity, String> {
}
