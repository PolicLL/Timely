const start = document.querySelector(".startButton");
const stopBtn = document.querySelector(".stopButton");

let projectName;

function stopProject()
{
	projectName = prompt("Enter project name : " , "Project");
	location.href = "/stop/" + projectName;
}
