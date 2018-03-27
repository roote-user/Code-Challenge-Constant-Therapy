
var path = [];
var solution;
var textInt = null;

var columns = ["A", "B", "C", "D", "E", "F", "G", "H"];
var rows = ["1", "2", "3", "4", "5", "6", "7", "8"];
var size = 8;

var divList = document.getElementsByClassName("text");
var targetDiv = divList[0];

var boardWrite = function(){
	
		var parent = document.getElementById("chessboard");
		
		for(j=0; j<size; j++){
			for(i = 0; i < size; i++){
				if( i % 2 == j % 2){
					var Check = document.createElement("div");
					Check.className = 'whitecheck';
					Check.title = columns[i] + rows[j];
					Check.setAttribute("onClick", "GridClick(this)");
					parent.appendChild(Check);
				}else{
					var Check = document.createElement("div");
					Check.className = 'blackcheck';
					Check.title = columns[i] + rows[j];								
					Check.setAttribute("onClick", "GridClick(this)");
					parent.appendChild(Check);
									}
							}
						}
					};
					
var GridClick = function(square){
		var Select = document.querySelector('#click');
		var Result = document.querySelector('#result');

		if(path.length == 0){
			Select.textContent= 'You have selected Grid ' + square.title + ' as your starting point.';
			path.push(square.title);
			square.className = square.className + 'highlight';
			console.log(square.className + 'highlight');
		}else if(path.length == 1){
			Select.textContent= 'You have selected Grid ' + square.title + ' as your end point.'
			path.push(square.title);
			Result.textContent = path[0] + " and " + path[1] + " have been accepted as input";
			square.className = square.className + 'highlight';
			console.log(square.className + 'highlight');
			sendData(path);
			path = [];

		}
	};
	

	
var sendData = function(squares){
	var requestString = "start=" + squares[0] + "&end=" + squares[1];
	console.log(requestString);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			cFunction(solution, this);
			
		}
	};
	xhttp.open("POST", "jaunt" ,true);
	xhttp.requestType = 'json';
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(requestString);
};

async function cFunction(output,xhttp){
	output = JSON.parse(xhttp.responseText);
	console.log(output);
	 await completesChallenge(output);
}

function completesChallenge(input){
	var divList = document.getElementById("toggle");
	var button = document.getElementById("resetButton");
	var inputList = input;
	hide(divList);
	divList = document.getElementById("Solution");
	show(divList)
	var paragraph = document.getElementById("solutionout");
	if(inputList != null){
		var solutionString = "Starting from " + inputList.moves[0] + " your knight goes to square ";
	
		if(inputList.moves.length < 3){
			solutionString += inputList.moves[1] + " completing its move.";
			paragraph.innerHTML = solutionString;
		}
		
		if(inputList.moves.length == 3){
			solutionString  += inputList.moves[1] + " and finally ends at your destination, square " + inputList.moves[2];
			paragraph.innerHTML = solutionString;
		}

		if(inputList.moves.length > 3){
			for( i = 1; i < inputList.moves.length - 1; i++){
				if(i < inputList.moves.length - 2){
				solutionString += inputList.moves[i] + " and then to square ";
				}else{solutionString += inputList.moves[i];
				}
				
			}
			solutionString  += " and finally ends at your destination, square " + inputList.moves[i];
			paragraph.innerHTML = solutionString;
		}
			show(paragraph);
			show(button);
		
	}else{
		paragraph.innerHTML = "There seems to have been an error processing your request.";
		show(paragraph);
		show(button);
	}
}

function reset(){
	console.log("Reseting");
	var divList = document.getElementById("Solution");
	var button = document.getElementById("resetButton");
	hide(divList);
	hide(button);
	divList = document.getElementById("toggle");
	//show(divList);
	var solutionOut = document.getElementById("solutionout");
	hide(solutionOut);
	var Select = document.querySelector('#click');
	var Result = document.querySelector('#result');
	Select.textContent= "Click on the board in order to begin.";
	Result.textContent= "";
	document.querySelector(".whitecheckhighlight").className = "whitecheck";
	document.querySelector(".blackcheckhighlight").className = "blackcheck";
};
	
var toggle = function () {
	var divList = document.getElementsByClassName("text");
	var targetDiv = divList[0];
	console.log(targetDiv.classList);
	console.log(targetDiv);
	
	if (targetDiv.classList.contains("texton")) {
		console.log("Hiding");
		hide(targetDiv);
		return;
	}

	// Otherwise, show it
	console.log("Show");
	show(targetDiv);
	
};

var show = function (targetDiv) {

	// Get the natural height of the element
	var getHeight = function () {
		targetDiv.style.display = 'block';
		var height = targetDiv.scrollHeight + 'px';
		targetDiv.style.display = '';
		return height;
	};

	var height = getHeight();
	targetDiv.classList.add("texton");
	targetDiv.style.height = height;

	// Once the transition is complete, remove the inline max-height so the content can scale responsively
	window.setTimeout(function () {
		targetDiv.style.height = '';
	}, 1000);

};

// Hide an element
var hide = function (targetDiv) {

	// Give the element a height to change from
	targetDiv.style.height = targetDiv.scrollHeight + 'px';

	// Set the height back to 0
	window.setTimeout(function () {
		targetDiv.style.height = '0';
	}, 1);

	// When the transition is complete, hide it
	window.setTimeout(function () {
		targetDiv.classList.remove("texton");
		console.log("Removed extra class");
	}, 1000);

};