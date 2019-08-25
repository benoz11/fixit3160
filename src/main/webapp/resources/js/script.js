$(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.document.location.href = $(this).data("href");
    });
});