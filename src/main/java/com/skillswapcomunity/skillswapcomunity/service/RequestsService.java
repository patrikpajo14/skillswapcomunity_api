package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.RequestDto;

import java.util.List;
import java.util.Optional;

public interface RequestsService {
    List<RequestDto> getRequests();
    Optional<RequestDto> getRequest(Long id);
    RequestDto createRequest(Long senderId, Long recipientId);
    RequestDto updateRequest(RequestDto request, Long id);
    void deleteRequest(Long id);
}
