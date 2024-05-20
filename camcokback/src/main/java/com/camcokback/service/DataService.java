package com.camcokback.service;

import com.camcokback.entity.DataEntity;
import com.camcokback.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    //saveData 메서드를 정의 이 메서드는 DataEntity 객체의 리스트를 받아 데이터를 저장
    public void saveData(List<DataEntity> dataList){
        for(DataEntity data : dataList) {
            //  현재 반복되고 있는 contentId
            String contentId = data.getContentId();
            //데이터를 저장하기 위해 dataRepository를 사용 db에 DataEntity객체를 저장
            dataRepository.save(data);
        }
    }
}
