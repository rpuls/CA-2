$(document).ready(function () {
    refreshHobbies();

    $("#pAdd").click(function ()
    {
        var jsonobject = createJsonObjectFromForm();
        $.ajax({
            url: "api/person",
            type: "json",
            method: "POST",
            data: jsonobject,
            dataType: "json",
            contentType: "application/json; charset=utf-8"

        }).done(function (data, status) {
            console.log(data);
            insertNewData(data);
        }).fail(function (xhr, status, error) {
            console.log("Error!!!  Status Code: " + xhr.responseJSON.code + " Message: " + xhr.responseJSON.message);
        });
    });



    function refreshHobbies() {

        $.ajax({
            url: "api/hobby",
            contentType: "application/json",
            method: "GET"
        }).done(function (hobby) {
            var items = "";
            hobby.forEach(function (hobby) {

                items += "<option value='" + $(hobby).attr("id") + "'>" + $(hobby).attr("name") + "</options>";
            });
            $("#hobbylist").append(items);
        });
    }

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



    function createJsonObjectFromForm()
    {
        var personObject = new Object();
        // phone create start!
        var phoneArray = [];
        var div = $("#phoneContainer .phonediv"); // gets all the Phonedivs!

        $(div).each(function () {
            var phoneObj = new Object();
            ;
            var number = $(this).find('#PhoneNumber').val();
            var description = $(this).find('#PhoneDescription').val();
            phoneObj.numer = number;
            phoneObj.description = description;
            phoneArray.push(phoneObj);
        });
        personObject.phoneCollection = phoneArray; // this adds the array to the companyobject! Nameing has to be right!
        // phone creation end!

        // Adresse Start:
        var CityInfoObject = new Object();
        CityInfoObject.zipCode = $("#CompanyZip").val();
        CityInfoObject.city = $("#CompanyCity").val();
        var addressObejct = new Object();
        addressObejct.additionalInfo = $("#CompanyStreetAdditional").val();
        addressObejct.street = $("#CompanyStreetAddress").val();
        addressObejct.cityInfoNew = CityInfoObject;
        personObject.adress = addressObejct;
        // Adress end

        //Rest of Person
        personObject.firstName = $("#firstName").val();
        personObject.lastName = $("#lastName").val();
        personObject.email = $("#email").val();
        
        //Hobbies:
        var hobbies = $("#hobbylist").val() || [];
        var hobbieObjects = [];
        $(hobbies).each(function(index){
            hobbieObject = new Object();
            hobbieObject.id= hobbies[index];
            hobbieObjects.push(hobbieObject);
        });
        personObject.hobbyCollection = hobbieObjects;
        //End


        return JSON.stringify(personObject);
    }
    
    function insertNewData(data){
        clearFields();
        var htmlString = "<h3> Inserted Person!<h2>";
        htmlString += "<ul>";
        htmlString += "<li> id: " + data.id + "</li>";
        htmlString += "<li> name: " + data.name + "</li>";
        htmlString += "<li> name: " + data.name + "</li>";
        htmlString += "</ul>";
        
        $("#resultDiv").append(htmlString);
    }
    
    function clearFields(){
        
    };

});