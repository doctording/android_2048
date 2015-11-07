package com.example.g2048;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedpreferenceUtil {

	/**
	 * 根据target 和 keys得到结果集
	 * @param application
	 * @param fromTarget
	 * @param keys
	 * @return
	 */
	public ArrayList<String> read(Application application,
			String fromTarget, ArrayList<String> keys) {
		
    	SharedPreferences preference = 
    			application.getSharedPreferences(fromTarget, Context.MODE_PRIVATE);
    	ArrayList<String> resList = new ArrayList<String>();
    	for(int i=0;i<keys.size();i++) {
			String resTmp = preference.getString(keys.get(i),"");
			resList.add(resTmp);
		}
    	return resList;
    }
	
	/**
	 * sharedpreference 写入数据
	 * @param application
	 * @param totarget
	 * @param map 
	 */
    public void write(Application application,
    		String totarget,HashMap<String,String> map) {
    	SharedPreferences preference = 
    			application.getSharedPreferences(totarget,Context.MODE_PRIVATE);
    	Editor editor = preference.edit();
    	
    	Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,String> entry = iter.next();
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();
			editor.putString(key,val);
		}
    	editor.commit();
    }
    
}