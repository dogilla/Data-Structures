/* algoritmo que lee la entrada y nos dice que letras 
del alfabeto no fueron usadas */

var minusculas = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"];

var mayusculas = minusculas.map((x) => {
    return x.toUpperCase();
});

var a = readLine();