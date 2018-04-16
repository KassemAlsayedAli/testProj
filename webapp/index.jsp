
<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-4.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">

<head>
<title>AJAX in java web application using jQuery</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript" src="ajax.js"></script>
</head>
<body>
<form>
<fieldset>
	<legend>AJAX implementation in JSP and Servlet using JQuery</legend>
	<br /> Enter your Name: <input type="text" id="userName" />
</fieldset>
<br /> <br />
<fieldset>
	<legend>Response from jQuery Ajax Request on Blur event</legend>
	<br />
	<div id="ajaxResponse"></div>
</fieldset>
</form>

<div id="ajaxResponse2"></div>

</body>
</html>