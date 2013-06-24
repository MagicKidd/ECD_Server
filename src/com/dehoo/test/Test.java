package com.dehoo.test;

import com.dehoo.utils.DBUtils;

/**
 * 测试类，测试各个模块的可行性
 * @author dehoo­HuangDong 2013-6-18下午2:04:12
 * @version jdk 1.6 ;
 */
public class Test {
	private static DBUtils dbUtils;

	public static void main(String[] args) {
		insert_test();
	}
	
	public static void insert_test(){
		dbUtils = new DBUtils();
	}
}
