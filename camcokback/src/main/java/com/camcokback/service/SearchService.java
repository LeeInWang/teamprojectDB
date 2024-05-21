package com.camcokback.service;

import com.camcokback.dto.DataDTO;
import com.camcokback.entity.DataEntity;
import com.camcokback.entity.PageRequestDTO;
import com.camcokback.entity.SearchResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SearchService {
    //목록데이터
    SearchResponseDTO<DataEntity> getList(PageRequestDTO pageRequestDTO);

    // 서비스 조회 기능 처리
    DataDTO get(String contentId);

}
