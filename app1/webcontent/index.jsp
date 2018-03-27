<!DOCTYPE HTML>
<html>
	<head>
		<title>Constant Therapy Chess Solution</title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<h1>Constant Therapy Chess Challenge</h1>
	
		<div class="text">
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
	
	
			<script>
					var columns = ["A", "B", "C", "D", "E", "F", "G", "H"];
					var rows = ["1", "2", "3", "4", "5", "6", "7", "8"];
					var size = 8;
					
					var parent = document.createElement("div");
					parent.id = "chessboard";
					document.body.appendChild(parent);
	
					for(j=0; j<size; j++){
						for(i = 0; i < size; i++){
							if( i % 2 == j % 2){
								var Check = document.createElement("div");
								Check.className = 'whitecheck';
								Check.title = columns[j] + rows[i];
								Check.setAttribute("onClick", "GridClick(this)");
								parent.appendChild(Check);
							}else{
								var Check = document.createElement("div");
								Check.className = 'blackcheck';
								Check.title = columns[j] + rows[i];								
								Check.setAttribute("onClick", "GridClick(this)");
								parent.appendChild(Check);
								}
							
						}
					}
			</script>
			
		<br>
		
		<p id="click"> You have not clicked the box </p>
		
		<p id="result"></p>
		
		<script src="	JS/app.js" type="text/javascript"></script>
	</body>
</html>