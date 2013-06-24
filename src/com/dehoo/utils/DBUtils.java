package com.dehoo.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dehoo.beans.Akas;
import com.dehoo.beans.Casts;
import com.dehoo.beans.Countries;
import com.dehoo.beans.Directors;
import com.dehoo.beans.Movies;
import com.dehoo.beans.Types;
import com.dehoo.dao.AkasDao;
import com.dehoo.dao.CastsDao;
import com.dehoo.dao.CountriesDao;
import com.dehoo.dao.DirectorsDao;
import com.dehoo.dao.MoviesDao;
import com.dehoo.dao.TypesDao;

/**
 * 数据库相关的操作类
 * @author dehoo­HuangDong 2013-6-18下午3:28:33
 * @version jdk 1.6 ;
 */
public class DBUtils {
	
	/** 修改这里搜索的字段，运行Test.java即可往数据库中添加数据 */
	private static final String MOVIE_NAME = "富春山居图";
	
	DoubanUtil doubanUtil = new DoubanUtil();
	Integer[] ids = doubanUtil.getIdByMovieName(MOVIE_NAME);
	MoviesDao moviesDao;
	AkasDao akasDao;
	CastsDao castsDao;
	CountriesDao countriesDao;
	DirectorsDao directorsDao;
	TypesDao typesDao;
	JSONObject jsonObject;
	
	public DBUtils() {
		this.init();
	}
	
	public void init() {
		moviesDao = new MoviesDao();
		akasDao = new AkasDao();
		castsDao = new CastsDao();
		countriesDao = new CountriesDao();
		directorsDao = new DirectorsDao();
		typesDao = new TypesDao();
		
		for (Integer id : ids) {
			jsonObject = doubanUtil.getJSONObjectById(id);
			if (this.insertToMovies()) {
				this.insertToAkas(id);
				this.insertToCasts(id);
				this.insertToCountries(id);
				this.insertToDirectors(id);
				this.insertToTypes(id);
			}
		}
	}

	/**
	 * Function: insertToMovies 插入数据到movies表中
	 * @author dehoo­HuangDong 2013-6-20上午10:01:38
	 * @return 数据添加是否成功
	 */
	public boolean insertToMovies() {
		Movies movie = this.getMovieInfo();
		return moviesDao.insert(movie);
	}
	
	/**
	 * Function: insertToAkas 插入数据到akas表中
	 * @author dehoo­HuangDong 2013-6-18下午7:23:49
	 */
	public void insertToAkas(int id) {
		Set<Akas> akas = this.getAkasInfo(id);
		for (Akas akas2 : akas) {
			this.akasDao.insert(akas2);
		}
	}

	/**
	 * Function: insertToCasts 插入数据到casts表中
	 * @author dehoo­HuangDong 2013-6-18下午7:24:31
	 */
	public void insertToCasts(int id) {
		Set<Casts> casts = this.getCastsInfo(id);
		for (Casts casts2 : casts) {
			this.castsDao.insert(casts2);
		}
	}

	/**
	 * Function: insertToCountries 插入数据到countries表中
	 * @author dehoo­HuangDong 2013-6-18下午7:25:16
	 */
	public void insertToCountries(int id) {
		Set<Countries> countries = this.getCountriesInfo(id);
		for (Countries countries2 : countries) {
			this.countriesDao.insert(countries2);
		}
	}

	/**
	 * Function: insertToDirectors 插入数据到directors表中
	 * @author dehoo­HuangDong 2013-6-18下午7:26:02
	 */
	public void insertToDirectors(int id) {
		Set<Directors> directors = this.getDirectorsInfo(id);
		for (Directors directors2 : directors) {
			this.directorsDao.insert(directors2);
		}
	}

	/**
	 * Function: insertToTypes 插入数据到types表中
	 * @author dehoo­HuangDong 2013-6-18下午7:26:41
	 */
	public void insertToTypes(int id) {
		Set<Types> types = this.getTypesInfo(id);
		for (Types types2 : types) {
			this.typesDao.insert(types2);
		}
	}
	
	/**
	 * Function: getMovidId 获取影片在movies数据库中的id
	 * @author dehoo­HuangDong 2013-6-18下午7:19:52
	 * @return int
	 */
	public int getMovidId(int id) {
		Movies movie = this.moviesDao.queryByDoubanId(id);
		return movie.getId();
	}
	
