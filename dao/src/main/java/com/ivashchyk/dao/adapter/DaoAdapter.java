package com.ivashchyk.dao.adapter;

import com.ivashchyk.dao.mapper.Mapper;

public interface DaoAdapter<M extends Mapper> {

    M getMapper();

}
