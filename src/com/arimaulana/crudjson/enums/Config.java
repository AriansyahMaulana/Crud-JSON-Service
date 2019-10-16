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
package com.arimaulana.crudjson.enums;

/**
 *
 * @author ariansyahPC
 */
public class Config {
    
    protected static String SECRET_KEY = "0332030405060708090A0B0C0D0E0F";
    protected static String PATH_DATABASE = "database/";
    
    public static String getScretKey() {
        return SECRET_KEY;
    }
    
    public static String getPathDatabase() {
        return PATH_DATABASE;
    }
    
}
