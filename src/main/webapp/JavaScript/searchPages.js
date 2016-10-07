

// Search options toggle for person page
$("#options").hide();
$("#lessOptions").hide();

$("#moreOptions").on("click", function () {
    $(this).hide();
    $("#lessOptions").show();
    $("#options").show();
});

$("#lessOptions").on("click", function () {
    $(this).hide();
    $("#moreOptions").show();
    $("#options").hide();
});

$('#rCity').on("click", function() {
   $("#searchField").attr("placeholder", "Enter city name. ie. Randers");
   $('#rZip').prop('checked', false);
   $('#rPhone').prop('checked', false);
});

$('#rZip').on("click", function() {
   $("#searchField").attr("placeholder", "Enter zip code. ie. 2200"); 
   $('#rCity').prop('checked', false);
   $('#rPhone').prop('checked', false);
});

$('#rPhone').on("click", function() {
   $("#searchField").attr("placeholder", "Enter phone number. ie. 44557799");
   $('#rCity').prop('checked', false);
   $('#rZip').prop('checked', false);
});


//});