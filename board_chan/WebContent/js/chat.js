/**
 *	ChatService Module
 */
const chatService = (function() {

	function list(sender, receiver, callback, cp) {
		var chat = {sender : sender, receiver : receiver};
		$.getJSON(cp + "/chats", chat, function(data) {
			if(callback) callback(data)
		});
	}
	function get(cno, receiver, callback, cp) {
		var chat = {cno : cno, receiver : receiver};
		$.getJSON(cp + "/chat", chat, function(data) {
			if(callback) callback(data)
		});
	}
	function add(chat, callback, cp) {
		var chat = JSON.stringify(chat);
		$.post(cp + "/chat", chat, function(data) {
			if(callback) callback(data)
		});
	}
	return {
		get:get,
		list:list,
		add:add,
	}
})();