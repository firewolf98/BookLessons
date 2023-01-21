$(document).ready(function() {
	$(".error").hide();
	$("#form1").submit(function(){
		var ris = true;

		$("#form1").find('input[type="text"]').each(function() {
			if (!validateText($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
			}

		});
		
		$("#form1").find('input[type="password"]').each(function() {
			if (!validatePassword($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
			}

		});
		
		$("#form1").find('input[type="radio"]').each(function() {
			if (!validateSesso($(this).attr("name"))){
				//$(this).addClass("er").siblings("span:first").show();
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					//$(this).removeClass('er').siblings("span:first").hide();
					$(this).removeClass('er').next().hide();
			}

		});
		
		$("#form1").find('input[type="number"]').each(function() {
			if (!validateTariffa($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
				
			}

		});
		
		$("#form1").find('input[type="email"]').each(function() {
			if (!validateEmail($(this).attr("name"))){
				$(this).addClass("er").next().show();
				ris=false;
			}
			else{
				if($(this).hasClass("er"))
					$(this).removeClass('er').next().hide();
			}

		});
		
		return ris;
	});

	function validateText(namef){
		if (namef=="nome")
			return validateName();
		if (namef=="cognome")
			return validateSurname();
		if (namef=="citta")
			return validateCity();
		if (namef=="username")
			return validateUsername();
	}

	function validateName(){
		var nameformat = /^[A-Z]{1}[A-Za-z ]*$/;
		if (document.getElementById("nome").value.match(nameformat))
			return true;
		else{
			document.getElementById("nome").focus();
			return false;
		}
	}

	function validateSurname(){
		var surnameformat = /^[A-Z]{1}['a-zA-Z ]*$/;
		if (document.getElementById("cognome").value.match(surnameformat))
			return true;
		else{
			document.getElementById("cognome").focus();
			return false;
		}
	}

	function validateCity(){
		var cityformat = /^[A-Z]{1}[a-z]*$/;
		if (document.getElementById("citta").value.match(cityformat))
			return true;
		else{
			document.getElementById("citta").focus();
			return false;
		}
	}

	function validateUsername(){
		var usernameformat = /^[a-zA-Z0-9]{1}[a-z_A-Z0-9]{2,19}$/;
		if (document.getElementById("username").value.match(usernameformat))
			return true;
		else{
			document.getElementById("username").focus();
			return false;
		}
	}

	function validateEmail(){
		var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/;
		if (document.getElementById("email").value.match(mailformat))
			return true;
		else{
			document.getElementById("email").focus();
			return false;
		}
	}

	function validatePassword(){
		var usernameformat = /^[a-zA-Z0-9]{8,19}$/;
		if (document.getElementById("password").value.match(usernameformat))
			return true;
		else{
			document.getElementById("password").focus();
			return false;
		}
	}

	

	function validateSesso(){
		var valid = false;
		$('#form1').find(':input:radio').each(function(){
			if($(this).prop('checked'))
				valid = true;
		});

		return valid;
	}

	function validateTariffa(){
		var val=document.getElementById("tariffa").value;
		if (val>=10&&val<=40)
			return true;
		else return false;
	}

});