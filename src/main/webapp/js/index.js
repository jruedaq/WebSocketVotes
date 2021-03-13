let ana = new Candidate("Ana", "#7868e6");
let alejandro = new Candidate("Alejandro", "#1687a7");
let antonia = new Candidate("Antonia", "#f25287");
let alexandra = new Candidate("Alexandra", "#f2a154");

let votos = [0, 0, 0, 0];
function vote() {
  let votes = parseInt(document.getElementById("candidates").value);

  switch (votes) {
    case 0:
      ana.addQVotes();
      votos[0] = votos[0] + 1;
      break;
    case 1:
      alejandro.addQVotes();
      votos[1] = votos[1] + 1;

      break;
    case 2:
      antonia.addQVotes();
      votos[2] = votos[2] + 1;

      break;
    case 3:
      alexandra.addQVotes();
      votos[3] = votos[3] + 1;

      break;
  }
  graph();
  wsSend(votes);
}

function graph() {
  let canvas = document.getElementById("myCanvas");
  let context = canvas.getContext("2d");

  context.beginPath();
  context.fillStyle = "#FFFFFF";
  context.fillRect(0, 0, 800, 400);
  context.stroke();

  let totalVotes =
    ana.getQVotes() +
    alejandro.getQVotes() +
    antonia.getQVotes() +
    alexandra.getQVotes();

  for (let i = 0; i < 4; i++) {
    let obj =
      i === 0
        ? ana
        : i === 1
        ? alejandro
        : i === 2
        ? antonia
        : i === 3
        ? alexandra
        : null;

    let y = (obj.getQVotes() / totalVotes) * 400;

    console.log(obj.getName() + " " + y);

    context.beginPath();
    context.font = "20px Arial";
    context.fillStyle = obj.getColor();
    context.fillRect(
      i === 0 ? 10 : i === 1 ? 210 : i === 2 ? 410 : i === 3 ? 610 : null,
      300,
      180,
      y * -1
    );
    context.fillText(
      obj.getName(),
      i === 0 ? 80 : i === 1 ? 260 : i === 2 ? 460 : i === 3 ? 650 : null,
      320
    );
    context.font = "16px Arial";
    context.fillText(
        "Votos: " + obj.getQVotes(),
        i === 0 ? 70 : i === 1 ? 270 : i === 2 ? 460 : i === 3 ? 660 : null,
        350
      );
    context.font = "14px Arial";
    context.fillText(
      "Porcentaje: " + ((obj.getQVotes()/totalVotes)*100).toFixed(2) + "%",
      i === 0 ? 40 : i === 1 ? 240 : i === 2 ? 440 : i === 3 ? 640 : null,
      380
    );
    context.stroke();
  }
}

