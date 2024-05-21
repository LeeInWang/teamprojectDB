package com.camcokback.service;

import com.camcokback.entity.DataEntity;
import com.camcokback.entity.PageRequestDTO;
import com.camcokback.entity.SearchResponseDTO;
import com.camcokback.repository.CamcokRepository;
import com.camcokback.dto.DataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class SearchServiceImpl implements SearchService{

    private  final CamcokRepository camcokRepository;
    @Override
    public SearchResponseDTO<DataEntity> getList(PageRequestDTO pageRequestDTO) {
        log.info("*****  getList..............");

        //페이지만들기, 페이징 처리
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("contentId").descending());


//        Page<Object[]> result = camcokRepository.selectList(pageable);
        Page<DataEntity> result = camcokRepository.selectList(pageable);
//        List<DataEntity> dtoList = result.stream()
//                .map(arr -> (DataEntity) arr[0])
//                .collect(Collectors.toList());
        List<DataEntity> dtoList = result.getContent();
        long totalElements = result.getTotalElements(); // 총 데이터 수 가져오기

        long totalCount = result.getTotalElements();

        // 데이터 넣기
        return SearchResponseDTO.<DataEntity>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .totalEliment(totalElements)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    @Override
    public DataDTO get(String contentId) {
        java.util.Optional<DataEntity> result = camcokRepository.selectOne(contentId);

        DataEntity dataEntity = result.orElseThrow();

        DataDTO dataDTO = entityToDTO(dataEntity);

        return dataDTO;
    }

    private DataDTO entityToDTO(DataEntity dataEntity){

        DataDTO dataDTO = DataDTO.builder()
                .contentId(dataEntity.getContentId())
                .facltNm(dataEntity.getFacltNm())
                .lineIntro(dataEntity.getLineIntro())
                .firstImageUrl(dataEntity.getFirstImageUrl())
                .tourEraCl(dataEntity.getTourEraCl())
                .animalCmgCl(dataEntity.getAnimalCmgCl())
                .eqpmnLendCl(dataEntity.getEqpmnLendCl())
                .manageSttus(dataEntity.getManageSttus())
                .themaEnvrnCl(dataEntity.getThemaEnvrnCl())
                .fireSensorCo(dataEntity.getFireSensorCo())
                .frprvtSandCo(dataEntity.getFrprvtSandCo())
                .frprvtWrppCo(dataEntity.getFrprvtWrppCo())
                .extshrCo(dataEntity.getExtshrCo())
                .allar(dataEntity.getAllar())
                .intro(dataEntity.getIntro())
                .trlerAcmpnyAt(dataEntity.getTrlerAcmpnyAt())
                .operDeCl(dataEntity.getOperDeCl())
                .operPdCl(dataEntity.getOperPdCl())
                .caravInnerFclty(dataEntity.getCaravInnerFclty())
                .glampInnerFclty(dataEntity.getGlampInnerFclty())
                .tooltip(dataEntity.getTooltip())
                .siteBottomCl1(dataEntity.getSiteBottomCl1())
                .siteBottomCl2(dataEntity.getSiteBottomCl2())
                .siteBottomCl3(dataEntity.getSiteBottomCl3())
                .siteBottomCl4(dataEntity.getSiteBottomCl4())
                .siteBottomCl5(dataEntity.getSiteBottomCl5())
                .siteMg1Co(dataEntity.getSiteMg1Co())
                .siteMg2Co(dataEntity.getSiteMg2Co())
                .siteMg3Co(dataEntity.getSiteMg3Co())
                .siteMg1Vrticl(dataEntity.getSiteMg1Vrticl())
                .siteMg2Vrticl(dataEntity.getSiteMg2Vrticl())
                .siteMg3Vrticl(dataEntity.getSiteMg3Vrticl())
                .siteMg1Width(dataEntity.getSiteMg1Width())
                .siteMg2Width(dataEntity.getSiteMg2Width())
                .siteMg3Width(dataEntity.getSiteMg3Width())
                .sitedStnc(dataEntity.getSitedStnc())
                .indvdlCaravSiteCo(dataEntity.getIndvdlCaravSiteCo())
                .sbrsCl(dataEntity.getSbrsCl())
                .sbrsEtc(dataEntity.getSbrsEtc())
                .posblFcltyCl(dataEntity.getPosblFcltyCl())
                .posblFcltyEtc(dataEntity.getPosblFcltyEtc())
                .hvofBgnde(dataEntity.getHvofBgnde())
                .caravAcmpnyAt(dataEntity.getCaravAcmpnyAt())
                .hvofEnddle(dataEntity.getHvofEnddle())
                .featureNm(dataEntity.getFeatureNm())
                .induty(dataEntity.getInduty())
                .lctCl(dataEntity.getLctCl())
                .doNm(dataEntity.getDoNm())
                .sigunguNm(dataEntity.getSigunguNm())
                .zipcode(dataEntity.getZipcode())
                .addr1(dataEntity.getAddr1())
                .addr2(dataEntity.getAddr2())
                .mapX(dataEntity.getMapX())
                .mapY(dataEntity.getMapY())
                .tel(dataEntity.getTel())
                .homepage(dataEntity.getHomepage())
                .resveUrl(dataEntity.getResveUrl())
                .resveCl(dataEntity.getResveCl())
                .gnrlSiteCo(dataEntity.getGnrlSiteCo())
                .autoSiteCo(dataEntity.getAutoSiteCo())
                .glampSiteCo(dataEntity.getGlampSiteCo())
                .caravSiteCo(dataEntity.getCaravSiteCo())
                .build();



        return dataDTO;
    }
}
