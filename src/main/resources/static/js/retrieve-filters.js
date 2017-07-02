/**
 * Created by avggo on 7/2/2017.
 */

$(document).ready(function(){
    $("#search-form").submit(function () {
        retrieveFilters();
    })
});

function retrieveFilters() {
    var filters = $('input:checkbox:checked').map(function () {
        alert("tick");
        return this.value;
    }).get();

    $.ajax({
        type : "POST",
        url : "/search",
        data : {
            filters: filters
        },
        success : function(response) {
            alert(filters);
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    });
}
