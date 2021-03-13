let socket = new WebSocket("ws://localhost:8080/P3WebSocket-1.0-SNAPSHOT/echo");

function wsSend(voteCandidateId) {
    socket.send(voteCandidateId)
}

function wsClose() {
    socket.close();
}

socket.onmessage = function (ev) {
    let split = ev.data.split(",");

    ana.setQVotes(split[0]);
    alejandro.setQVotes(split[1]);
    antonia.setQVotes(split[2]);
    alexandra.setQVotes(split[3]);

    graph();
}