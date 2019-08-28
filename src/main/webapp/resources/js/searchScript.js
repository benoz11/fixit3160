function search() {

    var form = $('#searchForm');
    var searchTerm = form.find('input[name = "searchTerm"]').val().toLocaleUpperCase();


    var table = document.getElementById("ticketTableBody");
    var rows = table.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var data = rows[i].getElementsByTagName("td");
        var foundMatch = false;

        if (searchTerm != null) {
            for (var j = 0; j < data.length; j++) {
                //alert(data[j].innerText);
                if (data[j].textContent.toLocaleUpperCase().includes(searchTerm)) {
                    foundMatch = true;
                    rows[i].style.display = "";
                    break;
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    }
}