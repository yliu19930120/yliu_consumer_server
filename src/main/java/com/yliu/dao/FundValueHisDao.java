package com.yliu.dao;

import com.yliu.bean.FundValueHis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FundValueHisDao extends MongoRepository<FundValueHis,String> {

}
