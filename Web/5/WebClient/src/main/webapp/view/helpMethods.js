function helloWorld() {
    console.log("Hello world!");
}

function generateForm(formId, formInfo) {

    let form = document.getElementById(formId);

    function handleForm(event) {
        event.preventDefault();
    }

    form.addEventListener('submit', handleForm);

    let button = document.createElement("button");

    button.setAttribute('class', "button");

    button.textContent = formInfo.buttonInfo.text;

    button.className = "btn btn-info btn-lg";

    formInfo.inputFields.forEach(info => {
        let label = document.createElement("label");

        label.textContent = info.labelText;

        label.setAttribute("style", "display:block");

        form.appendChild(label);

        let inputField = document.createElement("input");

        inputField.setAttribute("id", info.inputId);
        inputField.setAttribute("type", info.inputType);

        inputField.setAttribute("style", "display:block; width:200px");

        inputField.setAttribute("class", "form-control");

        form.appendChild(inputField);

        if (info.validator) {

            inputField.addEventListener('input', function (evt) {
                info.validator(evt.target);
            });

            button.addEventListener("click", function () {
                info.validator(inputField);
            })

            let erroMsg = document.createElement("div");

            erroMsg.textContent = "Test error";

            erroMsg.style.display = "none";

            erroMsg.className = "alert alert-danger";


            form.appendChild(erroMsg);
        }

    })


    form.appendChild(button);
}


const FormBook =
    {
        inputFields: [
            {labelText: "Email", inputId: "emailId", inputType: "text", validator: validateEmail},
            {labelText: "Country", inputId: "countryId", inputType: "text"},
        ],
        buttonInfo: {text: "Book"}
    };

const FormContact =
    {
        inputFields: [
            {labelText: "Email", inputId: "emailId", inputType: "text", validator: validateEmail},
            {labelText: "Phone number", inputId: "phoneId", inputType: "text", validator: validatePhone},
            {labelText: "Subject", inputId: "subjectId", inputType: "text"},
        ],
        buttonInfo: {text: "Confirm"}
    };


function validatePhone(input) {
    let phone = input.value;

    let regex = "^\\+375 \\((17|29|33|44)\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$";

    if (!phone.match(regex)) {

        input.nextSibling.style.display = "block";
        input.nextSibling.textContent = "invalid phone format";
    } else {
        input.nextSibling.style.display = "none";
    }
}

function validateEmail(input) {
    let mail = input.value;

    let regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\\])"

    if (!mail.match(regex)) {
        console.log("Regex didn't match");

        input.nextSibling.style.display = "block";

        input.nextSibling.textContent = "Invalid email format";
    } else {
        console.log("Regex did match");
        input.nextSibling.style.display = "none";
    }


}


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function setCookie(cname, cvalue) {
    document.cookie = cname + "=" + cvalue + "";
}

function saveShowUserCookies() {

    console.log("Getting cookie info!");

    visitsCount = getCookie("visitsCount");

    console.log("Got visits count:" + visitsCount)

    if (visitsCount === "" || visitsCount === "NaN") {
        setCookie("visitsCount", (1).toString())
    } else {
        setCookie("visitsCount", (parseInt(visitsCount) + 1).toString());
    }

    setCookie("lastSessionTime", new Date().toDateString() + ". " + new Date().toTimeString())
}