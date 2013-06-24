define(['underscore', 
		'backbone', 
		'jquery',
		'tvModel', 
		'eventCardView'], 
		function(_, Backbone, $, TVModel, EventCardView) {
			
		var TvView = Backbone.View.extend({
		
		el : $("body"),
		container:$("#container"),
		tvList : [],
		cards:[],
		views:[],
		cardIndex:-1,
		length:-1,
		preIndex:-1,
		interval:1,	
		id:undefined,
		
		events : {
			"keydown" : "keyDown",
		},
		
		initialize : function() {
			var urlinfo = window.location.href;    
		    if(urlinfo.split("?")[1]){
			    this.id = urlinfo.split("?")[1].split("=")[1];
		    }
			if(window.screen.width==1920){
				this.tvList = testSource.slice(0,9);
				this.$el.parent().addClass("size_1920_1080");
			}else{
				this.tvList = testSource1.slice(0,9);
				this.$el.parent().addClass("size_1280_720");
			}
			this.length = this.tvList.length;
			this.render();
			
			if(this.id){
				if(this.$el.hasClass("blurBg")){
					this.$el.removeClass("blurBg");
				}
				for(var i = 0;i<this.length;i++){
					if(this.id==this.tvList[i].id){
						this.showShadow(this.cards[i]);
						this.cardIndex = i;
						break;
					}
				}
			}
		},
		
		proxy : function(func) {
			var self = this;
			return (function() {
				return func.apply(self, arguments);
			});
		},

		render : function() {
			for(var i = 0;i<this.length;i++){
				var view = new EventCardView.EventCardView({
					model : this.tvList[i]
				});
				this.views.push(view);
				this.container.append(view.render().$el.find(".event_card"));
				var card = this.$el.find('.event_card:last');
				this.cards.push(card);
				this.addAnimation(card);
			}
		},
		keyDown : function(event) {
			var keyCode = event.keyCode;
			event.stopPropagation();
							
			switch(keyCode) {
				/*左方向键*/
				case 37:
					if(this.cardIndex!=-1){
						//非页面焦点
						this.removeShadow(this.cards[this.cardIndex]);
						this.cardIndex==0?this.cardIndex = this.length-1:this.cardIndex--;
					}
					else{
						//页面焦点
						//暂时什么都不做
					}
					break;
				
				/*右方向键*/
				case 39:
					if(this.cardIndex!=-1){
						//非页面焦点
						this.removeShadow(this.cards[this.cardIndex]);
						this.cardIndex==this.length-1?this.cardIndex=0:this.cardIndex++;
					}
					else{
						//页面焦点
						//暂时什么都不做
					}			
					break;

				/*上方向键*/
				case 38:
					if(this.cardIndex!=-1){
						//非页面焦点
						this.removeShadow(this.cards[this.cardIndex]);
						if(Math.floor(this.cardIndex/3)==0){
							this.preIndex = this.cardIndex;
							this.cardIndex = -1;
							if(this.$el.hasClass("blurBg")){
								this.$el.removeClass("blurBg");
							}
						}
						else{
							this.cardIndex-=3;
						}
					}
					else{
						//页面焦点
						if(!this.$el.hasClass("blurBg")){
							this.$el.addClass("blurBg");
						}
						var rowNum = Math.ceil(this.length/3)-1;
						if(this.preIndex!=-1){
							var num = this.preIndex%3;
							var index = rowNum*3+num;
							if(index>=this.length){
								if(rowNum==0){
									this.cardIndex = this.preIndex;
								}
								else{
									this.cardIndex = (rowNum-1)*3+num;
								}
							}
							else{
								this.cardIndex = index;
							}
						}
						else{
							this.cardIndex = rowNum*3;
						}
					}
					break;

				/*下方向键*/
				case 40:
					if(this.cardIndex!=-1){
						//非页面焦点
						this.removeShadow(this.cards[this.cardIndex]);
						this.preIndex = this.cardIndex;
						if(Math.floor(this.cardIndex/3)==Math.ceil(this.length/3)-1){
							this.cardIndex = -1;
							if(this.$el.hasClass("blurBg")){
								this.$el.removeClass("blurBg");
							}
						}
						else{
							var next =  this.cardIndex +3;
							if(next<=this.length-1){
								this.cardIndex=next; 
							}else{
								this.cardIndex = -1;
								if(this.$el.hasClass("blurBg")){
									this.$el.removeClass("blurBg");
								}
							}
						}
					}
					else{
						//页面焦点
						if(!this.$el.hasClass("blurBg")){
							this.$el.addClass("blurBg");
						}
						this.cardIndex = this.preIndex!=-1?this.preIndex%3:0;
					}
					break;

				/*Enter键*/
				case 13:
					if(this.cardIndex!=-1){
						//非页面焦点
						window.location.href="/ECD_Server/EventCardDemo/TV/TVSingle.html" + "?id="+this.views[this.cardIndex].model.id;
					}
					break;
				/*PC上的CTRL键,用来模拟浏览器上的切换键*/
				case 17:
					window.location.href= "/ECD_Server/EventCardDemo/TV/TVTimeStream.html";
				default:
					break;
			}
			if(this.cardIndex!=-1){
				this.showShadow(this.cards[this.cardIndex]);	
			}
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
		},
		addAnimation:function(card){
			card[0].style.webkitAnimationName = 'zAnimation';
			card[0].style["-webkit-animation-duration"]=(500+this.interval*100)+"ms";
			this.interval++;
		}
	});
	return TvView;
});

