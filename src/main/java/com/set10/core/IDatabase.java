package com.set10.core;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.set10.core.Datadepot;

public interface IDatabase {
    public void serialiser(Datadepot datadepot) throws IOException;
    public void deserialiser(Datadepot datadepot) throws FileNotFoundException, IOException;
}
