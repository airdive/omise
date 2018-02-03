package com.linktai.dao;

import com.linktai.pojo.WebContent;
import org.apache.ibatis.annotations.Param;

public interface WebContentMapper {
    int insert(WebContent record);

    int insertSelective(WebContent record);
    
    WebContent selectByName(@Param("name") String name);
    
    Integer updateWebContent(WebContent webContent);
}