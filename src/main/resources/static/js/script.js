function getRandomWord(){
    document.getElementById("checkButton").disabled = false;
    document.getElementById("nextButton").disabled = true;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText == ""){
                showStatistic();
            } else {
                document.getElementById("word").innerHTML = this.responseText;
            }
        }
    };
    xhttp.open("GET", "http://localhost:8080/getRandomWord", true);
    xhttp.send();
}


function checkAnswer() {
    var answer = document.getElementById("answer").value;

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText == "right") {
                document.getElementById("errorField").style.color = "green";
            } else {
                document.getElementById("errorField").style.color = "red";
            }
            document.getElementById("errorField").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8080/checkWord?answer=" + answer, true);
    xhttp.send();

    document.getElementById("checkButton").disabled = true;
    document.getElementById("nextButton").disabled = false;
    document.getElementById("answer").value = "";
}

function showStatistic() {


    var rightAnsField = document.getElementById("rightAnswerNum");
    var wrongAnsField = document.getElementById("wrongAnswerNum");

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {

            var response = JSON.parse(this.responseText);

            rightAnsField.innerText = response["right"];
            wrongAnsField.innerText = response["wrong"];
        }
    };
    xhttp.open("GET", "http://localhost:8080/getStatistics", true);
    xhttp.send();

    document.getElementById("error-table").style.display = "table";
}