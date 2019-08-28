/*
 * General scripts for FixIT3160
 * Benjamin McDonnell
 */

/*
 * If we are on the users, tickets, or articles list page
 * When we click on something of class 'clickable-row', direct us to the url defined in its data-href value
 * Usage: <tr class="clickable-row" data-href="/users/${user.id}"><td>click me</td></tr>
 */
if(location.pathname.match(/\/users/) ||  location.pathname.match(/\/tickets/) || location.pathname.match(/\/articles/)) {
	$(document).ready(function($) {
	    $(".clickable-row").click(function() { //
	        window.document.location.href = $(this).data("href"); //take us to the data-href attached to this object
	    });
	});
}

/*
 * If we are on the user editing page, check the radio button relating to the current user role
 * See edituser.jsp for usage
 */
if(location.pathname.match(/\/users\/\d+\/edit/)) {
	//Check the radio button for the current role on the edit user page
	var role = document.getElementById("originalrole").value;
	document.getElementsByName("role").forEach(checkRoleRadio);

	function checkRoleRadio(item) {
		if (item.value === role) {
			item.checked = true;
		}
	}
}
