package com.camcokback.response;

import com.camcokback.entity.DataEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Jackson 라이브러리에 의해 JSON으로 직렬화되거나 역직렬화될 때, 알려지지 않은 속성을 무시하도록 지정
@JsonIgnoreProperties(ignoreUnknown = true)
//외부 API의 응답
public class DataResponse {
    // DataResponse 클래스 내부에 Response 객체를 포합한다. 이 객체는 외부 API응답의 메타 데이터를 나타낸다.
    private Response response;
    // response 필드의 getter
    public Response getResponse() {
        return response;
    }
    // response 필드의 setter
    public void setResponse(Response response) {
        this.response = response;
    }

    //DataResponse 클래스 내부에 정적 내부 클래스인 Response를 정의, 외부 API응답의 본문
    public static class Response {
        // Response 객체 내부에 Body 객체를 포함, Body는 응답의 실제 데이터를 나타냄
        private Body body;

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }
    }

    //클래스 내부에 정적 내부 클래스인 Body를 정의, 응답의 본문
    public static class Body {
        // Body 객체 내부에 Items 객체를 포함, Items는 실제 데이터를 담고 있는 항목
        private Items items;

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }

    //Body 클래스 내부에 정적 내부 클래스인 Items를 정의, 실제 데이터를 담고 있는 항목을 나타냄 
    public static class Items {
        //Items 객체 내부에 DataEntity 객체의 리스트를 포함, 실제 데이터가 담겨있음
        private List<DataEntity> item;

        public List<DataEntity> getItem() {
            return item;
        }

        public void setItem(List<DataEntity> item) {
            this.item = item;
        }
    }
}
