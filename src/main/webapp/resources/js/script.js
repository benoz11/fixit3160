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

/*
 * Hiding and showing Comment related buttons on the ticket page
 */

var oldval;
function editComment(commentid) {
	window.oldval = document.getElementById("commentcontents"+commentid).value;
	document.getElementById("commentcontents"+commentid).readOnly = false;
	document.getElementById("canceleditbutton"+commentid).hidden= false;
	document.getElementById("submiteditbutton"+commentid).hidden= false;
	
	document.getElementsByName("editbutton").forEach(function(obj) {obj.hidden = true;});	
}

function cancelEditComment(commentid) {
	document.getElementById("commentcontents"+commentid).value = window.oldval;
	document.getElementsByName("commentcontents").forEach(function(obj) {obj.readOnly = true;});
	document.getElementsByName("canceleditbutton").forEach(function(obj) {obj.hidden = true;});
	document.getElementsByName("submiteditbutton").forEach(function(obj) {obj.hidden = true;});
	document.getElementsByName("editbutton").forEach(function(obj) {obj.hidden = false;});
}

function submitCommentEdit(commentid) {
	document.getElementById("submitcommenteditform"+commentid).submit();
}

/*
 *	The ticketbuttons has just the one form wrapped around the section
 *	Javascript is used to change the mapping for the form based on the button clicked
 */

$('button[name="deleteTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/delete');
	$('#buttonForm').submit();
});
$('button[name="closeTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/close');
	$('#buttonForm').submit();
});
$('button[name="assignTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/assign');
	$('#buttonForm').submit();
});
$('button[name="editTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/edit');
	$('#buttonForm').submit();
});
$('button[name="completeTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/complete');
	$('#buttonForm').submit();
});
$('button[name="rejectTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/reject');
	$('#buttonForm').submit();
});
$('button[name="kbTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/kb');
	$('#buttonForm').submit();
});
$('button[name="openTicketButton"]').click(function(){
	$('#buttonForm').attr('action', window.location.pathname + '/open');
	$('#buttonForm').submit();
});

