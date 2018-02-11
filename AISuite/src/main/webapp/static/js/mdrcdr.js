function changeMdrDropdown(){
	
		if($('#mdr').val() == 'NO')
		{
			$('#commision').hide();
			$('#bas').hide();
			$('#val').hide();
			$('#instr').hide();
			$('#instr1').hide();
			$('#instr2').hide();
			$('#instr3').hide();
			$('#instr4').hide();
		}else
		{
			$('#commision').show();
			
			if($('#commissionBasis').val() == 'FLAT')
			{
				
				$('#bas').show();
				$('#val').show();
				$('#instr').hide();
				$('#instr1').hide();
				$('#instr2').hide();
				$('#instr3').hide();
				$('#instr4').hide();
			}else
			{
			
				$('#instr').show();
				$('#bas').hide();
				$('#val').hide();
				$('#instr1').show();
				$('#instr2').show();
				$('#instr3').show();
				$('#instr4').show();
			}
			
		}
		if($('#cdr').val() == 'NO')
		{
			$('#cdrComm').hide();
			$('#cdrbas').hide();
			$('#cdrval').hide();
			$('#cdrinstr').hide();
			$('#cdrinstr1').hide();
			$('#cdrinstr2').hide();
			$('#cdrinstr3').hide();
			$('#cdrinstr4').hide();
		}else{
			$('#cdrComm').show();
			if($('#cdrCommissionBasis').val() == 'FLAT')
			{
				$('#cdrbas').show();
				$('#cdrval').show();
				$('#cdrinstr').hide();
				$('#cdrinstr1').hide();
				$('#cdrinstr2').hide();
				$('#cdrinstr3').hide();
				$('#cdrinstr4').hide();
			}else
			{
				$('#cdrinstr').show();
				$('#cdrbas').hide();
				$('#cdrval').hide();
				$('#cdrinstr1').show();
				$('#cdrinstr2').show();
				$('#cdrinstr3').show();
				$('#cdrinstr4').show();
			}
		}
		
		
		
	}
	function com(){
	if($('#commissionBasis').val() == 'FLAT')
		{
			$('#bas').show();
			$('#val').show();
			$('#instr').hide();
			$('#instr1').hide();
			$('#instr2').hide();
			$('#instr3').hide();
			$('#instr4').hide();
		}else
		{
			$('#instr').show();
			$('#bas').hide();
			$('#val').hide();
			$('#instr1').show();
			$('#instr2').show();
			$('#instr3').show();
			$('#instr4').show();
		}
		
	
	}
	function cdrcomi(){
	
		if($('#cdrCommissionBasis').val() == 'FLAT')
			{
				$('#cdrbas').show();
				$('#cdrval').show();
				$('#cdrinstr').hide();
				$('#cdrinstr1').hide();
				$('#cdrinstr2').hide();
				$('#cdrinstr3').hide();
				$('#cdrinstr4').hide();
			}else
			{
				$('#cdrinstr').show();
				$('#cdrbas').hide();
				$('#cdrval').hide();
				$('#cdrinstr1').show();
				$('#cdrinstr2').show();
				$('#cdrinstr3').show();
				$('#cdrinstr4').show();
			}
		
	}
	
	function format(){
		var num = document.getElementById("value").value;
		var num1 = document.getElementById("value1").value;
		var num2 = document.getElementById("value2").value;
		var num3 = document.getElementById("value3").value;
		var num4 = document.getElementById("value4").value;
		var cdrnum = document.getElementById("cdrValue").value;
		var cdrnum1 = document.getElementById("cdrValue1").value;
		var cdrnum2 = document.getElementById("cdrValue2").value;
		var cdrnum3 = document.getElementById("cdrValue3").value;
		var cdrnum4 = document.getElementById("cdrValue4").value;
	
		if(num !=''){	
			if(!isNaN(num))
			{
				if(num.indexOf('.') > -1)
				{
				num = num.split('.');
				num[0] = num[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(num[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("value").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("value").value = '';
			return false;
			}
		}
		 if(num1 != ''){
			if(!isNaN(num1))
			{
				if(num1.indexOf('.') > -1)
				{
				num1 = num1.split('.');
				num1[0] = num1[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(num1[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("value1").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("value1").value = '';
			return false;
			}
		}
		 if(num2 != ''){
			if(!isNaN(num2))
			{
				if(num2.indexOf('.') > -1)
				{
				num2 = num2.split('.');
				num2[0] = num2[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(num2[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("value2").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("value2").value = '';
			return false;
			}
		}	
		 if(num3 != ''){
			if(!isNaN(num3))
			{
				if(num3.indexOf('.') > -1)
				{
				num3 = num3.split('.');
				num3[0] = num3[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(num3[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("value3").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("value3").value = '';
			return false;
			}
		}	
		 if(num4 != ''){
			if(!isNaN(num4))
			{
				if(num4.indexOf('.') > -1)
				{
				num4 = num4.split('.');
				num4[0] = num4[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(num4[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("value4").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("value4").value = '';
			return false;
			}
		}		
		
		if(cdrnum !=''){	
			if(!isNaN(cdrnum))
			{
				if(cdrnum.indexOf('.') > -1)
				{
				cdrnum = cdrnum.split('.');
				cdrnum[0] = cdrnum[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(cdrnum[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("cdrValue").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("cdrValue").value = '';
			return false;
			}
		}	
		if(cdrnum1 !=''){	
			if(!isNaN(cdrnum1))
			{
				if(cdrnum1.indexOf('.') > -1)
				{
				cdrnum1 = cdrnum1.split('.');
				cdrnum1[0] = cdrnum1[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(cdrnum1[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("cdrValue1").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("cdrValue1").value = '';
			return false;
			}
		}	
		if(cdrnum2 !=''){	
			if(!isNaN(cdrnum2))
			{
				if(cdrnum2.indexOf('.') > -1)
				{
				cdrnum2 = cdrnum2.split('.');
				cdrnum2[0] = cdrnum2[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(cdrnum2[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("cdrValue2").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("cdrValue2").value = '';
			return false;
			}
		}	
		if(cdrnum3 !=''){	
			if(!isNaN(cdrnum3))
			{
				if(cdrnum3.indexOf('.') > -1)
				{
				cdrnum3 = cdrnum3.split('.');
				cdrnum3[0] = cdrnum3[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(cdrnum3[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("cdrValue3").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("cdrValue3").value = '';
			return false;
			}
		}	
		if(cdrnum4 !=''){	
			if(!isNaN(cdrnum4))
			{
				if(cdrnum4.indexOf('.') > -1)
				{
				cdrnum4 = cdrnum4.split('.');
				cdrnum4[0] = cdrnum4[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
			
					if(cdrnum4[1].length > 2){
					alert('You may only enter two decimals!');
					document.getElementById("cdrValue4").value = '';
					return false;
					}
				}
			}
			else 
			{
			alert(' Please enter only numeric value with max two decimals');
			document.getElementById("cdrValue4").value = '';
			return false;
			}
		}	
	}
