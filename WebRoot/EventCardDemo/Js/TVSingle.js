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
        'tVSingleView':"../View/TVSingleView"
    }
});

//主页js
require(['underscore', 
		 'backbone', 
		 'jquery',
		 'eventCardView',
		 'tVSingleView'], 
		function (_, Backbone, $,EventCardView,TVSingleView) {
		    var view = new TVSingleView();
		}
	);
