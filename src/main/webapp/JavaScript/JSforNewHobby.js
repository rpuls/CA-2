
$(function(){
    
    $("#hobbySubmit").on("click", function(){
       var jsonobject;
       
       
       var ajaxCall = $.ajax({
           url: "api/",
           type: "POST",
           data: jsonobject
       });
       
       ajaxCall.done(function(data,status){
          console.log(data); 
       });
        
    });
    
}); 


