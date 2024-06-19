package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.PersonDto;
import com.skillswapcomunity.skillswapcomunity.dto.RequestDto;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.model.Requests;
import com.skillswapcomunity.skillswapcomunity.repository.PersonRepository;
import com.skillswapcomunity.skillswapcomunity.repository.RequestsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RequestsServiceImpl implements RequestsService {

    private final PersonRepository personRepository;
    private RequestsRepository requestsRepository;
    private PersonService personService;

    @Override
    public List<RequestDto> getRequests() {
        return requestsRepository.findAll()
                .stream()
                .map(this::convertRequestToRequestDto)
                .toList();
    }

    @Override
    public Optional<RequestDto> getRequest(Long id) {
        Optional<Requests> requestOptional = requestsRepository.findById(id);
        if(requestOptional.isPresent()) {
            return Optional.of(convertRequestToRequestDto(requestOptional.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public RequestDto createRequest(Long senderId, Long recipientId) {
        Optional<Person> sender = personService.getPersonEntity(senderId);
        Optional<Person> recipient = personService.getPersonEntity(recipientId);
        if (sender.isPresent() && recipient.isPresent()) {
            RequestDto request = new RequestDto();
            request.setSender(sender.get());
            request.setRecipient(recipient.get());
            request.setStatus(10);
            return convertRequestToRequestDto(
                    requestsRepository.save(convertRequestDtoToRequest(request)));
        }
        else {
            throw new EntityNotFoundException("Request not found");
        }
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto, Long id) {
        Optional<Requests> requestOptional = requestsRepository.findById(id);
        if (requestOptional.isPresent()) {
            Requests requestToUpdate = requestOptional.get();
            requestToUpdate.setStatus(requestDto.getStatus());
            return convertRequestToRequestDto(requestsRepository.save(requestToUpdate));
        }
        else {
            throw new EntityNotFoundException("Requests with the ID = '"
                    + id + "' not found");
        }
    }

    @Override
    public void deleteRequest(Long id) {
        requestsRepository.deleteById(id);
    }

    private RequestDto convertRequestToRequestDto(Requests request) {
        return new RequestDto(request.getId(), request.getStatus(), request.getSender(), request.getRecipient());
    }

    private Requests convertRequestDtoToRequest(RequestDto requestDto) {
        Requests request = new Requests();
        request.setStatus(requestDto.getStatus());
        if (requestDto.getSender() != null && requestDto.getSender().getId() != null) {
            Person person = personRepository.findById(requestDto.getSender().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Sender not found"));
            request.setSender(person);
        }

        if (requestDto.getRecipient() != null && requestDto.getRecipient().getId() != null) {
            Person person = personRepository.findById(requestDto.getRecipient().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Recipient not found"));
            request.setRecipient(person);
        }
        return request;
    }
}

