$(document).ready(function () {
    $("#searchBtn").on("click", function () {
        $.ajax({
            url: "api/company/complete",
            type: "json",
            method: "GET"
        }).done(function (data) {
            var items = "";
            data.forEach(function (company) {
                items += "<li>" + company.name + "</li>";
            });
            $("#searchResult").html(items);
        });
    });

});
