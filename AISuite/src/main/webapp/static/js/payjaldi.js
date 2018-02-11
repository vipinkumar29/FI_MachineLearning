function merchantSearch(url){
var merchantDetailsCode = document.getElementById("merchantDetailsCode").value;
if(merchantDetailsCode != ""){
	document.forms["invoiceSettingsForm"].action =url; 
	document.forms["invoiceSettingsForm"].submit(); 
}else{
	alert("Please Enter merchantDetailsCode to search");
}

}
function onSubmitValidation(){
var billerInfoCode = document.getElementById("billerInfoCode").value;
if(billerInfoCode == ""){
	alert("BillerInfoCode Can Not Be Empty");
	return false;
}else{
	return true;
}
}

function dropDownChangeforAddCharge(){
	var isAdditionalCharge = document.getElementById("isAdditionalCharge").value;
	if(isAdditionalCharge == "false"){
		document.getElementById("additionalChargeLevel").disabled = true;
		document.getElementById("additionalChargeBasis").disabled = true;
	}else{
		document.getElementById("additionalChargeLevel").disabled = false;
		document.getElementById("additionalChargeBasis").disabled = false;
	}
}



function dropDownChangeforDiscount(){
	var isDiscount = document.getElementById("isDiscount").value;

	
	if(isDiscount == "false"){
			document.getElementById("discountLvl").disabled=true;
			document.getElementById("discountBasis").disabled=true;
	}else{
			document.getElementById("discountLvl").disabled=false;
			document.getElementById("discountBasis").disabled=false;
	}

}



function excelDownload(url){
		var invCode = document.getElementById('invoiceSettingCode').value;
		
		if(invCode ==''){
			alert("InvoiceSettingCode should not be empty.");
			return false;
		}else{
       		 document.forms["uploadInvoiceForm"].action = url+invCode; 
       		 document.forms["uploadInvoiceForm"].target = "_parent";
			 document.forms["uploadInvoiceForm"].submit();
			 return true;
		}

}

function fillInvoiceDetails(url){
	
	var invCode = document.getElementById('invoiceSettingCode').value;
	if(invCode ==''){
		alert("InvoiceSettingCode should not be empty.");
		return false;
	}else{
   		 document.forms["uploadInvoiceForm"].action = url+invCode; 
		 document.forms["uploadInvoiceForm"].submit();
		 return true;
	}

}


function preview(url){
		var invCode = document.getElementById('invoiceSettingCode').value;
		if(invCode ==''){
		    alert("InvoiceSettingCode should not be empty.");
			return false;
		}else{
              document.forms["uploadInvoiceForm"].action = url+invCode; 
		      document.forms["uploadInvoiceForm"].submit();
		      return true;
		}
}

function validations(url){
         var uploadInvoiceFile = document.getElementById('uploadInvoiceFile').value;
         if(uploadInvoiceFile == ''){
         	alert("Please Choose UploadedFile.");
			return false;
		 }else{
		      document.forms["uploadInvoiceForm"].action = url; 
		      document.forms["uploadInvoiceForm"].target = "_parent";
		      document.forms["uploadInvoiceForm"].submit();
			return true;
		 }
}


	function payToMerchant(path){
		
	var invoiceDtlCode = document.getElementById("invoiceDetailCode").value;
	var url =path+invoiceDtlCode;
	
		document.forms["invoiceSettingsForm"].setAttribute('action',url);
		document.forms["invoiceSettingsForm"].submit();

}





function change(url){

	
	if(document.getElementById('invoicSettingsCode').value != '' && document.getElementById('invoicSettingsCode').value != 'Select')
	{
		document.forms["userDefinedUILabelFormNew"].action = url; 
		document.forms["userDefinedUILabelFormNew"].submit();
	}	
}

function uiSearch(url){
    document.forms["userDefinedUILabelFormNew"].action = url; 
	document.forms["userDefinedUILabelFormNew"].submit(); 
}

function validateInvoiceCode(){
	 var message = document.getElementById('message').value;
		 if(message =="true"){
		 	alert("Please Enter Valid InvoiceSetting Code.");
		 	document.getElementById('invoiceSettingCode').value='';
		 	window.close ();
		 	return false;
		 }
}

