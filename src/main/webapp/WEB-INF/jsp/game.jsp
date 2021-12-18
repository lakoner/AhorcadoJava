<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Hang Man Game</title>
    <style>
        body {
            margin: 0;
        }

        .container {
            height: 100vh;
            width: 100%;
            background: #f1f1f1;
            position: fixed;
        }

        .content-wrapper {
            margin-top: 5%;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80%;
        }

        .game-container {
            width: 60%;
            height: 500px;
        }

        .info-container {
            display: flex;
            height: 100%;
            -webkit-box-shadow: 5px 5px 11px 1px rgba(0, 0, 0, 0.36);
            box-shadow: 5px 5px 11px 1px rgba(0, 0, 0, 0.36);
            background: white;
            border-radius: 10px;
        }

        .img-container {
            width: 60%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .game-details-container {
            height: 100%;
            width: 40%;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="content-wrapper">
        <div class="game-container">
            <div class="info-container">
                <div class="img-container">
                    <img id="hangman" width="60%" src="0.jpg">
                </div>
                <div class="game-details-container">
                    <p style="text-align: center;font-size: 25px">Id Partida: <span id="gameIdElement">${gameId}</span>
                    </p>
                    <p><b>Nº de fallos:</b> <span id="mistakes"></span></p>
                    <p><b>Nº de caracteres:</b> <span id="charNo"></span></p>
                    <p><b>Palabra:</b> <span id="wordTrial"></span></p>
                    <input id="characterInput" type="text" class="form-control" name="letra"
                           placeholder="indica una letra" required
                           autofocus/>
                    <button onclick="letterTry()">Try Letter</button>
                    <p><span id="message"></span></p>
                </div>
            </div>
        </div>
    </div>

</div>


<div id="demo"></div>

</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    function init() {
        const xhttp = new XMLHttpRequest();
        let gameId = document.getElementById("gameIdElement").innerHTML;
        xhttp.onload = function () {
            let responseObject = JSON.parse(this.responseText);
            document.getElementById("charNo").innerHTML = responseObject.noOfCharacters;
            let initWord = "";
            for (let i = 0; i < responseObject.noOfCharacters; i++) {
                initWord += "_";
            }
            document.getElementById("wordTrial").innerHTML = initWord;
        }
        xhttp.open("POST", "http://localhost:8093/api/gameInfo/" + gameId);
        xhttp.send();
    }

    function letterTry() {
        document.getElementById("message").innerHTML = "";
        if (document.getElementById("characterInput").value.length < 1) {
            document.getElementById("message").innerHTML = "Please input a character";
            return;
        }
        const xhttp = new XMLHttpRequest();
        let gameId = document.getElementById("gameIdElement").innerHTML;
        let characterInput = document.getElementById("characterInput").value;
        let responses = {
            ALREADY_EXISTS: "ALREADY_EXISTS",
            DOES_NOT_EXIST: "DOES_NOT_EXIST",
            YOU_ARE_DEAD: "YOU_ARE_DEAD",
            YOU_SURVIVED: "YOU_SURVIVED"
        };

        xhttp.onload = function () {
            let responseObject = JSON.parse(this.responseText);
            switch (responseObject.markedCharactersResponse) {
                case responses.ALREADY_EXISTS:
                    document.getElementById("message").innerHTML = "That character already exists!"
                    return;
                case responses.DOES_NOT_EXIST:
                    document.getElementById("message").innerHTML = "Oups! That isn t part of the word!"
                    document.getElementById("mistakes").innerHTML = responseObject.mistakes;
                    document.getElementById("hangman").src = responseObject.mistakes + ".jpg";
                    return;
                case responses.YOU_ARE_DEAD:
                    document.getElementById("message").innerHTML = "YOU DIED!"
                    return;
                case responses.YOU_SURVIVED:
                    document.getElementById("message").innerHTML = "Congrats! You get to live another day! Time: "+responseObject.elapsedTime
                    document.getElementById("wordTrial").innerHTML = responseObject.solvedWord;
                    return;
            }
            document.getElementById("wordTrial").innerHTML = responseObject.markedCharactersResponse;
        }
        xhttp.open("POST", "http://localhost:8093/api/letterTry/" + gameId + "/" + characterInput);
        xhttp.send();
        document.getElementById("characterInput").value = "";
    }

    $(document).ready(function () {
        init();
    });

</script>