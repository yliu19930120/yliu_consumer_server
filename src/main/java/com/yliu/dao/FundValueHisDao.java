package com.yliu.dao;

import com.yliu.bean.FundValueHis;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FundValueHisDao extends MongoRepository<FundValueHis,String> {

     List<FundValueHis> findAllByCode(String code);
}