function invoiceSearch(url){
var invoiceSettingCode = document.getElementById("invoiceSettingCode").value;
if(invoiceSettingCode != ""){
	document.forms["invoiceSettingsForm"].action =url; 
	document.forms["invoiceSettingsForm"].submit(); 
}else{
	alert("Please Enter invoiceSettingCode to search");
}

}


function disabled(){
		if(document.getElementById("disable").value =='true'){ 
			document.getElementById("print").disabled = true;
		}
	}
	
	function printForm(url){
	var merchantCode = document.getElementById('code').value;
		document.forms["merchantRegistrationForm"].action = window.open(url+merchantCode,'SearchPopUpName','location=no,width=500,height=400'); 
		document.forms["merchantRegistrationForm"].submit(); 
}



function search(){
    window.print();
}

function disabled(){

		if(document.getElementById("disable").value =='true'){ 
			document.getElementById("perState").disabled = true;
			document.getElementById("regType").disabled = true;
			document.getElementById("accountType").disabled = true;
			document.getElementById("accountTe").disabled = true;
			document.getElementById("identityDocuments").disabled = true;
			document.getElementById("addressproofDocuments").disabled = true;
			document.getElementById("firmDetailsC").disabled = true;
			document.getElementById("firmDetailsD").disabled = true;
			document.getElementById("firmDetailsA").disabled = true;
			document.getElementById("firmDetailsA1").disabled = true;
			document.getElementById("firmDetailsA2").disabled = true;
			document.getElementById("firmDetailsA3").disabled = true;
			document.getElementById("firmDetailsB").disabled = true;
			document.getElementById("firmDetailsB1").disabled = true;
			document.getElementById("firmDetailsB2").disabled = true;
			document.getElementById("firmDetailsB3").disabled = true;
		var form = document.getElementById("merchantRegistrationForm");
		var elements = form.elements;
		for (var i = 0, len = elements.length; i < len; ++i) {
		    elements[i].readOnly = true;
		}
		}
}


 jQuery(document).ready(function() {
 
    jQuery('#newPassword, #confirmPassword').on('keyup', function(e) {
 
        if(jQuery('#newPassword').val() == '' && jQuery('#confirmPassword').val() == '')
        {
            jQuery('#passwordStrength').removeClass().html('');
            return false;
        }
 
     if(jQuery('#newPassword').val() != '' && jQuery('#confirmPassword').val() != '' && jQuery('#newPassword').val() != jQuery('#confirmPassword').val())
    	{
    		jQuery('#passwordStrength').removeClass().addClass('alert alert-error').html('Passwords do not match!');
    		jQuery('#submit').attr("disabled", "disabled");
        	return false;
    	}else {
    		jQuery('#submit').removeAttr("disabled"); }
 
        // Must have capital letter, numbers and lowercase letters
        var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
 
        // Must have either capitals and lowercase letters or lowercase and numbers
        var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
 
        // Must be at least 6 characters long
        var okRegex = new RegExp("(?=.{8,}).*", "g");
 
        if (okRegex.test(jQuery(this).val()) === false) {
            // If ok regex doesn't match the password
        	jQuery('#passwordStrength').removeClass().addClass('alert alert-error').html('Password must be atleast 8 characters long.');
 			jQuery('#submit').attr("disabled", "disabled");
        } else if (strongRegex.test(jQuery(this).val())) {
            // If reg ex matches strong password
           jQuery('#passwordStrength').removeClass().addClass('alert alert-success').html('Strong Password.');
           jQuery('#submit').removeAttr("disabled"); 
        } else if (mediumRegex.test(jQuery(this).val())) {
            // If medium password matches the reg ex
            jQuery('#passwordStrength').removeClass().addClass('alert alert-info').html('Medium Strength.');
            jQuery('#submit').removeAttr("disabled"); 
        } else {
            // If password is ok
            jQuery('#passwordStrength').removeClass().addClass('alert alert-error').html('Weak Password');
            jQuery('#submit').removeAttr("disabled"); 
        }
        
        return true;
    });
});



