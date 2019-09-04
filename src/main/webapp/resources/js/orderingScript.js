/*
 * Class: orderingScript.js
 * Package: fixit3160.resources
 * Project: fixit3160
 *		An IT help ticketing support system developed using Spring
 *
 * SENG3160 University of Newcastle 2019
 *
 * Benjamin McDonnell, Matthew Rudge, Jordan Maddock, Kundayi Sitole
 *
 * Note: some ideas for the logic for this file came from https://www.w3schools.com/howto/howto_js_sort_table.asp;
 *           however, every effort has been made to ensure that it was not copied
 */

function orderResults(columnIndex) {
    var table = document.getElementById("tableBody");
    var rows = table.rows;
    var direction = "ascending";        // if heading is clicked once, sort ascending
    var requiresSort = true;
    var requiresSwap = false;
    var i;
    var inOrderInitially = true;

    // basic bubble sort algorithm
    do {
        requiresSort = false;
        rows = table.rows;
        for (i = 1; i < rows.length; i++) {
            requiresSwap = false;

            var data = rows[i].getElementsByTagName("td")[columnIndex];
            var previousRowsData = rows[i-1].getElementsByTagName("td")[columnIndex];
            if (data.textContent.toLocaleUpperCase() < previousRowsData.textContent.toLocaleUpperCase()
                && direction == "ascending") {      // change all letters to uppercase so that it is case insensitive
                /* if cell above should actually be below when sorting in ascending order*/
                requiresSwap = true;
                break;
            } else if (data.textContent.toLocaleUpperCase() > previousRowsData.textContent.toLocaleUpperCase()
                && direction == "descending") {     // change all letters to uppercase so that it is case insensitive
                /* if cell above should actually be below when sorting in ascending order*/
                requiresSwap = true;
                break;
            }
        }
        if (requiresSwap) {
            rows[i].parentNode.insertBefore(rows[i], rows[i-1]);    // insert cell that should come before current cell before current cell
            requiresSort = true;
            inOrderInitially = false;
        }

        if (inOrderInitially && direction == "ascending") {
            requiresSort = true;
            inOrderInitially = true;
            direction = "descending";   // if they are in ascending order, switch the direction to 'descending'
        }

    } while (requiresSort);
}
