package com.dehoo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dehoo.config.Config;

/**
 * 从豆瓣获取数据
 * @author dehoo­HuangDong 2013-6-18上午11:21:14
 * @version jdk 1.6 ;
 */
public class DoubanUtil {
	
	/**
	 * Function: getIdByMovieName 通过影片的名字找到影片在豆瓣中的id值
	 * @author dehoo­HuangDong 2013-6-18下午2:31:23
	 * @param name 影片名称
	 * @return 影片在豆瓣中的id值的集合
	 */
	public Integer[] getIdByMovieName(String name) {
		final String url = Config.SEAECH_NAME_URL + name;
		JSONObject jsonObject = this.getUrlJSON(url);
		JSONArray array = jsonObject.getJSONArray("subjects");
		JSONObject object = null;
		Integer[] ids = new Integer[array.length()];
		for (int i = 0; i < array.length(); i++) {
			object = array.getJSONObject(i);
			ids[i] = object.getInt("id");
		}
		return ids;
	}
	
	/**
	 * Function: getUrlJSON 根据豆瓣url获取json数据并下载到本地
	 * @author dehoo­HuangDong 2013-6-18下午1:40:48
	 * @param url
	 * @return JSONArray
	 */
	public JSONObject getUrlJSON(String url) {
		InputStream is = null;
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		JSONObject object = null;
		try {
			URL url2 = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) url2
					.openConnection();
			connection.connect();
			is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}
			object = new JSONObject(builder.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	/**
	 * Function: getJSONObjectById 根据影片id获取影片详细信息的json对象
	 * @author dehoo­HuangDong 2013-6-18下午2:45:21
	 * @param id
	 * @return JSONObject
	 */
	public JSONObject getJSONObjectById(int id) {
		final String url = Config.SEARCH_ID_URL + String.valueOf(id);
		JSONObject jsonObject = this.getUrlJSON(url);
		return jsonObject;
	}
	
}
