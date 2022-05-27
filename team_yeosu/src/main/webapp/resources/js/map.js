/**
 * 
 */
var mapService = (function () {
	
	
	var getList = function (callback, error) {
        /*console.log(url)
        console.log("getList()......")*/
        $.ajax({
            url : "/map/map",
            type : "get",
            dataType : "json",
            async : false,
            success : function (result, status, xhr) {
                if(callback) {
                    callback(result);
                }
            },
            error : function (xhr, status, er) {
                if(error) {
                    error(er);
                }
            }
            
        })
    }
	var get = function(pensionid, callback, error) {
		console.log(pensionid)
		$.ajax({
            url : "/map/getId",
            type : "get",
            dataType : "json",
            data : pensionid,
            success : function (result, status, xhr) {
                if(callback) {
                    callback(result);
                }
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
		 get : get
	 };
})()