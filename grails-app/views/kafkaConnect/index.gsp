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
            <nav class="navbar ">
                <div class="container-fluid">
                    <g:link class="btn btn-default" action="runConsumer">Start cons</g:link>
                    <g:link class="btn btn-info" action="exportLogToTxt">To TXT</g:link>
                </div>
            </nav>

            <div class="col-xs-12">
                <div class="alerts">
                    <g:if test="${flash.success}">
                        <div class="alert alert-success alert-dismissible show" role="alert">
                            ${flash.success}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </g:if>
                    <g:if test="${flash.error}">
                        <div class="alert alert-error alert-dismissible show" role="alert">
                            <strong>Holy guacamole!</strong> ${flash.error}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </g:if>
                </div>
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
        function retrieveLogMessages() {
            <g:remoteFunction action="retrieveLogMessages" update="msg"/>
        }

        function pollMessages() {
            retrieveLogMessages();
            setTimeout('pollMessages()', 2000);
        }
        pollMessages();
    </script>
</body>
</html>