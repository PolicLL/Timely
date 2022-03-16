const start = document.querySelector(".startButton");
const stopBtn = document.querySelector(".stopButton");

let projectName;

function stop1()
{
	projectName = prompt("Enter project name : " , "Project");
	location.href = "/stop/" + projectName;
}