	/**
	 * Function: getMovieInfo 获取影片信息
	 * @author dehoo­HuangDong 2013-6-18下午6:45:35
	 * @return Movies
	 */
	public Movies getMovieInfo() {
		String douban_id = String.valueOf(jsonObject.opt("id"));
		String name = jsonObject.getString("title");
		String year = jsonObject.getString("year");
		String alt = jsonObject.getString("alt");
		String summary = jsonObject.getString("summary");

		JSONObject images = jsonObject.getJSONObject("images");
		String image_url = images.getString("large");
		Date currentDate = new Date();
		String addTime = String.valueOf(currentDate.getTime());

		Movies movie = new Movies(douban_id, name, year, alt, summary,
				image_url, addTime);
		return movie;
	}
	
	/**
	 * Function: getAkasInfo 获取影片别名信息
	 * @author dehoo­HuangDong 2013-6-18下午5:12:04
	 * @return Set<Akas>
	 */
	public Set<Akas> getAkasInfo(int id) {
		JSONArray akasArray = jsonObject.getJSONArray("aka");
		Set<Akas> akas = new HashSet<Akas>();
		JSONObject object = null;
		Movies movie = new Movies();
		movie.setId(this.getMovidId(id));
		for (int i = 0; i < akasArray.length(); i++) {
//			object = akasArray.getJSONObject(i);
//			String aka_name = object.toString();
			String aka_name = akasArray.getString(i);
			Akas aka = new Akas(movie, aka_name);
			akas.add(aka);
		}
		return akas;
	}
	
	/**
	 * Function: getCastsInfo 获取影片主演信息
	 * @author dehoo­HuangDong 2013-6-18下午5:12:27
	 * @return Set<Casts>
	 */
	public Set<Casts> getCastsInfo(int id) {
		JSONArray castsArray = jsonObject.getJSONArray("casts");
		Set<Casts> casts = new HashSet<Casts>();
		JSONObject object = null;
		Movies movie = new Movies();
		movie.setId(this.getMovidId(id));
		for (int i = 0; i < castsArray.length(); i++) {
			object = castsArray.getJSONObject(i);
			String douban_id = String.valueOf(object.opt("id"));
			String cast_name = object.getString("name");
			Casts cast = new Casts(movie, douban_id, cast_name);
			casts.add(cast);
		}
		return casts;
	}
	
	/**
	 * Function: getCountriesInfo 获取影片产地信息
	 * @author dehoo­HuangDong 2013-6-18下午5:15:09
	 * @return Set<Countries>
	 */
	public Set<Countries> getCountriesInfo(int id) {
		JSONArray conutriesArray = jsonObject.getJSONArray("countries");
		Set<Countries> conutries = new HashSet<Countries>();
		JSONObject object = null;
		Movies movie = new Movies();
		movie.setId(this.getMovidId(id));
		for (int i = 0; i < conutriesArray.length(); i++) {
//			object = conutriesArray.getJSONObject(i);
//			String country_name = object.toString();
			String country_name = conutriesArray.getString(i);
			Countries country = new Countries(movie, country_name);
			conutries.add(country);
		}
		return conutries;
	}
	
	/**
	 * Function: getTypesInfo 获取影片的类型信息
	 * @author dehoo­HuangDong 2013-6-18下午5:17:13
	 * @return Set<Types>
	 */
	public Set<Types> getTypesInfo(int id) {
		JSONArray typesArray = jsonObject.getJSONArray("genres");
		Set<Types> types = new HashSet<Types>();
		JSONObject object = null;
		Movies movie = new Movies();
		movie.setId(this.getMovidId(id));
		for (int i = 0; i < typesArray.length(); i++) {
//			object = typesArray.getJSONObject(i);
//			String type_name = object.toString();
			String type_name = typesArray.getString(i);
			Types type = new Types(movie, type_name);
			types.add(type);
		}
		return types;
	}

	/**
	 * Function: getDirectorsInfo 获取影片导演信息
	 * @author dehoo­HuangDong 2013-6-18下午5:21:19
	 * @return Set<Directors>
	 */
	public Set<Directors> getDirectorsInfo(int id) {
		JSONArray directorsArray = jsonObject.getJSONArray("directors");
		Set<Directors> directors = new HashSet<Directors>();
		JSONObject object = null;
		Movies movie = new Movies();
		movie.setId(this.getMovidId(id));
		for (int i = 0; i < directorsArray.length(); i++) {
			object = directorsArray.getJSONObject(i);
			String douban_id = String.valueOf(object.opt("id"));
			String name = object.getString("name");
			Directors director = new Directors(movie, douban_id, name);
			directors.add(director);
		}
		return directors;
	}

}
