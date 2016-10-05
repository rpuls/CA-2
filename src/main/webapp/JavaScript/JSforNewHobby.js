
$(function () {

    $("#hobbySubmit").on("click", function () {
        var object = new Object();
        object.name = $("#hobbyName").val();
        object.description = $("#hobbyDescription").val();
        var jsonobject = JSON.stringify(object);
        var ajaxCall = $.ajax({
            url: "http://localhost:8080/CA-2/api/hobby",
            type: "application/json",
            data: jsonobject,
            method: "POST"
        });

        ajaxCall.done(function (data, status) {
            console.log(data);
        });
        ajaxCall.fail(function (xhr, status, error) {
            console.log("Error!!! " + xhr.responseText);
        });

    });

});


