/**
/**
 * 
 */
console.log("Reply Module");
var replyService = (function() {
    var add = function(reply, callback, error) {
        console.log("add() come!");
        $.ajax({
            url:"/reply/new",
            type:"post",
            data:JSON.stringify(reply),
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            success : function(result, satatus, xhr) {
                if(callback) callback(result);
            },
            error : function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }
    var getList = function(param, callback, error, before, after) {
        var pensionid = param.pensionid;
        var lastRno = param.lastRno || 0;
        var amount = param.amount || 10;
        
        console.log("getList() come!");
        var url = "/reply/pages/" + pensionid + "/" + lastRno + "/" + amount
        console.log("url : " + url);
        $.ajax({
            url:url,
            type:"get",
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            success : function(result, satatus, xhr) {
                if(callback) {
                	callback(result);
                }
                if(after) {
                	after(result);
                }
            },
            error : function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }
    
    var remove = function(reply, callback, error) {
        console.log("remove() come!");
        $.ajax({
            url:"/reply/" + reply.rno,
            type:"delete",
            data:JSON.stringify(reply),
            contentType:"application/json; charset=utf-8",
            success : function(result, satatus, xhr) {
                if(callback) callback(result);
            },
            error : function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }
    function update(reply, callback, error) {
        console.log("update() come!");
        $.ajax({
            url:"/reply/" + reply.rno,
            type:"put",
            data:JSON.stringify(reply),
            contentType:"application/json; charset=utf-8",
            success : function(result, status, xhr) {
            	console.log("reaching here?")
                if(callback) callback(result);
            },
            error : function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }
    var get = function(rno, callback, error) {
        console.log("get() come!");
        $.ajax({
            url:"/reply/" + rno,
            type:"get",
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            success : function(result, satatus, xhr) {
                if(callback) callback(result);
            },
            error : function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }
    function displayTime(timeValue) {
        console.log("displayTime() come@")
        moment.locale('ko');
        return moment(timeValue).fromNow();
    }

    return {
        add : add,
        getList : getList,
        remove : remove,
        get : get,
        update : update,
        displayTime : displayTime
    };
})();