
$(function () {

    $("#newPhoneForComapany").on("click", function () {
        var htmlString = "<div class=\"phonediv\">";
        htmlString += "<div class=\"form-group\">";
        htmlString += "<label for=\"name\">PhoneNumber: </label>";
        htmlString += "<input required id=\"PhoneNumber\" type=\"text\" class=\"form-control\"   placeholder=\"Enter a Phone number\">";
        htmlString += "</div>";
        htmlString += "<div class=\"form-group\">";
        htmlString += "<label for=\"name\">Phone Description: </label>";
        htmlString += "<input required id=\"PhoneDescription\" type=\"text\" class=\"form-control\"  placeholder=\"Enter Marked Value\">";
        htmlString += "</div>";
        htmlString += "</div> ";

        $("#phoneContainer").append(htmlString);


    });

    $("#CompanyZip").on("change", function () {
        var zip = $("#CompanyZip").val();
        var ajaxCall = $.ajax({
            url: "api/cityinfonew?zip=" + zip,
            contentType: "application/json",
            method: "GET"
        });

        ajaxCall.done(function (data, status) {
            $("#CompanyCity").val(data);
            $("#CompanySubmit").attr("disabled", false);
        });
        ajaxCall.fail(function (response, fail, fej) {
            $("#CompanyCity").val("Not Found");
            $("#CompanySubmit").attr("disabled", true);
        });
    });
    
    
    $("#CompanySubmit").on("click", function(){
        
//        var $inputs = $('.form-control');
       var jsonobject = createJsonObjectFromForm();
        var ajax = $.ajax({
            url: "api/company",
            contentType: "application/json",
            data: jsonobject,
            method: "POST"
        });
        ajax.done(function(data){
            alert("succes");
            console.log(data);
        });
        ajax.fail(function(response, fail, error){
           alert("Json Fail!"); 
           console.log(response.responseText);
           console.log("Error!!!  Status Code: "+ response.responseJSON.code + " Message: " +response.responseJSON.message );
        });
    });
    
    function createJsonObjectFromForm(){
        var companyObject = new Object();
        
        // phone create start!
        var phoneArray = [];
        var div = $("#phoneContainer .phonediv"); // gets all the Phonedivs!
        
        $(div).each(function(){
            var phoneObj = new Object();;
            var number = $(this).find('#PhoneNumber').val();
            var description = $(this).find('#PhoneDescription').val();
           phoneObj.numer = number;
           phoneObj.description = description;
           phoneArray.push(phoneObj);
        });
        companyObject.phoneCollection = phoneArray; // this adds the array to the companyobject! Nameing has to be right!
        // phone creation end!
        
        // Adresse Start:
        var CityInfoObject = new Object();
        CityInfoObject.zipCode = $("#CompanyZip").val();
        CityInfoObject.city = $("#CompanyCity").val();
        var addressObejct = new Object();
        addressObejct.additionalInfo = $("#CompanyStreetAdditional").val();
        addressObejct.street = $("#CompanyStreetAddress").val();
        addressObejct.cityInfoNew = CityInfoObject;
        companyObject.adress = addressObejct;
        // Adress end
        
        //Rest of Company:
        companyObject.name = $("#CompanyName").val();
        companyObject.email = $("#CompanyEmail").val();
        companyObject.description = $("#CompanyDescription").val();
        companyObject.cvr = $("#ComapanyCVR").val();
        companyObject.marketValue = $("#CompanyMarkedValue").val();
        companyObject.NumEmployees = $("#CompanyEmployees").val();
        // End
        return JSON.stringify(companyObject);
        
    };

});

