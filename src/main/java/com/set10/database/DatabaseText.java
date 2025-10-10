package com.set10.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.set10.core.IDatabase;
import com.set10.core.Rute;
import com.set10.core.Stoppested;
import com.set10.core.Datadepot;


public class DatabaseText implements IDatabase{
    final String path = "data\\data.txt";

    public void serialiser(Datadepot datadepot) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for(int i = 0; i < datadepot.stoppestedCache.size(); i++){
            Stoppested s = datadepot.stoppestedCache.get(i);
            writer.append("s;"+s.stoppID +";"+ s.navn+"\n");
        }
        for(int i = 0; i < datadepot.ruteCache.size(); i++){
            Rute r = datadepot.ruteCache.get(i);
            String stopp = "";
            for(int j = 0; j < r.stopp.size()-1; j++){
                stopp += r.stopp.get(j).stoppID +",";
            }
            stopp += r.stopp.get(r.stopp.size()-1).stoppID;
            writer.append("r;"+r.ruteID +";"+stopp+"\n");
        }
        writer.close();
    }

    public void deserialiser(Datadepot datadepot) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(path));

        datadepot.stoppestedCache = new ArrayList<>();
        datadepot.ruteCache = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null){
            if(line.startsWith("s;")){
                String[] bits = line.split(";");
                datadepot.stoppestedCache.add(
                    new Stoppested(Integer.parseInt(bits[1]),bits[2])
                );
            }else if(line.startsWith("r;")){
                String[] bits = line.split(";");

                String[] stoppidxs = bits[2].split(",");
                ArrayList<Stoppested> stopp = new ArrayList<>();
                for(String string : stoppidxs){
                    int idx = Integer.parseInt(string);
                    stopp.add(datadepot.stoppestedCache.get(idx));
                }

                datadepot.ruteCache.add(
                    new Rute(Integer.parseInt(bits[1]),stopp)
                );
            }
        }
    }
}
