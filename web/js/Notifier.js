Notifier = Class.create( {
    locale: 'english',
    messages: {
        english: {
            'hello_world': 'Hello World',
            'create_new_event_page_loaded': 'Create New Event page loaded.'
        }, srpski: {
            'hello_world': 'Zdravo Svete',
            'create_new_event_page_loaded': 'Strana za kreiranje novih dogadjaj je ucitana.'
        }
    },
    
    initialize : function(locale) {
        if(locale !== '') {
            this.locale = locale;	
        }
    },
	
    get : function(key) {
        return this.messages[this.locale][key];
    },	
    
    notify : function(key) {
        alert('New Notification: "' + this.get(key) + '"');
    }   
    
} );

Tapestry.Initializer.get = function(json) {
    var notifier = new Notifier(json.locale);
    alert('Fallowing localized message is retrived from javascript for locale \'' + json.locale + '\'');
    alert('Message: ' + notifier.get(json.key));
};

Tapestry.Initializer.notify = function(json) {    
    new Notifier(json.locale).notify(json.key);
};
