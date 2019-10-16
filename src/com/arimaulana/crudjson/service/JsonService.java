/*
 * Copyright (c) 2017 ARIANSYAH MAULANA
 *
 * Anda diperkenankan mengedit isi dari source code ini
 * asalkan tetap menyertakan copyright ini.
 *
 * File ini dibuat menggunakan :
 * Editor     : NetBeans IDE 8.0.2
 * NoteBook   : ASUS Notebook K42F
 * OS         : Windows 10 Pro 64bit
 * Compiler   : JDK 8 update 18
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 *      http://www.apache.org/licenses/LICENSE-2.0
 */
package com.arimaulana.crudjson.service;

import com.arimaulana.crudjson.abstrct.AbstractService;
import com.arimaulana.crudjson.enums.Config;
import com.arimaulana.crudjson.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ariansyahPC
 */
public class JsonService {
    
    public static void writeJSON(String domainName, JSONArray listJson) {
        try {
            JSONObject table = new JSONObject();
            table.put(domainName, listJson);
            File fileGet = new File(Config.getPathDatabase() + domainName + ".json");
            try (FileWriter file = new FileWriter(fileGet.getAbsolutePath())) {
                file.write(Utils.encrypData(table.toJSONString()));
                file.flush();

            } catch (IOException e) {
                System.out.println("ERROR di JsonService > writeJSON");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > writeJSON");
            e.printStackTrace();
        }
    }

    public static JSONArray readJSON(String jsonFilename, String objKey) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray listData = null;

            try {
                File fileGet = new File(Config.getPathDatabase() + jsonFilename);

                FileReader fileReader = new FileReader(fileGet.getAbsolutePath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuffer = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }

                Object obj = null;
                try {
                    obj = parser.parse(stringBuffer.toString());
                } catch (Exception e) {
                    obj = parser.parse(Utils.decrypData(stringBuffer.toString()));
                }

                JSONObject jsonObject = (JSONObject) obj;
                listData = (JSONArray) jsonObject.get(objKey);

            } catch (FileNotFoundException e) {
                System.out.println("ERROR di JsonService > readJSON");
                e.printStackTrace();
            }
            return listData;
        } catch (IOException | ParseException e) {
            System.out.println("ERROR di JsonService > readJSON");
            e.printStackTrace();
            return null;
        }
    }

    public static List<Object> getListObject(Class clss) {
        try {
            List<Object> listData = new ArrayList<>();
            JSONArray jSONArray = JsonService.readJSON(clss.getSimpleName() + ".json", clss.getSimpleName());
            JSONObject jSONObject = null;
            for (Object jSONArray1 : jSONArray) {
                jSONObject = (JSONObject) jSONArray1;
                Map<String, Object> map = new HashMap<>();

                BeanInfo beanInfo = Introspector.getBeanInfo(clss);
                for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                    String propertyName = propertyDesc.getName();
                    if (!propertyName.equals("class")) {
                        map.put(propertyName, jSONObject.get(propertyName));
                    }
                }
                listData.add(map);
            }
            return listData;
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > getListObject");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Object> getListObject(Class clss, String key, Object value) {
        try {
            List<Object> listData = new ArrayList<>();
            JSONArray jSONArray = JsonService.readJSON(clss.getSimpleName() + ".json", clss.getSimpleName());
            JSONObject jSONObject = null;

            for (Object jSONArray1 : jSONArray) {
                jSONObject = (JSONObject) jSONArray1;

                if (jSONObject.get(key).toString().toLowerCase().contains((String) value)
                        || jSONObject.get(key).toString().toUpperCase().contains((String) value)) {

                    Map<String, Object> map = new HashMap<>();
                    BeanInfo beanInfo = Introspector.getBeanInfo(clss);
                    for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                        String propertyName = propertyDesc.getName();
                        if (!propertyName.equals("class")) {
                            map.put(propertyName, jSONObject.get(propertyName));
                        }
                    }
                    listData.add(map);
                }
            }
            return listData;
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > getListObject");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Object> getListObject(Class clss, String[] key, Object value) {
        try {
            
            List<Object> listData = new ArrayList<>();
            JSONArray jSONArray = JsonService.readJSON(clss.getSimpleName() + ".json", clss.getSimpleName());
            JSONObject jSONObject = null;

            for (Object jSONArray1 : jSONArray) {
                int countFind = 0;
                jSONObject = (JSONObject) jSONArray1;

                for (String keyStr : key) {
                    if (jSONObject.get(keyStr).toString().toLowerCase().contains((String) value)
                            || jSONObject.get(keyStr).toString().toUpperCase().contains((String) value)) {
                        countFind++;
                    }
                }

                if (countFind > 0) {
                    Map<String, Object> map = new HashMap<>();
                    BeanInfo beanInfo = Introspector.getBeanInfo(clss);
                    for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                        String propertyName = propertyDesc.getName();
                        if (!propertyName.equals("class")) {
                            map.put(propertyName, jSONObject.get(propertyName));
                        }
                    }
                    listData.add(map);
                }
            }
            return listData;
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > getListObject");
            e.printStackTrace();
            return null;
        }

    }

    public static Object getObject(Class clss, String idName, Long id) {
        try {
            JSONArray jSONArray = JsonService.readJSON(clss.getSimpleName() + ".json", clss.getSimpleName());
            JSONObject jSONObject = null;
            Object obj = null;

            for (Object jSONArray1 : jSONArray) {
                jSONObject = (JSONObject) jSONArray1;
                if (jSONObject.get(idName).toString().equalsIgnoreCase(String.valueOf(id))) {
                    obj = jSONObject;
                }
            }

            return obj;
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > getObject");
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object getObject(Class clss, String key, Object value) {
        try {
            JSONArray jSONArray = JsonService.readJSON(clss.getSimpleName() + ".json", clss.getSimpleName());
            JSONObject jSONObject = null;
            Object obj = null;

            for (Object jSONArray1 : jSONArray) {
                jSONObject = (JSONObject) jSONArray1;
                if (jSONObject.get(key).toString().equalsIgnoreCase(String.valueOf(value))) {
                    obj = jSONObject;
                }
            }

            return obj;
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > getObject");
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray setObject(Class clss, Object obj, List<Object> listObj, String idName) {
        try {
            JSONArray listData = new JSONArray();

            for (Object object : listObj) {
                JsonElement dataList = new Gson().toJsonTree(object);
                listData.add(dataList);
            }

            BeanInfo beanInfo = null;
            try {
                beanInfo = Introspector.getBeanInfo(clss);
            } catch (IntrospectionException ex) {
                Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
            }

            JSONObject data = new JSONObject();
            for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                String propertyName = propertyDesc.getName();
                if (!propertyName.equals("class")) {
                    Object value = propertyDesc.getReadMethod().invoke(obj);
                    data.put(propertyName, value);
                }

                if (propertyName.equals(idName)) {
                    data.put(propertyName, Utils.getAutoIncrement(listObj, idName));
                }
            }

            listData.add(data);
            return listData;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("ERROR di JsonService > setObject");
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray setObject(Class clss, Object obj, List<Object> listObj, String idName, String type) {
        try {
            Gson gson = new Gson();
            JSONArray listData = new JSONArray();
            JsonElement dataObj = gson.toJsonTree(obj);

            for (Object object : listObj) {
                JsonElement data = gson.toJsonTree(object);

                if (data.getAsJsonObject().get(idName).toString().equals(dataObj.getAsJsonObject().get(idName).toString())) {
                    BeanInfo beanInfo = null;
                    try {
                        beanInfo = Introspector.getBeanInfo(clss);
                    } catch (IntrospectionException ex) {
                        Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                        String propertyName = propertyDesc.getName();
                        if (!propertyName.equals("class")) {
                            data.getAsJsonObject().add(propertyName, dataObj.getAsJsonObject().get(propertyName));
                        }

                    }
                }
                listData.add(data);
            }
            return listData;
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR di JsonService > setObject");
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray removeObject(Class clss, List<Object> listObj, String idName, Long id) {
        try {
            JSONArray listData = new JSONArray();

            for (Object object : listObj) {
                JsonElement data = new Gson().toJsonTree(object);

                if (!data.getAsJsonObject().get(idName).toString().equals(id.toString())) {
                    listData.add(data);
                }

            }
            return listData;
        } catch (Exception e) {
            System.out.println("ERROR di JsonService > removeObject");
            e.printStackTrace();
            return null;
        }
    }
    
}
