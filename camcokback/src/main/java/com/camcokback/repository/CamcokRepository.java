package com.camcokback.repository;

import com.camcokback.entity.DataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CamcokRepository extends JpaRepository<DataEntity, String> {
    //단일 조회
    @Query("select d from DataEntity d where d.contentId = :contentId")
    Optional<DataEntity> selectOne(@Param("contentId") String contentId);

    //리스트 조회
    @Query("select d from DataEntity d")
//    Page<Object[]> selectList(Pageable pageable);
    Page<DataEntity> selectList(Pageable pageable);
}
