/**
 * 
 */
package com.scqrs.core.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.scqrs.core.util.EventUtils;

public class FileEventRepository implements EventRepository {

	protected String seperator = System.getProperty("file.separator");
    protected String location = "./events";
    protected String suffix = ".event";
    protected Gson gson = new Gson();
    
	@Override
	public void save(Object event) {
		Object uniqueId = EventUtils.getUniqueId(event);
		Assert.notNull(uniqueId);
		Assert.notNull(event);
		FileWriter fw = null;
        PrintWriter pw = null;
        String genPath = location + seperator + event.getClass().getSimpleName() + seperator;
        String eventContent = gson.toJson(event);
        try {
            File bulidFile = new File(genPath);
            if (!bulidFile.exists()) {
                bulidFile.mkdirs();
            }
            String fileName = uniqueId + suffix;
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
	
	@Override
	public Object loadEvent(Object uniqueId, Class<?> eventClass) {
		Assert.notNull(uniqueId);
		Assert.notNull(eventClass);
		try {
            String path = location + seperator + eventClass.getSimpleName() + seperator + uniqueId + suffix;
            File eventFile = new File(path); 
            if(eventFile.exists()) {
                return gson.fromJson(new FileReader(path), eventClass);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}
