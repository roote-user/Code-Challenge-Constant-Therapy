<!DOCTYPE HTML>
<html>
	<head>
		<title>Constant Therapy Chess Solution</title>
		<link rel="stylesheet" href="webcontent/style.css">
		<script  src="JS/app.js" type="text/javascript"> async</script>	
	</head>
	<body>
		<h1>Constant Therapy Chess Challenge</h1>		
			<div class="text texton" id="toggle">
				<p> This is my solution to tech challenge provided by Constant Therapy </p>
				<p> In this challenge I was asked to create an HTML page inside of an Apache Tomcat Container or to host an HTML page in a VM on the cloud.</p>
				<p> This page will show an interactable chess board that a user could click two points on. </p>
				<p> A user could select two points on this chessboard, which would cause the RESTful service to:</p>
				<p>
					<ul>
						<li>Send the chosen squares to an endpoint using an AJAX call</li>
						<li>Return either XML or JSON to the client and either list the shortest list of moves to the selected end point or highlight on the chessboard the shortest path to the user</li>
					</ul>
				</p>
				<p>After the server received the client information it would process the shortest number of jumps a knight could take in order to reach that spot on the board.</p>
			</div>
			
			<div class="text" id="Solution">
				<h2> Your Solution is below<h2>
			</div>
		
		<div id="chessboard" >
		
		</div>
	
		<br>
		
		<p class="text" id="solutionout"></p>
		
		<p id="click"> Click on the board in order to begin. </p>
		
		<p id="result"></p>
		
		<button class="text" type="button" onClick="reset()" id="resetButton">Would you like to try again?</button>
		
		
		<script>
			window.onload=boardWrite();
		</script>


	</body>
</html>