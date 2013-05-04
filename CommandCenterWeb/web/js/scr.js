$(document).ready(function(){
                $('#agentDiv').load("agents.jsp");
                $('#refrButton').click(function(){
                    refrAgents();
                });
                setInterval(function() {
                    $('#agentDiv').load("agents.jsp");
                }, 5000);
                $('#taskForm').ajaxForm(function() {
                    alert("Задание отправлено!");
                });
            });
            function refrAgents(){
                $.ajax({
                    type: "GET",
                    url: "GetAgents"
                }).done(function() {
                    $('#agentDiv').load("agents.jsp");
                });
            }
            