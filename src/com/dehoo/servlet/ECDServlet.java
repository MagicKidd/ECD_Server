package com.dehoo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dehoo.beans.Movies;
import com.dehoo.dao.MoviesDao;
import com.dehoo.utils.Utils;

@SuppressWarnings("serial")
public class ECDServlet extends HttpServlet {
	MoviesDao moviesDao = new MoviesDao();
	List<Movies> movies;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		String function = request.getParameter("callback");
		System.out.println("function = " + function);

		PrintWriter out = response.getWriter();
		this.movies = this.moviesDao.queryAll();
		JSONArray jsonArray = Utils.moviesToJSON(movies);

		String datas = jsonArray.toString();

		out.print(datas);

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
