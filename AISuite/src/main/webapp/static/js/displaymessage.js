function displayAlert(statusCode){
	$("#success").css({ display: "none" });
	$("#failure").css({ display: "none" });
	if(statusCode != ''){
	    if(statusCode == '11'){
	       $("#success").css({ display: "block" });
	    }else{
	       $("#failure").css({ display: "block" });
	    }
	 }
}
