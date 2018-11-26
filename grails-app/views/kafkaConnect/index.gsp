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
            </div>

            <div class="col-xs-12">
                <table class="table table-striped table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th style="max-width:.5rem;">Id</th>
                            <th scope="col">Log messages : </th>
                        </tr>
                    </thead>
                    <tbody class="msg" id="msg">
                    </tbody>
                </table>
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