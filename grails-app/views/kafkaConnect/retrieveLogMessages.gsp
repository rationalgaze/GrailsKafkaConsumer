<%@ page import="me.nvorotnikov.grailstest.KafkaConnect" %>

<g:each in="${messages}" var="msg">
    <tr>
        <td style="max-width: .5rem;">${msg.id}</td>
        <td>${msg.logMessage}</td>
    </tr>
</g:each>
%{--<span class="mess">${msg.logMessage}</span>--}%
