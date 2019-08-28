function orderResults(columnIndex) {
    var table = document.getElementById("tableBody");
    var rows = table.rows;
    var direction = "ascending";

    console.log("Sorting column " + columnIndex);

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
            if (data.textContent.toLocaleUpperCase() < previousRowsData.textContent.toLocaleUpperCase() && direction == "ascending") {
                requiresSwap = true;
                break;
            } else if (data.textContent.toLocaleUpperCase() > previousRowsData.textContent.toLocaleUpperCase() && direction == "descending") {
                requiresSwap = true;
                break;
            }
        }
        if (requiresSwap) {
            rows[i].parentNode.insertBefore(rows[i], rows[i-1]);
            requiresSort = true;
            inOrderInitially = false;
        }

        if (inOrderInitially && direction == "ascending") {
            requiresSort = true;
            inOrderInitially = true;
            direction = "descending";
        }

    } while (requiresSort);
}
