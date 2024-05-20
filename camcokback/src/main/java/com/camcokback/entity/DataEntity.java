package com.camcokback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataEntity {
    @Id
    private String contentId; //콘텐츠 ID
    private String facltNm; //야영장명
    private String lineIntro; //한줄소개
    private String firstImageUrl; //대표이미지
    private String tourEraCl; //여행시기
    private String animalCmgCl; //애완동물출입
    private String eqpmnLendCl; //캠핑장비대여
    private String manageSttus; //운영상태.관리상태
    private String themaEnvrnCl; //테마환경
    private String fireSensorCo; // 화재감지기 개수
    private String frprvtSandCo; //방화사 개수
    private String frprvtWrppCo; //방화수 개수
    private String extshrCo; //소화기 개수
    private String allar; //전체면적
    @Column(columnDefinition = "TEXT")
    private String intro; //소개
    private String trlerAcmpnyAt; // 개인 트레일러 동반 여부(Y:사용, N:미사용)
    private String operDeCl; //운영일
    private String operPdCl; //운영기간
    private String caravInnerFclty; //카라반 - 내부시설
    private String glampInnerFclty; //글램핑 - 내부시설
    @Column(columnDefinition = "TEXT")
    private String tooltip; //툴팁
    private String siteBottomCl1; //잔디
    private String siteBottomCl2; //파쇄석
    private String siteBottomCl3; //테크
    private String siteBottomCl4; //자갈
    private String siteBottomCl5; //맨흙
    private String siteMg1Co; //사이트 크기1 수량
    private String siteMg2Co; //사이트 크기2 수량
    private String siteMg3Co; //사이트 크기3 수량
    private String siteMg1Vrticl; //사이트 크기1 세로
    private String siteMg2Vrticl; //사이트 크기2 세로
    private String siteMg3Vrticl; //사이트 크기3 세로
    private String siteMg1Width; //사이트 크기1 가로
    private String siteMg2Width; //사이트 크기2 가로
    private String siteMg3Width; //사이트 크기3 가로
    private String sitedStnc; //사이트간 거리
    private String indvdlCaravSiteCo; //주요시설 개인 카라반
    private String sbrsCl; // 부대시설
    private String sbrsEtc; // 부대시설 기타
    private String posblFcltyCl; // 주변이용가능시설
    private String posblFcltyEtc; // 주변이용가능시설 기타
    private String hvofBgnde; // 휴장기간.휴무기간 시작일
    private String caravAcmpnyAt; // 개인 카라반 동반 여부(Y:사용, N:미사용)
    private String hvofEnddle; // 휴장기간.휴무기간 종료일
    @Column(length = 1000)
    private String featureNm; // 특징
    private String induty; // 업종
    private String lctCl; // 입지구분
    private String doNm; // 도
    private String sigunguNm; // 시군구
    private String zipcode; // 우편번호
    private String addr1; // 주소
    private String addr2; // 주소상세
    private String mapX; // 경도(X)
    private String mapY; // 위도(Y)
    private String tel; // 전화
    private String homepage; // 홈페이지
    @Column(columnDefinition = "TEXT")
    private String resveUrl; // 예약 페이지
    private String resveCl; // 예약 구분
    private String gnrlSiteCo; // 주요시설 일반야영장
    private String autoSiteCo; // 주요시설 자동차야영장
    private String glampSiteCo; // 주요시설 글램핑
    private String caravSiteCo; // 주요시설 카라반

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
