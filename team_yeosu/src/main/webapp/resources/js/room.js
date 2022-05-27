/**
 */
var roomService = (function () {
	
	var getList = function (param, callback, error) {
		var pensionid=param.pensionid
        var lastRoomNum = param.lastRoomNum || 0;
        var amount = param.amount || 100;
        var url = "/room/pages/" + pensionid + "/"+ lastRoomNum + "/" + amount
        console.log(url)
        console.log("getList()......")
        $.ajax({
            url : url,
            type : "get",
            dataType : "json",
            /*beforeSend : function() {
            	if(before) {
            		before();
            	}
            },*/
            success : function (result, status, xhr) {
                if(callback) {
                    callback(result);
                }
               /* if(after) {
                	after(result);
                }*/
            },
            error : function (xhr, status, er) {
                if(error) {
                    error(er);
                }
            }
            
        })
    }
	
	var getListAdmin = function (param, callback, error) {
		var pensionid=param.pensionid
		var lastRoomNum = param.lastRoomNum || 0;
		var amount = param.amount || 100;
		var url = "/room/pages/" + pensionid + "/"+ lastRoomNum + "/" + amount
		console.log(url)
		console.log("getListAdmin()......")
		$.ajax({
			url : url,
			type : "get",
			dataType : "json",
			/*beforeSend : function() {
            	if(before) {
            		before();
            	}
            },*/
			success : function (result, status, xhr) {
				if(callback) {
					callback(result);
				}
				/* if(after) {
                	after(result);
                }*/
			},
			error : function (xhr, status, er) {
				if(error) {
					error(er);
				}
			}
			
		})
	}
	
	
	 return { 
		 getList : getList,
		 getListAdmin : getListAdmin,
	 };
})()