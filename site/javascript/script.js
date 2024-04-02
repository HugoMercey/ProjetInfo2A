function draw(){
    var canvas = document.getElementById('Bezier');
    var ctx = canvas.getContext('2d');
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    var point1x = document.getElementById('point1x');
    var point1y = document.getElementById('point1y');
    var point2x = document.getElementById('point2x');
    var point2y = document.getElementById('point2y');
    var point3x = document.getElementById('point3x');
    var point3y = document.getElementById('point3y');
    var point4x = document.getElementById('point4x');
    var point4y = document.getElementById('point4y');
    

    ctx.strokeStyle = "red";  
    ctx.lineWidth = 5;
    ctx.setLineDash([]);
    ctx.beginPath();
        ctx.moveTo(400, 10);
        ctx.lineTo(600, 10);

        ctx.bezierCurveTo(point1x.value, point1y.value, point2x.value, point2y.value, 600, 500);

        ctx.lineTo(400, 500);

        ctx.bezierCurveTo(point3x.value, point3y.value, point4x.value, point3y.value, 400, 10);
        ctx.stroke();
    ctx.closePath();


    ctx.strokeStyle = "black"; 
    ctx.lineWidth = 1;
    ctx.setLineDash([10, 20]);
    ctx.beginPath();
        ctx.arc(400, 10, 3, 0, 2 * Math.PI);
        ctx.fill();
    ctx.closePath();

    ctx.beginPath();
        ctx.arc(600, 10, 3, 0, 2 * Math.PI);
        ctx.fill();
    ctx.closePath();

    ctx.beginPath();
        ctx.font = "20px serif";
        ctx.fillText('1', point1x.value, point1y.value);
    ctx.closePath();

    ctx.beginPath();
        ctx.font = "20px serif";
        ctx.fillText('2', point2x.value, point2y.value);
    ctx.closePath();

    ctx.beginPath();
        ctx.arc(600, 500, 3, 0, 2 * Math.PI);
        ctx.fill();
    ctx.closePath();

    ctx.beginPath();
        ctx.arc(400, 500, 3, 0, 2 * Math.PI);
        ctx.fill();
    ctx.closePath();
            
    ctx.beginPath();
        ctx.font = "20px serif";
        ctx.fillText('3', point3x.value, point3y.value);
    ctx.closePath();

    ctx.beginPath();
        ctx.font = "20px serif";
        ctx.fillText('4', point4x.value, point4y.value);
    ctx.closePath();


    ctx.setLineDash([10, 10]);
    ctx.lineWidth = 1;
    ctx.beginPath();
        ctx.moveTo(400, 10);
        ctx.lineTo(600, 10);
        ctx.lineTo(point1x.value, point1y.value);
        ctx.lineTo(point2x.value, point2y.value);
        ctx.lineTo(600, 500);
        ctx.lineTo(400, 500);
        ctx.lineTo(point3x.value, point3y.value);
        ctx.lineTo(point4x.value, point4y.value);
    ctx.closePath();
    ctx.stroke();
}

function reset(){
    var point1x = document.getElementById('point1x');
    var point1y = document.getElementById('point1y');
    var point2x = document.getElementById('point2x');
    var point2y = document.getElementById('point2y');
    var point3x = document.getElementById('point3x');
    var point3y = document.getElementById('point3y');
    var point4x = document.getElementById('point4x');
    var point4y = document.getElementById('point4y');

    point1x.value = 400;
    point2x.value = 700;
    point3x.value = 300;
    point4x.value = 600;

    point1y.value = 110;
    point2y.value = 250;
    point3y.value = 250;
    point4y.value = 110;

    draw();
}

var point1x = document.getElementById("point1x");
var point2x = document.getElementById("point2x");
var point3x = document.getElementById("point3x");
var point4x = document.getElementById("point4x");

var point1y = document.getElementById("point1y");
var point2y = document.getElementById("point2y");
var point3y = document.getElementById("point3y");
var point4y = document.getElementById("point4y");


point1x.addEventListener("input", function() {
    draw();
}, false); 


point2x.addEventListener("input", function() {
    draw();
}, false); 

point3x.addEventListener("input", function() {
    draw();
}, false); 

point4x.addEventListener("input", function() {
    draw();
}, false); 



point1y.addEventListener("input", function() {
    draw();
}, false); 

point2y.addEventListener("input", function() {
    draw();
}, false); 

point3y.addEventListener("input", function() {
    draw();
}, false); 

point4y.addEventListener("input", function() {
    draw();
}, false); 