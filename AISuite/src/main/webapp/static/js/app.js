/*new*/

$(document).delegate('.discription table tr','click',function(){
	var value=$(this).find('td').eq(0).text();
	var finalValue=value.replace('Rs.','');
	$('#amount').val(finalValue);
});

$("input[id='mobileNumber']").keyup(function count() {
    var input = this.value.length;
	if(input==10){
		$('#header').removeClass('background');
		$('.recharge_plans').show(500, 'easeInOutExpo');
	}
});









