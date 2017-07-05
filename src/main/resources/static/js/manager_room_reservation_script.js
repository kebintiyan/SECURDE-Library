/**
 * Created by patricktobias on 03/07/2017.
 */

var currentSlot = null;

$(document).on ("click", ".reserved", function () {
    currentSlot = this.id;

    var splitArray = currentSlot.split("-");
    //console.log(currentSlot);

    $("#displayCurrentSlot").html(splitArray[3] + " " + splitArray[1] + " - " + splitArray[2]);

    var currentAction = splitArray[0] + "-" + splitArray[1] + "-" + splitArray[2];

    //$("#reserve-form").attr("action", currentAction);
    $("#msg").attr("value", currentAction);

});
