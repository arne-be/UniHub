<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    /* CSS for the centered message */
    .message {
        font-family: 'Montserrat', sans-serif;
        font-size: 21px;
        text-align: center;
        margin-top: 20px;
    }
</style>

<c:if test="${users.isEmpty()}">
    <div class="message">
        <p>You don't follow any users yet...</p>
    </div>
</c:if>

<c:forEach var="u" items="${users}">
    <div id="${u.id}" class="w3-container w3-card w3-section w3-white w3-round w3-animate-opacity"><br>
        <img src="imgs/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
        <h4>${u.username}</h4><br>
        <hr class="w3-clear">
        <button type="button" class="userInfo w3-row w3-button purple-button w3-section"><i class="fa fa-info-circle"></i> &nbsp;Info</button>
        <button type="button" class="unfollowUser w3-row w3-button purple-button w3-section"><i class="fa fa-trash"></i> &nbsp;Unfollow</button>

        <c:if test="${user.getUsertype() == 'Administrator'}">
            <button type="button" class="deleteProfile w3-row w3-button purple-button w3-section"><i class="fa fa-trash"></i> &nbsp;Delete</button>
        </c:if>
    </div>
</c:forEach>
