package com.set10.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.set10.core.IDatabase;
import com.set10.core.Rute;
import com.set10.core.Stoppested;
import com.set10.core.Datadepot;


public class DatabaseText implements IDatabase{

    public void serialiser(Datadepot datadepot) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("data\\data.txt"));
        for(int i = 0; i < datadepot.stoppestedCache.size(); i++){
            Stoppested s = datadepot.stoppestedCache.get(i);
            writer.append("s;"+s.stoppID +";"+ s.navn+"\n");
        }
        writer.append("ruter:" + datadepot.ruteCache.size()+"\n");
        for(int i = 0; i < datadepot.ruteCache.size(); i++){
            Rute r = datadepot.ruteCache.get(i);
            String stopp = "[";
            for(int j = 0; j < r.stopp.size()-1; j++){
                stopp += r.stopp.get(j).stoppID +",";
            }
            stopp += r.stopp.get(r.stopp.size()-1).stoppID +"]";
            writer.append("r;"+r.ruteID +";"+stopp+"\n");
        }
        writer.close();
    }

    public void deserialiser(Datadepot datadepot){


    }

}
