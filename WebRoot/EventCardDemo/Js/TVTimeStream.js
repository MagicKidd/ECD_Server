//调用非模块化js
require.config({
    shim: {
        'underscore': {
            exports: '_'
        },
        'backbone': {
            deps: ['underscore'],
            exports: 'Backbone'
        }
    },
    paths: {
        "underscore": "Lib/underscore",
        "backbone": "Lib/backbone",
        "jquery": "Lib/jquery",
        'eventCardView':"../View/EventCardView",
        'tvTimeStreamView': "../View/TVTimeStreamView"
    }
});

//主页js
require(['underscore', 
		 'backbone', 
		 'jquery',
		 'eventCardView',
		 'tvTimeStreamView'], 
		function (_, Backbone,$,EventCardView,TVTimeStreamView) {
		    var tvTimeStreamView = new TVTimeStreamView();;
		}
	);
