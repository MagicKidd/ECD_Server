define(['underscore', 
		'backbone', 
		'jquery', 
		'eventCardView'], 
		function(_, Backbone, $, EventCardView) {
			
		var TvView = Backbone.View.extend({
		
		el : $("body"),
		tvList:[],
		model:undefined,
		container:$("#container"),
		id:undefined,
		
		events : {
			"keydown" : "keyDown",
		},
		
		initialize : function() {
			var urlinfo = window.location.href;                                                               
		    this.id = urlinfo.split("?")[1].split("=")[1];
		    if(window.screen.width==1920){
				this.tvList = testSource;
				this.$el.parent().addClass("size_1920_1080");
			}else{
				this.tvList = testSource1;
				this.$el.parent().addClass("size_1280_720");
			}
		    
		    //以后改成用类似getDataById之类的方法来获取特定id的对象
		    for(var i = 0;i<this.tvList.length;i++){
		    	if(this.tvList[i].id==this.id){
		    		this.model = this.tvList[i];
		    		break;
		    	}
		    }
		    this.render();
		},
		
		proxy : function(func) {
			var self = this;
			return (function() {
				return func.apply(self, arguments);
			});
		},

		render : function() {
				var view = new EventCardView.EventCardView({
					model : this.model
				});
				var card = view.render().$el.find(".event_card");
				card.addClass("center_card");
				this.container.append(card);
				card.find("#left").height(card.find("#img_content").height());
				card.find("#right").height(card.find("#img_content").height());
		},
		keyDown : function(event) {
			var keyCode = event.keyCode;
			event.stopPropagation();
						
			switch(keyCode){
				/*Android里的P键*/
				/*PC里的shift键*/
				case 16:
				case 80:
					/**返回键处理*/
					window.location.href="/ECD_Server/EventCardDemo/TV/TVMain.html" + "?id="+this.id;
					break;
					
				default:
					break;
			}
		}
	});
	return TvView;
});

