/**
 *	ReplyService Module
 */
const replyService = (function() {

	function list(bno, callback, cp) {
		var reply = {bno : bno};
		$.getJSON(cp + "/replies", reply, function(data) {
			if(callback) callback(data)
		});
	}
	function get(rno, callback, cp) {
		var reply = {rno : rno};
		$.getJSON(cp + "/reply", reply, function(data) {
			if(callback) callback(data)
		});
	}
	function add(reply, callback, cp) {
		var reply = JSON.stringify(reply);
		$.post(cp + "/reply", reply, function(data) {
			if(callback) callback(data)
		});
	}
	function modify(reply, callback, cp) {
		var reply = JSON.stringify(reply);
		$.ajax({
			url : cp + "/reply",
			success : function(data) {
				if(callback) callback(data)
			},
			type : "PUT",
			data : reply
		})
	}
	function remove(reply, callback, cp) {
		var reply = JSON.stringify(reply);
		$.ajax({
			url : cp + "/reply",
			success : function(data) {
				if(callback) callback(data)
			},
			type : "DELETE",
			data : reply
		})
	}
	return {
		get:get,
		list:list,
		add:add,
		modify:modify,
		remove:remove
	}
})();