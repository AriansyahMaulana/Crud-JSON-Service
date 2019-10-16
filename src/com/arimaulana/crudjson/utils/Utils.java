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
package com.arimaulana.crudjson.utils;

import com.arimaulana.crudjson.enums.Config;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;

/**
 *
 * @author ariansyahPC
 */
public class Utils {

    public static Image getImage(final String pathAndFileName) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    public static String encrypData(String data) {
        try {
            TripleDES tdes = new TripleDES(Config.getScretKey());
            String vData = tdes.encrypt(data);
            return vData;
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypData(String data) {
        try {
            TripleDES tdes = new TripleDES(Config.getScretKey());
            String vData = tdes.decrypt(data);
            return vData;
        } catch (Exception e) {
            return null;
        }
    }

    public static Long getAutoIncrement(List<Object> list, String idName) {
        try {

            Long id = 1L;
            if (list.size() > 0) {
                for (Object object : list) {
                    JsonElement dataList = new Gson().toJsonTree(object);
                    id = Long.valueOf(dataList.getAsJsonObject().get(idName).toString()) + 1;
                }
            }

            return id;
        } catch (Exception e) {
            return null;
        }
    }
}
