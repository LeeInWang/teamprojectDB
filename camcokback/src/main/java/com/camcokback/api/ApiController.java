package com.camcokback.api;

import com.camcokback.entity.DataEntity;
import com.camcokback.response.DataResponse;
import com.camcokback.service.DataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// RESTful 웹 서비스의 컨트롤러
@RestController
@PropertySource("classpath:application.properties")
public class ApiController {
    @Autowired
    private DataService dataService;

    @Autowired
    private Environment env;

    @GetMapping("/fetch-and-save")
    public String fetchAndSaveData() {
        //Rest 요청을 보내기 위한 RestTemplate 객체
        RestTemplate restTemplate = new RestTemplate();

        //HTTP 요청 헤더를 설정하기 위한 HttpHeaders 객체
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String campingUrl = env.getProperty("external.api.url");
        String serviceKey = env.getProperty("external.api.key");

        // 직접 URL 구성
        // API 요청에 사용할 URL을 구성
        String url = campingUrl + "?serviceKey=" + serviceKey
                + "&numOfRows=3826&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

        try {
            // RestTemplate를 사용하여 HTTP GET요청을 보내고, 응답을 ResponseEntity로 받는다.
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String responseBody = responseEntity.getBody();

            //JSON 데이터를 매핑하기 위한 매퍼 생성
            ObjectMapper objectMapper = new ObjectMapper();
            //응답 본문을 JsonNode로 파싱
            JsonNode root = objectMapper.readTree(responseBody);
            //응답에서 response키에 해당하는 값을 가져옴
            JsonNode responseNode = root.path("response");

            //응답이 존재하는지 확인하고, 데이터가 있는 경우와 없는 경우를 처리
            if (responseNode != null) {
                JsonNode bodyNode = responseNode.path("body");
                JsonNode itemsNode = bodyNode.path("items");
                JsonNode itemListNode = itemsNode.path("item");

                List<DataEntity> dataList = new ArrayList<>();
                //응답에서 가져온 JSON데이터를 반복하면서 DataEntity객체로 변환하여 리스트에 추가
                for (JsonNode itemNode : itemListNode) {
                    // itemNode를 DataEntity 객체로 변환하여 dataEntity에 저장
                    DataEntity dataEntity = objectMapper.treeToValue(itemNode, DataEntity.class);
                    // 변환된 DataEntity 객체를 dataList에 추가
                    dataList.add(dataEntity);
                }
                //DataService를 사용하여 데이터를 저장
                dataService.saveData(dataList);
                return "데이터 저장 완료";
            } else {
                return "API 응답에 데이터가 없습니다.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "데이터 저장 실패";
        }
    }

}
