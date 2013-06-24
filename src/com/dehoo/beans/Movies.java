package com.dehoo.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * Movies entity. @author MyEclipse Persistence Tools
 */

public class Movies implements java.io.Serializable {

	// Fields

	private Integer id;
	private String doubanId;
	private String name;
	private String year;
	private String alt;
	private String summary;
	private String images;
	private String preVideos;
	private String addTime;
	private Set akases = new HashSet(0);
	private Set castses = new HashSet(0);
	private Set directorses = new HashSet(0);
	private Set countrieses = new HashSet(0);
	private Set typeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Movies() {
	}
	
	/** minimal constructor */
	public Movies(String doubanId, String name, String year, String alt,
			String summary, String images, String addTime) {
		this.doubanId = doubanId;
		this.name = name;
		this.year = year;
		this.alt = alt;
		this.summary = summary;
		this.images = images;
		this.addTime = addTime;
	}

	/** full constructor */
	public Movies(String doubanId, String name, String year, String alt,
			String summary, String images, String preVideos, String addTime,
			Set akases, Set castses, Set directorses, Set countrieses,
			Set typeses) {
		this.doubanId = doubanId;
		this.name = name;
		this.year = year;
		this.alt = alt;
		this.summary = summary;
		this.images = images;
		this.preVideos = preVideos;
		this.addTime = addTime;
		this.akases = akases;
		this.castses = castses;
		this.directorses = directorses;
		this.countrieses = countrieses;
		this.typeses = typeses;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAlt() {
		return this.alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPreVideos() {
		return this.preVideos;
	}

	public void setPreVideos(String preVideos) {
		this.preVideos = preVideos;
	}

	public String getAddTime() {
		return this.addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Set getAkases() {
		return this.akases;
	}

	public void setAkases(Set akases) {
		this.akases = akases;
	}

	public Set getCastses() {
		return this.castses;
	}

	public void setCastses(Set castses) {
		this.castses = castses;
	}

	public Set getDirectorses() {
		return this.directorses;
	}

	public void setDirectorses(Set directorses) {
		this.directorses = directorses;
	}

	public Set getCountrieses() {
		return this.countrieses;
	}

	public void setCountrieses(Set countrieses) {
		this.countrieses = countrieses;
	}

	public Set getTypeses() {
		return this.typeses;
	}

	public void setTypeses(Set typeses) {
		this.typeses = typeses;
	}

}