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
                <a class="btn btn-default" onclick="start()">Start cons</a>
                <a class="btn btn-info" onclick="toTxt()">To TXT</a>
                %{--<g:link class="list" action="exportLogToTxt">Document List</g:link>--}%
                %{--<a class="btn btn-danger" onclick="stop()">Stop cons</a>--}%
            </div>

            <div class="col-xs-12">
                <p>
                    <span style="font-weight: 900;">Log message : </span>
                    <span class="msg" id="msg">
                    </span>
                </p>
            </div>
        </div>
    </div>

    <script>
        function start() {
            <g:remoteFunction action="runConsumer"/>
        }

        function toTxt() {
            <g:remoteFunction action="exportLogToTxt"/>
        }

        %{--function stop() {--}%
            %{--<g:remoteFunction action="stopConsumer"/>--}%
        %{--}--}%

        function retrieveLogMessages() {
            <g:remoteFunction action="retrieveLogMessages" update="msg"/>
        }

        function pollMessages() {
            retrieveLogMessages();
            setTimeout('pollMessages()', 500);
        }
        pollMessages();
    </script>
</body>
</html>