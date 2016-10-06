
$(function () {
  
    $("#newPhoneForComapany").on("click",function(){
       var htmlString = "<div id=\"phonediv\">";
           htmlString += "<div class=\"form-group\">";
           htmlString += "<label for=\"name\">PhoneNumber: </label>";
           htmlString += "<input type=\"text\" class=\"form-control\"  class=\"PhoneNumber\" placeholder=\"Enter a Phone number\">";
           htmlString += "</div>";
           htmlString += "<div class=\"form-group\">";
           htmlString +="<label for=\"name\">Phone Description: </label>";
           htmlString += "<input type=\"text\" class=\"form-control\" class=\"PhoneDescription\" placeholder=\"Enter Marked Value\">";
           htmlString +="</div>";
           htmlString += "</div> ";
           
           $("#phonediv").append(htmlString);
       
        
    });
    
    
    
    
});

