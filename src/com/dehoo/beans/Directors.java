package com.dehoo.beans;

/**
 * Directors entity. @author MyEclipse Persistence Tools
 */

public class Directors implements java.io.Serializable {

	// Fields

	private Integer id;
	private Movies movies;
	private String doubanId;
	private String name;

	// Constructors

	/** default constructor */
	public Directors() {
	}

	/** full constructor */
	public Directors(Movies movies, String doubanId, String name) {
		this.movies = movies;
		this.doubanId = doubanId;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Movies getMovies() {
		return this.movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	public String getDoubanId() {
		return this.doubanId;
	}

	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}