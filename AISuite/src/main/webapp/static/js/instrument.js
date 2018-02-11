function calculatePmntProcessingFees(dropdown){
		var indexVal = 0.00;
	 	var myindex  = dropdown.selectedIndex;
	    var paymentMode = dropdown.options[myindex].value
	    var commissionDetailList = document.getElementById("jsonCommissionDetails").value;
	 	var obj = jQuery.parseJSON (commissionDetailList);
		for (var i = 0; i < obj.length; i++) { 
			if(paymentMode == obj[i].instrumentType){
				document.getElementById('totalAmt').style.display = "block";
				document.getElementById('processingFees').style.display = "block";
				$('#processingFees').html('<h5><strong>Payment Processing Fees : </strong>'+'<i class="fa fa-inr"></i>'+obj[i].paymentProcessingFees.toFixed(2));
				$('#totalAmt').html('<hr>'+'<h5><strong>Total Amount : </strong>'+'<i class="fa fa-inr"></i>'+obj[i].totalAmount.toFixed(2));
				document.getElementById('onLoadFeesAmt').style.display = "none";
			}else if(myindex == 0){
				document.getElementById('totalAmt').style.display = "block";
				document.getElementById('processingFees').style.display = "block";
				$('#processingFees').html('<h5><strong>Payment Processing Fees : </strong>'+'<i class="fa fa-inr"></i>'+indexVal.toFixed(2));
				$('#totalAmt').html('<hr>'+'<h5><strong>Total Amount : </strong>'+'<i class="fa fa-inr"></i>'+obj[i].amount.toFixed(2));
				document.getElementById('onLoadFeesAmt').style.display = "none";
			}
		}
	}

function dropDownValidation(){
	var paymentMethod = document.getElementById("paymentMethod").value;
	if(paymentMethod == '--Select Instrument--'){
		alert("Please Select Any One Payment Method");
		return false;
	}else{
		return true;
	}
}
