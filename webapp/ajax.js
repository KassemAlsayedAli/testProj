$(document).ready(function() {
	$('#userName').blur(function(event) {
		var name = $('#userName').val();
		$('body').css('cursor', 'progress');

		$.get('JqueryServlet', {
			userName : name
		}, function(responseText) {
			$('body').css('cursor', 'auto');

			$('#ajaxResponse').text(responseText);
			$('#ajaxResponse2').html(responseText);

		});
	});
});
