package com.linktai.dao;

import com.linktai.pojo.WebContent;

public interface WebContentMapper {
    int insert(WebContent record);

    int insertSelective(WebContent record);
    
    WebContent selectByName(String name);
    
    Integer updateWebContent(WebContent webContent);
}