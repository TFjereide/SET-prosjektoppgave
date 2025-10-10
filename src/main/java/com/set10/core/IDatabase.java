package com.set10.core;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDatabase {
    public void serialiser(Datadepot datadepot) throws Exception;
    public void deserialiser(Datadepot datadepot) throws Exception;
}
