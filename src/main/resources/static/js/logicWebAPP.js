$(document).ready(function () {


    document.write("Общее разрешение " + screen.width + " " + screen.height);
    document.write(" Идеальный центр "+screen.width / 2 + " " + screen.height / 2);

    document.onmousemove = mousemove;

    function mousemove(event) {
        var mouse_x = 0;
        var mouse_y = 0;

        mouse_x = event.clientX;
        mouse_y = event.clientY;

        x_y_coordinates = {'id':800200600, 'coordinate_x': mouse_x, 'coordinate_y': mouse_y};

        $.ajax({
            type: "GET",
            data: {param: JSON.stringify(x_y_coordinates)},
            url: "/coordinate_x_y",
            success: function (data) {
                // if (data == "error") {
                //
                // }
                // if (data == "success"){
                //
                // }
            }
        });

    }

});