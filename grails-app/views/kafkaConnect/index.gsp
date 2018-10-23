<%@ page import="me.nvorotnikov.grailstest.KafkaConnect" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>KafkaConsumer</title>
</head>

<body>

<div class="container">
        <h1>Kafka Consumer</h1>
        <div class="row">
            <div class="col-xs-12">
                <a class="btn btn-default" href="<g:createLink action="runConsumer" />">Start cons</a>
                <a class="btn btn-default" href="<g:createLink action="retrieveLogMessages" />">Get list</a>
                <a class="btn btn-default" href="<g:createLink action="write" />">write</a>
            </div>

            <div class="col-xs-12">
                <p>
                    <span style="font-weight: 900;">Log message : </span>
                    <span class="msg">
                        <g:each in="${messages}" var="msg">
                            <div>
                                <span class="mess">${msg.logMessage}</span>
                            </div>
                        </g:each>
                    </span>
                </p>
            </div>
        </div>
    </div>

    <script>
        %{--function run() {--}%
            %{--console.log("run");--}%
            %{--<g:remoteFunction action="runConsumer" />--}%
        %{--}--}%

        %{--function retrieveLogMessages() {--}%
            %{--console.log("msg");--}%
            %{--<g:remoteFunction action="retrieveLogMessages" update="msg"/>--}%
        %{--}--}%

        %{--function stop () {--}%
            %{--<g:remoteFunction action="write" />--}%
        %{--}--}%
    </script>
</body>
</html>