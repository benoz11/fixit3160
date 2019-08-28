function orderResults(columnIndex) {
    var table = document.getElementById("ticketTableBody");
    var rows = table.rows;
    var direction = "ascending";

    var requiresSort = true;
    var requiresSwap = false;
    var i;
    var inOrderInitially = false;

    // basic bubble sort algorithm
    do {
        requiresSwap = false;
        requiresSort = false;
        for (i = 1; i < rows.length-1; i++) {
            var data = rows[i].getElementsByTagName("td")[columnIndex];
            var previousRowsData = rows[i-1].getElementsByTagName("td")[columnIndex];
            if (data.textContent.toLocaleUpperCase() <= previousRowsData.textContent.toLocaleUpperCase() && direction == "ascending") {
                requiresSwap = true;
                console.log("Trying to swap, ascending order");
            } else if (data.textContent.toLocaleUpperCase() > previousRowsData.textContent.toLocaleUpperCase() && direction == "descending") {
                requiresSwap = true;
                console.log("Trying to swap, descending order");
            }

            if (requiresSwap && direction == "ascending") {
                console.log("Performing a swap for " + i + " and " + (i-1));
                rows[i].parentNode.insertBefore(rows[i], rows[i-1]);
                requiresSort = true;
                inOrderInitially = false;
            }
        }

        if (inOrderInitially) {
            console.log("Already in ascending order, swapping direction");
            requiresSort = true;
            inOrderInitially = true;
            direction = "descending";
        }

    } while (requiresSort);
}
