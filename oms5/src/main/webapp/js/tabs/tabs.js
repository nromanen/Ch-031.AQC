/**
 * JavaScript file contains functions that asks user before logout
 */
$(document).ready(function() {

	$("#logout").click(
			function logout() {
				var url = "logout.htm";
				var OK = confirm($("#logoutMsg").text());
				if (OK) {
					window.location = url;
				}
			}
	);
});