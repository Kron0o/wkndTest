$(document).ready(function() {
    $("#submitButton").click(function(event) {
        event.preventDefault();
        $("#divInput").hide();
        $.post(location.href.replaceAll('.html', '.servletQuery.json'),
            {"path":$("#rootPath").val(),
            "customProperty":$("#customProperty").val(),
            "keywords":$("#keywords").val(),
            "type": $("#type").val()},
            function(response) {
                var responseString = JSON.stringify(response, null, 2);
                var h3 = $("<h3>").text(responseString);
                h3.appendTo($("#divQuery"));
              },
              "json"
            );
        $("#divQuery").show();
    });
});