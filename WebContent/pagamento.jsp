<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.*,model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css">
<title>Prenota le tue lezioni</title>

<style>
input.er{
	border-style: solid;
	border-color:red;
}
</style>
</head>

<body>

<%@ include file="header.jsp" %>

<div class="container" id="text">
    <div class="row">
    <div style="height:20px"></div>
            <div class="col-md-12">
            <div class="col-md-6 col-md-offset-3">
                <div class="well margin-30-top;">
                <h2><strong>Inserisci i tuoi dati di pagamento:</strong></h2>
                
                <div class="payment">
               
                 <form action="EffettuaAcquisto" method="post" id="form1">
                 
                 	
                    <div class="form-group">
                   
                        <label for="owner">Nome del proprietario:</label>
                        <input type="text" class="form-control" name="nome">
                    </div>
                    
                    <div class="form-group">
                        <label for="cvv">CVV:</label>
                        <input type="text" class="form-control" maxlength="4" style="width: 60px;" id="cvv" name="cvv" pattern="[0-9]{3,4}">
   
                    </div>
                    
                    <div class="form-group">
                        <label for="cardNumber">Numero della carta:</label>
                        <input type="text" class="form-control" maxlength="16" id="cardNumber" name="cardNumber" pattern="[0-9]{13,16}"> 
 
                    </div>
                    <div class="form-group">
                        <label>Data di Scadenza: </label>
                        <select>
                            <option>Gennaio</option>
                            <option>Febbraio </option>
                            <option>Marzo</option>
                            <option>Aprile</option>
                            <option>Maggio</option>
                            <option>Giugno</option>
                            <option>Luglio</option>
                            <option>Agosto</option>
                            <option>Settembre</option>
                            <option>Ottobre</option>
                            <option>Novembre</option>
                            <option>Dicembre</option>
                        </select>
                        <select>
                            <option> 2019</option>
                            <option> 2020</option>
                            <option> 2022</option>
                            <option> 2023</option>
                            <option> 2024</option>
                            
                        </select>
                    </div>
                    <div class="form-group">
                        <img src="http://www.free-credit-card-logos.com/credit_card_logos_visa_mc_amex_discover_paypal_sm.gif"/>
                        
                    </div>
                    <div class="form-group" >
                        <button type="submit" class="btn btn-default" >Conferma</button>
                    </div>
                </form>
            </div>
                
                 </div>
            
             
                </div>
            </div>
        </div>
    </div>
              
                  
</body>
</html>