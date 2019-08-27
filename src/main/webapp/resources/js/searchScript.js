function search() {
    alert("saealdlawd");
    var form = $('#searchForm');
    var searchTerm = form.find('input[name = "searchTerm"]').val();

    var table = document.getElementById("ticketTableBody");
    var rows = table.getElementsByTagName("tr");


    for (var i = 0; i < rows.length; i++) {
        var data = rows[i].getElementsByTagName("td");

        var foundMatch = false;

        if (searchTerm != null) {
            for (var j = 0; j < data.length; j++) {
                if (data[j].contains(searchTerm)) {
                    foundMatch = true;
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    }
}
