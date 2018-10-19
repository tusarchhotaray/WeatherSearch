<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.js"></script>

<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.css" />
<script type="text/javascript" >
$(document).ready(function(){
	
	var tableHeader='<table class="ui celled table"> <thead> <tr><th>Name</th> <th>city</th> <th>state</th><th>Country</th> </tr></thead> <tbody>';
	
	var tableFooter='</tbody></table>';
	$(".button").click(function(event){
		 event.preventDefault();
		 var cityName= $("#cityName").val();
		 $("#tablecontainer").empty();
		 if(cityName){
			 $.ajax({
				  type: "GET",
				  url: "/findByCityName?cityName="+cityName,
				  success: function(result){
					  $("#tablecontainer").append(tableHeader);
					  for(var i=0; i< result.length; i++){
						  var tr= '<tr><td>'+result[i].name+' </td> <td>'+result[i].city+' </td>  <td> '+result[i].state+' </td> <td> '+result[i].country_name+'</td></tr>';
						  $("#tablecontainer ").find('tbody').append(tr);
					  }
					  $("#tablecontainer").append(tableFooter);
				  }
				});
			 
		 }else{
			 alert('Please provide city name');
		 }
	}); 
});
</script>
</head>

<body>
<div class="ui placeholder segment">
  <div class="ui two column very relaxed stackable grid">
    <div class="column">
      <div class="ui form">
        <div class="field">
          <label>City Name</label>
          <div class="ui left icon input">
            <input type="text" name="cityName" id="cityName" placeholder="Enter City name"
            pattern="^[a-zA-Z]+(?:[\s-][a-zA-Z]+)*$" title="City name should contain a-zA-z">
            <i class="search icon"></i>
          </div>
        </div>
        <div class="ui blue submit button">Search</div>
      </div>
    </div>
    
  </div>
  
</div>	 

	<div id="tablecontainer"> </div>

</body>

</html>