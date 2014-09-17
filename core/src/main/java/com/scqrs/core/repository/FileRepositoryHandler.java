package com.scqrs.core.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.scqrs.core.aggregate.Aggregate;

public class FileRepositoryHandler extends AbstractRepositoryHandler implements RepositoryHandler {

    protected String seperator = System.getProperty("file.separator");
    protected String location = "./events";
    protected String suffix = "event";
    
    @Override
    public void eventStore(AggregateEvent event) {
        System.out.println("Event store");
        fileGenerator(event);
    }

    
    @Override
    public Aggregate load(Object aggregateId) {
        return null;
    }
    
    protected void fileGenerator(AggregateEvent event) {
        FileWriter fw = null;
        PrintWriter pw = null;
        String genPath = location + seperator + event.getAggregate().getClass().getSimpleName() + seperator;
        Gson gson = new Gson();
        String eventContent = gson.toJson(event);
        try {
            File bulidFile = new File(genPath);
            if (!bulidFile.exists()) {
                bulidFile.mkdirs();
            }
            String fileName = event.getUniqueId() + "." + suffix;
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
