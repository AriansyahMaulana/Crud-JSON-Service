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
package com.arimaulana.crudjson.intrface;

import java.util.List;

/**
 *
 * @author ariansyahPC
 */
public interface AbstractServiceInterface {
    public List<Object> list();
    public List<Object> listByObject(String key, Object value);
    public List<Object> listByObject(String[] keys, Object value);
    public Object get(Long id);
    public Object get(String key, Object value);
    public void save(Object obj);
    public void update(Object obj);
    public void delete(Long id);
}
