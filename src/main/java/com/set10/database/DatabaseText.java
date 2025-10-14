package com.set10.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.set10.core.IDatabase;
import com.set10.core.Rute;
import com.set10.core.Stoppested;
import com.set10.core.Avgang;
import com.set10.core.Billett;
import com.set10.core.Bruker;
import com.set10.core.Datadepot;


/* 
 * Dataformat skamløst stjålet fra .obj 
 * 
*/
public class DatabaseText implements IDatabase{
    final String path = "data\\data.txt";

    public void serialiser(Datadepot datadepot) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        for(int i = 0; i < datadepot.avgangCache.size(); i++){
            Avgang avgang = datadepot.avgangCache.get(i);
            writer.append("a;" + avgang.id + ";" + avgang.ruteID+";" + avgang.stoppestedID+ ";" + avgang.tidspunkt +"\n");
        }

        for(int i = 0; i < datadepot.stoppestedCache.size(); i++){
            Stoppested stopp = datadepot.stoppestedCache.get(i);

            String avganger = ""; 
            for(int j = 0; j < stopp.avganger.size()-1; j++){
                avganger += stopp.avganger.get(j).id +",";
            }
            avganger += stopp.avganger.get(stopp.avganger.size()-1).id;
            writer.append("s;" + stopp.id + ";"+ stopp.navn + ";" + avganger + "\n");
        }

        for(int i = 0; i < datadepot.ruteCache.size(); i++){
            Rute rute = datadepot.ruteCache.get(i);
            String stopp = "";
            if (rute.stopp.size() != 0){
                for(int j = 0; j < rute.stopp.size()-1; j++){
                    stopp += rute.stopp.get(j).id +",";
                }
                stopp += rute.stopp.get(rute.stopp.size()-1).id;
            }else{
                System.err.println(rute +" har ingen stopp!");
            }
            writer.append("r;"+rute.id +";"+stopp+"\n");
        }

        for(int i = 0; i < datadepot.billettCache.size(); i++){
            Billett billett = datadepot.billettCache.get(i);
            writer.append("i;" + billett.id + ";" + billett.type + ";" +billett.startTid + ";" + billett.sluttTid + "\n");
        }

        for(int i = 0; i < datadepot.brukerCache.size(); i++){
            Bruker bruker = datadepot.brukerCache.get(i);
            writer.append("b;" + bruker.id + ";" + bruker.navn+";" + "\n");
        }

        
        writer.close();
    }

    public void deserialiser(Datadepot datadepot) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(path));

        datadepot.stoppestedCache = new ArrayList<>();
        datadepot.ruteCache = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null){
            if(line.startsWith("a;")){
                String[] bits = line.split(";");
                datadepot.avgangCache.add(
                    new Avgang(
                        Integer.parseInt(bits[1]),  // id
                        Integer.parseInt(bits[2]),  // ruteid
                        Integer.parseInt(bits[3]),  // stoppestedid
                        LocalTime.parse(bits[4])    // tidspunkt
                        )
                );
                
            }else if(line.startsWith("s;")){
                String[] bits = line.split(";");

                String[] avgIdxs = bits[3].split(",");
                ArrayList<Avgang> avganger = new ArrayList<>();
                for(String string : avgIdxs){
                    int idx = Integer.parseInt(string);
                    avganger.add(datadepot.avgangCache.get(idx));
                }
                Stoppested stopp = new Stoppested(Integer.parseInt(bits[1]), bits[2]);
                stopp.avganger = avganger;

                datadepot.stoppestedCache.add(stopp);

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

            }else if(line.startsWith("i;")){
                String[] bits = line.split(";");
                Billett billett = new Billett(Billett.Type.valueOf(bits[2]), LocalDateTime.parse(bits[3]));
                datadepot.billettCache.add(billett);

            }else if(line.startsWith("b;")){
                String[] bits = line.split(";");
                Bruker bruker = new Bruker(Integer.parseInt(bits[1]),bits[2]);

                datadepot.brukerCache.add(bruker);
            }
        }
        reader.close();
    }
}
