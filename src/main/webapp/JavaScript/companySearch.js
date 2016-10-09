$(document).ready(function () {

    $("#searchBtn").on("click", function () {
        var searchValue = $("#searchField").val();
        if ($("#rCvr").is(':checked')) {
            var url = "api/company/cvr/" + searchValue;
        } else if ($("#rPhone").is(':checked')) {
            var url = "api/company/phone/" + searchValue;
        } else if ($("#rZip").is(':checked')) {
            var url = "api/company/zip/" + searchValue;
        } else if ($("#rCity").is(':checked')) {
            var url = "api/company/city/" + searchValue;
        } else {
            var url = "api/company/complete";
        }

        $.ajax({
            url: url,
            type: "json",
            method: "GET"
        }).done(function (data) {
            var items = "";
            if(typeof data !== 'object'){
            data.forEach(function (company) {
                    items += "<li>" + company.name + "</li>";
                });
            } else {
                items = "<li>" + data.name + "</li>";
            }
            $("#searchResult").html(items);
        });
    });

});
