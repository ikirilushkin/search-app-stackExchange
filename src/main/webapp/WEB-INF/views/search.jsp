<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/style.css">
    <title>Search</title>
</head>
<body>
<nav class="navbar navbar-light bg-light justify-content-between search-navbar">
    <div class="container">
        <a class="navbar-brand" href="<spring:url value="/search"/>">Search-App</a>
        <form class="form-inline">
            <input class="form-control mr-sm-2" name="text" value="${search}" type="search" placeholder="Search"
                   aria-label="Search">
            <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="container result-container">
    <c:if test="${result != null}">
        <div class="row">
            <div class="col">
                <c:if test="${result.questions.size() > 0}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Author</th>
                            <th scope="col" class="date-col">Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${result.questions}" var="question">
                            <tr class="${question.isAnswered() ? 'table-success' : ''}">
                                <td>
                                    <a href="${question.link}"
                                       target="_blank">
                                            ${question.title}
                                    </a>
                                </td>
                                <td>
                                    <a href="${question.owner.link}"
                                       target="_blank">
                                            ${question.owner.name}
                                    </a>
                                </td>
                                <td>
                                <span class="text-secondary">
                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${question.date}"/>
                                </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${result.questions.size() == 0}">
                    <p class="text-center text-danger">Nothing was found for "${search}"</p>
                </c:if>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="search-navigation">
                    <c:if test="${page > 1}">
                        <a href="<spring:url value="/search/?page=${page - 1}&text=${search}"/>"
                           class="btn btn-outline-secondary btn-sm search-navigation__btn">Prev</a>
                    </c:if>
                    <c:if test="${result.hasMore}">
                        <a href="<spring:url value="/search/?page=${page + 1}&text=${search}"/>"
                           class="btn btn-outline-secondary btn-sm search-navigation__btn">Next</a>
                    </c:if>
                </div>
            </div>
        </div>
    </c:if>
</div>
<dv class="footer fixed-bottom"/>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
