<%@ page import="me.nvorotnikov.grailstest.KafkaConnect" %>
<g:each in="${messages}" var="msg">
    <div>
        <span class="mess">${msg.logMessage}</span>
    </div>
</g:each>