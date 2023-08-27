document.addEventListener("DOMContentLoaded",() => {
    console.log("Hello Sample Component!");
    $("#buttonId").on("click",function(){
        let url = location.href.replaceAll('.html','.mysampleservletcomponent.json');
        let data = {"firstName": $("#firstNameId").val(), "lastName": $("#lastNameId").val(), "email": $("#emailId").val()};
        let $servletResponse = $(".my-sample-servlet-component .servlet-response");
        $.post(url, data, function (response){
            if(response.firstName !== "is valid"){
                $("#firstNameId").addClass("error");
                if ($("#firstNameId").val().trim() === "") {
                    $("#firstNameId").attr("placeholder", "campo obbligatorio");
                }
            }else{$("#firstNameId").removeClass("error");}
            if(response.lastName !== "is valid"){
                $("#lastNameId").addClass("error");
                if ($("#lastNameId").val().trim() === "") {
                    $("#lastNameId").attr("placeholder", "campo obbligatorio");
                }
            }else{$("#lastNameId").removeClass("error");}
            if(response.email !== "is valid"){
                $("#emailId").addClass("error");
                if ($("#emailId").val().trim() === "") {
                    $("#emailId").attr("placeholder", "campo obbligatorio");
                }
            }else{$("#emailId").removeClass("error");}
        });
    });
});