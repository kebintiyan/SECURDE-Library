/**
 * Created by patricktobias on 03/07/2017.
 */

var currentSlot = null;

/*$(document).ready(function() {
    $("#reserve-btn").click(function () {
        console.log("RESERVE BTN CLICK");



        $.ajax({
            url : "rooms/reserve",
            type : "GET",
            dataType: "json",
            data : {
                chosenSlot: currentSlot
            }

        })
            .done(function(result) {
                console.log("success");

                console.log(result);
            })
            .fail(function( jqXHR, textStatus, errorThrown ) {
                console.log(jqXHR.responseText + " " + textStatus + " " + errorThrown);
            });

    });
});
*/

$(document).on ("click", ".slot", function () {
    currentSlot = this.id;

    var splitArray = currentSlot.split("-");
    //console.log(currentSlot);

    $("#displayCurrentSlot").html(splitArray[3] + " " + splitArray[1] + " - " + splitArray[2]);

    var currentAction = splitArray[0] + "-" + splitArray[1] + "-" + splitArray[2];

    //$("#reserve-form").attr("action", currentAction);
    $("#msg").attr("value", currentAction);

});
