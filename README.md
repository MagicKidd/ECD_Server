ECD_Server
==========

ECD后台服务端，从豆瓣获取数据


数据库结构：

  			
				
Directors(导演信息表)				
名称	      类型	        约束条件	    说明	
id	        int	          无重复	      导演识别，主键，自增长	
movie_id	  int	          不为空	      外键，与Movies.id关联	
douban_id	  varchar(10)		              豆瓣的导演id值	
name	      varchar(16)	  不为空	      导演姓名	

				
Casts(主演信息表)				
名称	      类型	        约束条件	    说明	
id	        int	          无重复	      演员识别，主键，自增长	
movie_id	  int	          不为空	      外键，与Movies.id关联	
douban_id	  varchar(10)		              豆瓣的演员id值	
name	      varchar(16)	  不为空	      演员姓名	


Movies(影片信息)				
名称	      类型	        约束条件	    说明	
id	        int	          无重复	      影片识别，主键，自增长	
douban_id	  varchar(10)	  唯一，不为空	豆瓣的影片id值	
name	      varchar(30)	  不为空	      影片名	
year	      varchar(4)		              影片上映时间(年)	
alt	        varchar(100)		            影片链接地址	
summary	    text		                    影片的内容简介	
images	    varchar(100)		            影片的剧照的地址	
pre_videos	varchar(10)		              影片预告片的地址	
add_time	  varchar(13)	  不为空	      影片添加入数据库的时间（毫秒）	


Countries(影片产地信息)				
名称	      类型	        约束条件	    说明	
id	        int	          无重复	      主键，自增长	
movie_id	  int	          不为空	      外键，与Movies.id关联	
name	      varchar(8)	  不为空	      产地的名称（国家）	


Types(影片类型信息)				
名称	      类型	        约束条件	    说明	
id	        int	          无重复	      主键，自增长	
movie_id	  int	          不为空	      外键，与Movies.id关联	
name	      varchar(4)	  不为空	      影片类型	


Akas(影片别名信息)				
名称	      类型	        约束条件	    说明	
id	        int         	无重复	      主键，自增长	
movie_id	  int	不为空	                外键，与Movies.id关联	
name	      varchar(30)	  不为空	      影片别名	 
