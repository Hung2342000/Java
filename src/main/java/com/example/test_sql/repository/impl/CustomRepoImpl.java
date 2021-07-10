package com.example.test_sql.repository.impl;

import com.example.test_sql.repository.CustomeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepoImpl implements CustomeRepository {
    @Override
    public List<String> test() {
        return null;
    }
}
