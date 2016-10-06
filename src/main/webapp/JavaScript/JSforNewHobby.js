
$(function () {

    $("#hobbySubmit").on("click", function () {
        var object = new Object();
        object.name = $("#hobbyName").val();
        object.description = $("#hobbyDescription").val();
        var jsonobject = JSON.stringify(object);
        var ajaxCall = $.ajax({
            url: "http://localhost:8080/CA-2/api/hobby",
            contentType: "application/json",
            data: jsonobject,
            method: "POST"
        });

        ajaxCall.done(function (data, status) {
            console.log(data);
            insertNewData(data);
        });
        ajaxCall.fail(function (xhr, status, error) {
            console.log("Error!!!  Status Code: "+ xhr.responseJSON.code + " Message: " +xhr.responseJSON.message );
        });

    });

    function insertNewData(data){
        var id = $(data).prop("id");
        var name = $(data).prop("name");
        var description = $(data).prop("description");
        
        var htmlString = "<h2> Inserted New Hobby in the database with the attriubutes </h2>";
        htmlString += "<ul>";
        htmlString += "<li> id: " + id +"</li>";
        htmlString += "<li> Name: " + name +"</li>";
        htmlString += "<li> description: " + description +"</li>";
        htmlString += "</ul>";
        $("#newdiv").append(htmlString);
    }
});


