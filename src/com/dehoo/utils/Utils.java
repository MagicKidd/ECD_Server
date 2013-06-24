package com.dehoo.utils;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dehoo.beans.Movies;

/**
 * 操作类
 * @author dehoo­HuangDong 2013-6-17下午5:09:15
 * @version jdk 1.6;
 */
public class Utils {

	/**
	 * Function: moviesToJSON 查询movies表 并转换成json格式的数据
	 * @author dehoo­HuangDong 2013-6-19上午11:57:20
	 * @param movies
	 * @return JSONArray
	 */
	public static JSONArray moviesToJSON(List<Movies> movies) {
		JSONArray array = new JSONArray();
		try {
			for (Movies movie : movies) {
				JSONObject object = new JSONObject();
				object.put("title", movie.getName());
				object.put("image_url", movie.getImages());
				array.put(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return array;
	}
}
