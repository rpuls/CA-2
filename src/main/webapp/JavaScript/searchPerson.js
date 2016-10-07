$(document).ready(function () {
    $("#searchBtn").on("click", function () {
        $.ajax({
            url: "api/person/complete",
            type: "json",
            method: "GET"
        }).done(function (data) {
            var items = "";
            data.forEach(function (person) {
                items += "<li>" + person.firstName + " " + person.lastName + "</li>";
            });
            $("#searchResult").html(items);
        });
    });

});