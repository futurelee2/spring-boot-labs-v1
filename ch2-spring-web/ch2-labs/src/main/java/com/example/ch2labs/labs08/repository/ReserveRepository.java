package com.example.ch2labs.labs08.repository;

import com.example.ch2labs.labs08.domain.Reserve;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ReserveRepository {
    private final Map<String,Reserve> store = new HashMap<>();

    public Reserve save(Reserve reserve) {
        store.put(reserve.getUserId(), reserve);
        return reserve;
    }
}
