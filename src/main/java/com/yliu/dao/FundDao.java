package com.yliu.dao;

import com.yliu.bean.Fund;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundDao extends MongoRepository<Fund,String> {

}
