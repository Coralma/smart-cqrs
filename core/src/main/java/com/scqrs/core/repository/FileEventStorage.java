package com.scqrs.core.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.scqrs.core.aggregate.Aggregate;

public class FileEventStorage extends AbstractRepositoryHandler implements EventStorage {

    protected String seperator = System.getProperty("file.separator");
    protected String location = "./events";
    protected String suffix = ".event";
    protected Gson gson = new Gson();
    
    @Override
    public void eventStore(AggregateEvent event) {
        System.out.println("Event store");
        fileGenerator(event);
    }

    @Override
//    public Aggregate load(Object aggregateId, Class<?> aggregateClass) {
    public <T extends Aggregate> T load(Object aggregateId, Class<T> aggregateClass) {
        try {
            String path = location + seperator + aggregateClass.getSimpleName() + seperator + aggregateId + suffix;
            File eventFile = new File(path); 
            if(eventFile.exists()) {
                return gson.fromJson(new FileReader(path), aggregateClass);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void fileGenerator(AggregateEvent event) {
        FileWriter fw = null;
        PrintWriter pw = null;
        String genPath = location + seperator + event.getAggregate().getClass().getSimpleName() + seperator;
        String eventContent = gson.toJson(event);
        try {
            File bulidFile = new File(genPath);
            if (!bulidFile.exists()) {
                bulidFile.mkdirs();
            }
            String fileName = event.getUniqueId() + suffix;
            fw = new FileWriter(genPath + fileName);
            pw = new PrintWriter(fw);
            pw.println(eventContent);
            pw.flush();
            System.out.println("Generate " + genPath + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.close();
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
