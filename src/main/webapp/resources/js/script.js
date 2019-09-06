/*
 * Class: script.js
 *
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 */

/*
 * If we are on the users, tickets, or articles list page
 * When we click on something of the class 'clickable-row', direct us to the url defined in its data-href value
 * Usage: <tr class="clickable-row" data-href="/users/${user.id}"><td>click me</td></tr>
 */
if(location.pathname.match(/\/users/) ||  location.pathname.match(/\/tickets/) || location.pathname.match(/\/knowledgeBase/)) {
	$(document).ready(function($) {
	    $(".clickable-row").click(function() {
	        window.document.location.href = $(this).data("href"); // take us to the data-href attached to this object
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
 *	The ticketbuttons has one form wrapped around the section
 *	Javascript is used to change the mapping for the form based on the button clicked
 */
var actionpath = window.location.pathname;
if (actionpath.endsWith("/")) {actionpath = actionpath.slice(0,-1);}

$('button[name="deleteTicketButton"]').click(function(){
	if(confirm("Are you sure?")) {
		$('#buttonForm').attr('action', actionpath + '/delete');
		$('#buttonForm').submit();
	}
});
$('button[name="closeTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/close');
	$('#buttonForm').submit();
});
$('button[name="assignTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/assign');
	$('#buttonForm').submit();
});
$('button[name="editTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/edit');
	$('#buttonForm').submit();
});
$('button[name="completeTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/complete');
	$('#buttonForm').submit();
});
$('button[name="rejectTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/reject');
	$('#buttonForm').submit();
});
$('button[name="kbTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/kb');
	$('#buttonForm').submit();
});
$('button[name="openTicketButton"]').click(function(){
	$('#buttonForm').attr('action', actionpath + '/open');
	$('#buttonForm').submit();
});

