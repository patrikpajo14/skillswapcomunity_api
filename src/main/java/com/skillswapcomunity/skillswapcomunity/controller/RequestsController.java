package com.skillswapcomunity.skillswapcomunity.controller;

import com.skillswapcomunity.skillswapcomunity.dto.RequestDto;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.model.Requests;
import com.skillswapcomunity.skillswapcomunity.service.PersonService;
import com.skillswapcomunity.skillswapcomunity.service.RequestsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests")
@AllArgsConstructor
public class RequestsController {

    private RequestsService requestService;
    private PersonService personService;

    @GetMapping("/all")
    public List<RequestDto> getAllRequests() {
        return requestService.getRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getRequestById(@PathVariable long id) {
        Optional<RequestDto> requestOptional = requestService.getRequest(id);
        return requestOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    //http://localhost:8080/requests?senderId=1&recipientId=2
    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestParam Long senderId, @RequestParam Long recipientId) {
        return new ResponseEntity<>(
                requestService.createRequest(senderId, recipientId),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestDto> updateRequest(@RequestBody RequestDto request, @PathVariable Long id)
    {
        try {
            RequestDto updatedRequest = requestService.updateRequest(request, id);
            return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}