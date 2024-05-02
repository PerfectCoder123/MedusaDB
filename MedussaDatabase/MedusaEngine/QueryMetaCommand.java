package MedussaDatabase.MedusaEngine;

import java.util.List;
import java.util.Properties;

public class QueryMetaCommand {
    
    // i will not validate any logic here 
    // i get valid existing path i just return meta information about table or database
    private String[] show( String path){
        String[] records;
        Properties recordProperty = PropertyBuilder.buildProperty(path);
        records = new String[recordProperty.size()];
        for(int i =0 ; i< recordProperty.size();i++)
        records[i] = recordProperty.getProperty(i+"");
        return records;
    }

     // i will get a path  i just return records
    private String[] desc(String path){
     String[] entity;
     Properties description = PropertyBuilder.buildProperty(path);
     entity = new String[description.size()];
     for(int i = 0 ; i< description.size();i++) entity[i] = description.getProperty(i+"");
     return entity;
    }

    // i will get a dbnamepath load the db from config and set the path to query parameter
    private void use(String dbName){
     QueryParameter.currentDb = dbName;
    }
}
