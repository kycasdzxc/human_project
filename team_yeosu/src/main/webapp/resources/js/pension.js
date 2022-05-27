/**
 * 
 */


var pensionService = (function () {
	
	var getList = function (param, callback, error) {
        var lastPensionid = param.lastPensionid || 0;
        var amount = param.amount || 10;
        var data = param.option || {};
        var url = "/pension/pages/" + lastPensionid + "/" + amount
        /*console.log(url)
        console.log("getList()......")*/
        $.ajax({
            url : url,
            type : "get",
            dataType : "json",
            data : data,
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
		 getList : getList
	 };
})()