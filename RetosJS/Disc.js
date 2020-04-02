/* Input : 5 lineas de desigualdades <
   Output: La solucion si es posible dichas desigualdades
   o "Imposible" si no se puede*/


const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Inserta discos \n", function(d1){
    var desigualdades = d1.trim().split(",");
    console.log(desigualdades);
    rl.close();
});