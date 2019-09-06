/*
 * Class: searchScript.js
 *
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 * Note: ideas for the logic for this file came from https://www.w3schools.com/howto/howto_js_sort_table.asp;
 *           however, effort has been made to ensure that it was not just copied
 */

function search() {

    var form = $('#searchForm');
    var searchTerm = form.find('input[name = "searchTerm"]').val().toLocaleUpperCase(); // convert search term to capitals
    var table = document.getElementById("tableBody");
    var rows = table.getElementsByTagName("tr");    // get table row

    for (var i = 0; i < rows.length; i++) {
        var data = rows[i].getElementsByTagName("td");
        var foundMatch = false;
        if (searchTerm != null) {   // if user has entered something into the search box
            for (var j = 0; j < data.length; j++) {
                if (data[j].textContent.toLocaleUpperCase().includes(searchTerm)) { // search each table cell in row to see if search term present
                    foundMatch = true;
                    rows[i].style.display = ""; // if search term found, display it
                    break;
                } else {
                    rows[i].style.display = "none"; // if search term is not found, hide the row
                }
            }
        }
    }
}