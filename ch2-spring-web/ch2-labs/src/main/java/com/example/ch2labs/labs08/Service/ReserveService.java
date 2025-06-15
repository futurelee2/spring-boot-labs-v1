package com.example.ch2labs.labs08.Service;

import com.example.ch2labs.labs08.domain.Reserve;
import com.example.ch2labs.labs08.dto.CreateResponse;
import com.example.ch2labs.labs08.dto.ReserveResponse;
import com.example.ch2labs.labs08.repository.ReserveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReserveService {

    private final ReserveRepository repository;

    public ReserveResponse create(CreateResponse response) {
        Reserve reserve = new Reserve(response.getUserId(), response.getStartTime(), response.getEndTime());
        Reserve saved = repository.save(reserve);
        return toResponse(saved);
    }
    private ReserveResponse toResponse(Reserve request) {
        return new ReserveResponse(request.getUserId(), request.getStartTime(), request.getEndTime());

    }
}
