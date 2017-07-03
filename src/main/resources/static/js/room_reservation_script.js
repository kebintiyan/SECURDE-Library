/**
 * Created by patricktobias on 03/07/2017.
 */

var currentSlot = null;

$(document).ready(function() {

});

$(document).on ("click", ".slot", function () {
    currentSlot = this.id;

    var splitArray = currentSlot.split("-");
    //console.log(currentSlot);

    $("#displayCurrentSlot").html(splitArray[3] + " " + splitArray[1] + " - " + splitArray[2]);
});