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
public class Notif {
    
    protected static String notifSaveSuccess;
    protected static String notifSaveGagal;
    protected static String notifUpdateSuccess;
    protected static String notifUpdateGagal;
    protected static String notifDeleteSuccess;
    protected static String notifDeleteGagal;
    protected static String notifError;
    
    public static String notifSaveSuccess(String domainName) {
        return notifSaveSuccess != null ? notifSaveSuccess : "Data " + domainName + " Berhasil Disimpan" ;
    }
    
    public static String notifSaveGagal(String domainName) {
        return notifSaveGagal != null ? notifSaveGagal : "Data " + domainName + " Gagal Disimpan !";
    }
    
    public static String notifUpdateSuccess(String domainName) {
        return notifUpdateSuccess != null ? notifUpdateSuccess : "Data " + domainName + " Berhasil Diupdate";
    }
    
    public static String notifUpdateGagal(String domainName) {
        return notifUpdateGagal != null ? notifUpdateGagal : "Data " + domainName + " Gagal Diupdate !";
    }
    
    public static String notifDeleteSuccess(String domainName) {
        return notifDeleteSuccess != null ? notifDeleteSuccess : "Data " + domainName + " Berhasil Dihapus";
    }
    
    public static String notifDeleteGagal(String domainName) {
        return notifDeleteGagal != null ? notifDeleteGagal : "Data " + domainName + " Gagal Dihapus !";
    }
    
    public static String notifError(String domainName) {
        return notifError != null ? notifError : "Maaf Terjadi Kesalahan !";
    }
    
}
