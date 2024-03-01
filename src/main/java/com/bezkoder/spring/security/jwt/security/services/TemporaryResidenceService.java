package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.payload.request.AcceptedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.AdditionalTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.DeniedTemporaryResidenceRequest;
import com.bezkoder.spring.security.jwt.payload.request.TemporaryResidenceRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface TemporaryResidenceService {
    Boolean acceptedTemporaryResidence(Long id, AcceptedTemporaryResidenceRequest request);
    Boolean additionalTemporaryResidence(Long id, AdditionalTemporaryResidenceRequest request);
    Boolean deniedTemporaryResidence(Long id, DeniedTemporaryResidenceRequest request);
    Boolean registerTemporaryResidence(Long userId, TemporaryResidenceRegistrationRequest rq, Boolean isCopy);
    Boolean acceptedExtension(Long id);
    Boolean deniedExtension(Long id, DeniedTemporaryResidenceRequest request);
    Boolean acceptedDelete(Long id);
    Boolean deniedDelete(Long id, DeniedTemporaryResidenceRequest request);
}
