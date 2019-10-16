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
package com.arimaulana.crudjson.abstrct;

import com.arimaulana.crudjson.enums.Notif;
import com.arimaulana.crudjson.intrface.AbstractServiceInterface;
import com.arimaulana.crudjson.service.JsonService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;

/**
 *
 * @author ariansyahPC
 */
public abstract class AbstractService implements AbstractServiceInterface {
    
    protected Class domainClass;
    protected String domainName;
    protected String idName = "id";
    protected Gson gson;

    @Override
    public List<Object> list() {
        this.domainName = domainClass.getSimpleName();
        try {
            gson = new Gson();
            List<Object> listData = JsonService.getListObject(domainClass);

            List<Object> resultList = new ArrayList<>();
            for (Object obj : listData) {
                JsonElement jsonElement = gson.toJsonTree(obj);
                Object pojo = gson.fromJson(jsonElement, domainClass);
                resultList.add(pojo);
            }
            return resultList;
        } catch (Exception e) {
            System.out.println("ERROR di AbstractService > list");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> listByObject(String key, Object value) {
        this.domainName = domainClass.getSimpleName();
        try {
            gson = new Gson();
            List<Object> listData = JsonService.getListObject(domainClass, key, value);

            List<Object> resultList = new ArrayList<>();
            for (Object obj : listData) {
                JsonElement jsonElement = gson.toJsonTree(obj);
                Object pojo = gson.fromJson(jsonElement, domainClass);
                resultList.add(pojo);
            }
            return resultList;
        } catch (Exception e) {
            System.out.println("ERROR di AbstractService > listByObject");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> listByObject(String[] keys, Object value) {
        this.domainName = domainClass.getSimpleName();
        try {
            gson = new Gson();
            List<Object> listData = JsonService.getListObject(domainClass, keys, value);

            List<Object> resultList = new ArrayList<>();
            for (Object obj : listData) {
                JsonElement jsonElement = gson.toJsonTree(obj);
                Object pojo = gson.fromJson(jsonElement, domainClass);
                resultList.add(pojo);
            }
            return resultList;
        } catch (Exception e) {
            System.out.println("ERROR di AbstractService > listByObject");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object get(Long id) {
        this.domainName = domainClass.getSimpleName();
        try {
            gson = new Gson();
            Object obj = JsonService.getObject(domainClass, idName, id);
            JsonElement jsonElement = gson.toJsonTree(obj);
            Object resultObj = gson.fromJson(jsonElement, domainClass);
            return resultObj;
        } catch (Exception e) {
            System.out.println("ERROR di AbstractService > get");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Object get(String key, Object value) {
        this.domainName = domainClass.getSimpleName();
        try {
            gson = new Gson();
            Object obj = JsonService.getObject(domainClass, key, value);
            JsonElement jsonElement = gson.toJsonTree(obj);
            Object resultObj = gson.fromJson(jsonElement, domainClass);
            return resultObj;
        } catch (Exception e) {
            System.out.println("ERROR di AbstractService > get");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Object obj) {
        try {
            JSONArray listData = JsonService.setObject(domainClass, obj, list(), idName);
            JsonService.writeJSON(domainName, listData);
            System.out.println(Notif.notifSaveSuccess(domainName));
            JOptionPane.showMessageDialog(null, Notif.notifSaveSuccess(domainName));
        } catch (IllegalArgumentException | HeadlessException e) {
            System.out.println("ERROR di AbstractService > save");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object obj) {
        try {
            JSONArray listData = JsonService.setObject(domainClass, obj, list(), idName, "update");
            JsonService.writeJSON(domainName, listData);
            System.out.println(Notif.notifUpdateSuccess(domainName));
            JOptionPane.showMessageDialog(null, Notif.notifUpdateSuccess(domainName));
        } catch (IllegalArgumentException | HeadlessException e) {
            System.out.println("ERROR di AbstractService > update");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            JSONArray listData = JsonService.removeObject(domainClass, list(), idName, id);
            JsonService.writeJSON(domainName, listData);
            System.out.println(Notif.notifDeleteSuccess(domainName));
            JOptionPane.showMessageDialog(null, Notif.notifDeleteSuccess(domainName));
        } catch (IllegalArgumentException | HeadlessException e) {
            System.out.println("ERROR di AbstractService > delete");
            JOptionPane.showMessageDialog(null, Notif.notifError(domainName));
            e.printStackTrace();
        }
    }
}
