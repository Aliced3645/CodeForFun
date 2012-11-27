function Lecture(name, teacher){
	this.name = name;
	this.teacher = teacher;
}

Lecture.prototype.display = function(){
	return this.teacher + "is teaching " + this.name;
};

var a = "hello";

function User(name){
	this.name = name;
}
var me = User("caoji");
var you = new me.constructor();

User.prototype.getName = function(){
	return this.name;
};

function User(properties){
	for (var i in properties){
		var p = i;
		
	};
	
};


function sendMessage(msg, obj){
	if(arguments.length == 2){
		if ( typeof msg == "string" || msg.constructor == String)
			obj.handleMsg(msg);
	}
	else 
		alert(msg);
}

setTimeout(function(){
	a = "caoji";
}, 1000);



var obj = {
		yes: function(){
			
		},
		
		no: function(){
			
		},
		
		value : String
};

obj.yes();


///PROTOTPYTE
function Person(name){
	this.name = name;
}

Person.prototype.getName = function(){
	return this.name;
};

function User(name, password){
	this.name = name;
	this.password = password;
}

//////////////////////////////Prototype Inherentince///////////////////
User.prototyte = new Person();
///////////////////////////////////////////////////////////////////////


User.prototype.getPassword = function() {
	return this.password;
};



