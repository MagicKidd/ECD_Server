define(['underscore', 
		'backbone', 
		'jquery',
		'eventCardView'], 
		function(_, Backbone, $, EventCardView) {
			
		var TvView = Backbone.View.extend({
		
		el : $("body"),
		length:0,
		index:0,
		tvList:[],
		arr:[],
		
		events : {
			"keydown" : "keyDown",
		},
		
		initialize : function() {
			if(window.screen.width==1920){
				this.$el.parent().addClass("size_1920_1080");
			}else{
				this.$el.parent().addClass("size_1280_720");
			}
			$.ajax({
				  url: "http://192.168.1.109:8080/ECD_Server/servlet/ECDServlet"
				}).success(this.proxy( this.handle ));
		},
		
		proxy : function(func) {
			var self = this;
			return (function() {
				return func.apply(self, arguments);
			});
		},
		
		handle : function(data){
			this.tvList = eval(data);
			this.length = this.tvList.length;
			this.render();
			this.BubbleSort(this.arr);
			this.showShadow(this.arr[this.index]);
		},
		
		render : function() {
			var str = "#column";
			for(var i = 0;i<this.length;i++){
				str+=i%3;
				var view = new EventCardView.EventCardView({
					model : this.tvList[i]
				});
				$(str).append(view.render().$el.find(".event_card").get(0));
				this.arr.push($(str).find(".event_card").eq(Math.floor(i/3)));
				str = "#column";
			}
		},
		
		keyDown : function(event) {
			var keyCode = event.keyCode;
			switch(keyCode) {
				
				/*下方向键*/
				case 40:
						var card = this.arr[this.index];
						if(card.next().length!=0){
							this.removeShadow(card);
							for(var i=this.index;i<this.length;i++){
								if(card.next().is(this.arr[i])){
									this.index = i;
									this.showShadow(this.arr[this.index]);
									var distance = this.arr[this.index].offset().top-100;
									if(distance>0){
										$("body").animate({scrollTop:distance},100);	
									}
									break;
								}	
							}
						}
						break;
				/*上方向键*/
				case 38:
						var card = this.arr[this.index];
						if(card.prev().length!=0){
							this.removeShadow(card);
							for(var i=this.index;i>=0;i--){
								if(card.prev().is(this.arr[i])){
									this.index = i;
									this.showShadow(this.arr[this.index]);
									var distance = this.arr[this.index].offset().top-100;
									if(distance>0){
										$("body").animate({scrollTop:distance},100);	
									}
									else{
										$("body").animate({scrollTop:this.arr[this.index].offset().top},100);
									}
									break;
								}	
							}
						}
						//后面有可能要重新刷新页面拿数据
						//等有拿数据的接口后直接重新刷新页面即可
						break;
				/*右方向键*/
				case 39:
						if(this.index+1<this.length){
							this.removeShadow(this.arr[this.index]);
							this.index++;
							var distance = this.arr[this.index].offset().top;
							if(distance>100){
								$("body").animate({scrollTop:distance-100},100);	
							}
							else if(50<distance&&distance<100){
								$("body").animate({scrollTop:distance},100);
							}
							
							this.showShadow(this.arr[this.index]);
						}
						break;
				/*左方向键*/
				case 37:
						if(this.index!=0){
							this.removeShadow(this.arr[this.index]);
							this.index--;
							var distance = this.arr[this.index].offset().top-100;
							if(distance>0){
								$("body").animate({scrollTop:distance},100);	
							}
							else{
								$("body").animate({scrollTop:this.arr[this.index].offset().top},100);
							}
							this.showShadow(this.arr[this.index]);
						}
						break;
				/*PC上的L键,用来模拟浏览器上的切换键*/
				case 76:
					window.location.pathname = "/ECD_Server/EventCardDemo/TV/TVMain.html";
						break;
						
				default:break;
			}
		},
		BubbleSort:function(array) {  
        	var needNextPass = true;  
	        for (var k = 1; k < array.length && needNextPass; k++) {  
	            needNextPass = false;  
	            for (var i = 0; i < array.length - k; i++) {  
	                if (array[i].offset().top > array[i + 1].offset().top) {  
	                    var temp = array[i];  
	                    array[i] = array[i + 1];  
	                    array[i + 1] = temp;  
	                    needNextPass = true;  
	                }  
	            }  
	        }
	        return array;  
    	},
    	showShadow:function(card){
			card.find(".blur").removeClass("blur");
			card.find(".removeShadow").removeClass("removeShadow");
			//这两行代码有可能需要修改，暂定
			card.find("#left").height(card.find("#img_content").height());
			card.find("#right").height(card.find("#img_content").height());
		},
		removeShadow:function(card){
			card.find("#img_content").addClass("blur");
			card.find("#left").addClass("removeShadow");
			card.find("#right").addClass("removeShadow");
			card.find("#top").addClass("removeShadow");
			card.find("#bottom").addClass("removeShadow");
		}
	});
	return TvView;
});

