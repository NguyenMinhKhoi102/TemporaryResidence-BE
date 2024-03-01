package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.ReceiveResult;
import com.bezkoder.spring.security.jwt.payload.request.ReceiveResultRequest;
import com.bezkoder.spring.security.jwt.repository.ReceiveResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiveResultServiceImpl implements ReceiveResultService {
    @Autowired
    ReceiveResultsRepository receiveResultsRepository;

    @Override
    public ReceiveResult infoReceiveResult(Integer id){
        return receiveResultsRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
    }
    @Override
    public List<ReceiveResult> listReceiveResults(Boolean active){
        return active ?
                receiveResultsRepository.findAllByIsDeleteAndIsActive(0, 1) :
                receiveResultsRepository.findAllByIsDelete(0);
    }
    @Override
    public Boolean addReceiveResults(ReceiveResultRequest receiveResultRequest){
        try {
            ReceiveResult receiveResult = ReceiveResult.builder()
                    .name(receiveResultRequest.getName())
                    .isDelete(0)
                    .isActive(1)
                    .build();
            receiveResultsRepository.save(receiveResult);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean updateReceiveResults(Integer id, ReceiveResultRequest receiveResultRequest){
        try {
            ReceiveResult receiveResult = receiveResultsRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            receiveResult.setName(receiveResultRequest.getName() == null ? receiveResult.getName() : receiveResultRequest.getName());
            receiveResult.setIsActive(receiveResultRequest.getIsActive() == null ? receiveResult.getIsActive() : receiveResultRequest.getIsActive());
            receiveResultsRepository.save(receiveResult);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @Override
    public Boolean deleteReceiveResults(Integer id){
        try {
            ReceiveResult receiveResult = receiveResultsRepository.findByIdAndIsDelete(id, 0).orElseThrow(()->new RuntimeException("Không tìm thấy"));
            receiveResult.setIsDelete(1);
            receiveResultsRepository.save(receiveResult);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
