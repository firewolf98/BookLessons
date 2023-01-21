$(document).ready(function() {
	$("#form1").submit(function(){
		var ris = true;

		if(!$("#datepicker").datepicker('getDate')){
			ris=false;
			alert("Devi selezionare un giorno per la tua lezione!");
		}
		
		if (!validateRadio($(this).attr("name"))){
			ris=false;
			alert("Devi selezionare un luogo per la tua lezione!");
		}
		
		if(!$("#oraInizio").val() || !$("#oraFine").val() ){
			ris=false;
			alert("Devi selezionare gli orari!");
	
		}
			
		return ris;
	});
	
	function validateRadio(){
		var valid = false;
		$('#form1').find(':input:radio').each(function(){
			if($(this).prop('checked'))
				valid = true;
		});

		return valid;
	}

});