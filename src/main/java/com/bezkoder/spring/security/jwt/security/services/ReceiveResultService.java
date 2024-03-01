package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.ReceiveResult;
import com.bezkoder.spring.security.jwt.payload.request.ReceiveResultRequest;
import com.bezkoder.spring.security.jwt.repository.ReceiveResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReceiveResultService {
    ReceiveResult infoReceiveResult(Integer id);
    List<ReceiveResult> listReceiveResults(Boolean active);
    Boolean addReceiveResults(ReceiveResultRequest receiveResultRequest);
    Boolean updateReceiveResults(Integer id, ReceiveResultRequest receiveResultRequest);
    Boolean deleteReceiveResults(Integer id);

}
