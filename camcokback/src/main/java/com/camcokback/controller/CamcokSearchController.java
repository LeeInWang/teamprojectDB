package com.camcokback.controller;

import com.camcokback.dto.DataDTO;
import com.camcokback.entity.DataEntity;
import com.camcokback.entity.PageRequestDTO;
import com.camcokback.entity.SearchResponseDTO;
import com.camcokback.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/search")
public class CamcokSearchController {
    private final SearchService searchService;

    @GetMapping(value = "/list", produces = "application/json")
    public SearchResponseDTO<DataEntity> list(PageRequestDTO pageRequestDTO){

        log.info("list.................." + pageRequestDTO);

        return searchService.getList(pageRequestDTO);
    }

    //추가4 -- 조회 기능
    @GetMapping("/{pno}")
    public DataDTO read(@PathVariable(name="contentId") String contentId) {

        return searchService.get(contentId);
    }
}
