<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
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

    .title {
        font-size: 50px;
        text-align: center;
        margin-top: calc(50vh - 200px);
    }

    .content-wrapper {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .init-game {
        padding: 10px 20px;
        margin-top: 10px;
        border: 1px solid black;
        text-decoration: none;
        color: black;
        display: block;
    }

    .init-game:hover {
        background: aliceblue;
    }

    .difficulty {
        display: block;
        padding: 10px 22px;
    }
    td{
        text-align: center;
    }
    .ranking-container{
        margin-top: 10px;
        height: auto;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;

    }

    table{
        border: 1px solid black;
        border-collapse: collapse;
        width: 50%;
    }
</style>
<body>
<div class="container">
    <div class="title">
        <span>Welcome to your death!</span>
    </div>
    <div class="content-wrapper">
        <form action="initGame" method="POST">
            <p>Choose Difficulty:</p>
            <select class="difficulty" name="difficulty">
                <option value="1">Facil</option>
                <option value="2" selected>Medio</option>
                <option value="3">Dificil</option>
            </select>
            <button class="init-game" type="submit">New Game</button>
        </form>

    </div>
    <div class="ranking-container">
        <h3>Rankings</h3>
    </div>
    <div class="ranking-container">
        <c:set var="count" value="1" scope="page" />
        <table>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${ranking}" var="entry">
                <tr>
                    <td>${count}</td>
                    <td>${entry.key}</td>
                    <td><fmt:formatNumber maxFractionDigits="0" value = "${entry.value/60}"/> mins and ${entry.value%60} seconds</td>
                </tr>
                <c:set var="count" value="${count + 1}" scope="page"/>
            </c:forEach>
        </table>

    </div>
</div>

</body>
</html>