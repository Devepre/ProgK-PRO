<html>
<head>
<title>Super poll</title>
</head>
<body>
	<form action="info" method="POST">
		Name: <input type="text" name="name">
		<br>Surname: <input type="text" name="surname">
		<br>Age: <input type="text" name="age">
		
		<br>Please answer the following questions:
		
		<br/>Does multiple inheritance is allowed in Java?
		<br><input type="radio" name="question1" value="1" />Yes
		<br/><input type="radio" name="question1" value="2" />No
		
		<br/>What is SOLID?
		<br><input type="radio" name="question2" value="1" />Firm and stable in shape
		<br><input type="radio" name="question2" value="2" />Not liquid or fluid
		<br/><input type="radio" name="question2" value="3" />Principles of Object Oriented Design
		
		<br><input type="submit" />		
	</form>
</body>
</html>