/**
 * Created by avggo on 7/2/2017.
 */

$(document).ready(function(){
    var filters = $('input:checkbox:checked').map(function () {
        return this.value;
    }).get();

    $.ajax({
        type : "POST",
        url : "/search",
        data : {
            filters: filters //notice that "myArray" matches the value for @RequestParam
                       //on the Java side
        },
        success : function(response) {
            alert('success!');
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    });
});