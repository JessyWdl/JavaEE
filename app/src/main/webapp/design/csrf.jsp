<%-- contr�le CSRF --%>
<%@ page import="" %>
<%-- generation du token --%>
<c:set var="csrfToken" value="${ TokenHelper.generateCsrfToken() }" />
<%-- positionnement du token dans la session --%>
<c:set var="_csrfToken" value="${csrfToken}" scope="session" />
<%-- positionnement du token dans le formulaire --%>
<input type="hidden" value="${csrfToken}" name="${ TokenHelper.CSRF_TOKEN_VALUE_NAME }" />