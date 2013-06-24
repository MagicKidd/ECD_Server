package com.dehoo.beans;

/**
 * Countries entity. @author MyEclipse Persistence Tools
 */

public class Countries implements java.io.Serializable {

	// Fields

	private Integer id;
	private Movies movies;
	private String name;

	// Constructors

	/** default constructor */
	public Countries() {
	}

	/** full constructor */
	public Countries(Movies movies, String name) {
		this.movies = movies;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}