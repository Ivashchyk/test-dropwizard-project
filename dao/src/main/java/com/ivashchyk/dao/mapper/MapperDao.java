package com.ivashchyk.dao.mapper;

import com.ivashchyk.dao.adapter.DaoAdapter;

public abstract class MapperDao<M extends Mapper> {

    private final DaoAdapter<M> daoAdapter;

    protected MapperDao(DaoAdapter<M> daoAdapter) {
        this.daoAdapter = daoAdapter;
    }

    public M getMapper() {
        return daoAdapter.getMapper();
    }

}
