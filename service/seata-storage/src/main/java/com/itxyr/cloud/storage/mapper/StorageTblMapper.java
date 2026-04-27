package com.itxyr.cloud.storage.mapper;

import com.itxyr.cloud.storage.bean.StorageTbl;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageTblMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StorageTbl record);

    int insertSelective(StorageTbl record);

    StorageTbl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StorageTbl record);

    int updateByPrimaryKey(StorageTbl record);

    void deduct(String commodityCode, int count);
}