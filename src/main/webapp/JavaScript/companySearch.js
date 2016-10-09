$(document).ready(function () {

    $("#searchBtn").on("click", function () {
        var searchValue = $("#searchField").val();
        var url = {};
        if ($("#rCvr").is(':checked')) {
            url = "api/company/cvr/" + searchValue;
            byCVR(url);
        } else if ($("#rPhone").is(':checked')) {
            url = "api/company/phone/" + searchValue;
        } else if ($("#rZip").is(':checked')) {
            url = "api/company/zip/" + searchValue;
            byZipOrCity(url);
        } else if ($("#rCity").is(':checked')) {
            url = "api/company/city/" + searchValue;
            byZipOrCity(url);
        } else {
            url = "api/company/complete";
            all(url);
        }

    });
    
    function byCVR(url){
        $.ajax({
                url: url,
                type: "json",
                method: "GET"
            }).done(function (data) {
                var items = "";
                for (var company in data) {
                    items += "<li>" + data[company].valueOf("name") + "</li>";
                }
                $("#searchResult").append(items);

            });
    }
    
    function all(url){
        $.ajax({
                url: url,
                type: "json",
                method: "GET"
            }).done(function (data) {
                var items = "";
                data.forEach(function (company) {
                   items += "<li>" + company.name + "</li>";
                });
                $("#searchResult").append(items);

            });
    }
    
    function byZipOrCity(url){
        $.ajax({
                url: url,
                type: "json",
                method: "GET"
            }).done(function (data) {
                var items = "";
                data.forEach(function (company) {
                   items += "<li>" + company.name + "</li>";
                });
                $("#searchResult").append(items);

            });
    }
});
