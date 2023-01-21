$(document).ready(function() {
	$(".error").hide();
	var formValido=true;

	$("#passwordV").change(function verificaPassword(){
		var p=$("#passwordV").val();
		$.post("VerificaPasswordVecchiaAjax",{"p":p}, function(data, status){
			if (data.length==0){
				$("#passwordV").addClass("er").next().show();
				formValido=false;
			}
			else{
				if($("#passwordV").hasClass("er"))
					$("#passwordV").removeClass("er").next().hide();
			}
		});
	});


	$("#passwordN2").change(function verificaPassword(){
		var p2=$("#passwordN2").val();
		var p1=$("#passwordN1").val();
		if (p1!=p2){
			$("#passwordN2").addClass("er").next().show();
			formValido=false;
		}
		else{
			if($("#passwordN2").hasClass("er"))
				$("#passwordN2").removeClass("er").next().hide();
		}
	});

	$("#form1").submit(function(){
		return (formValido&&validateCity()&&validatePassoword());
	});

	function validateCity(){
		var cityformat = /^[A-Z]{1}[a-z]*$/;
		var field=document.getElementById("citta");
		if (field.value.match(cityformat)){
			if($(field).hasClass("er"))
				$(field).removeClass('er').next().hide();
			return true;
		}
			
		else{
			document.getElementById("citta").focus();
			$(field).addClass("er").next().show();
			return false;
		}
	}

	function validatePassword(){
		var usernameformat = /^[a-zA-Z0-9]{8,19}$/;
		var field=document.getElementById("passwordN1");
		if (document.getElementById("passwordN1").value.match(usernameformat)){
			if($(field).hasClass("er"))
				$(field).removeClass('er').next().hide();
			return true;
		}
			
		else{
			document.getElementById("passwordN1").focus();
			$(field).addClass("er").next().show();
			return false;
		}
	}

	});