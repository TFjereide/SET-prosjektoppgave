package com.set10.core;

public interface IDatabase {
    public void serialize(DataRepository dataRepository) throws Exception;
    public void deserialize(DataRepository dataRepository) throws Exception;
}
