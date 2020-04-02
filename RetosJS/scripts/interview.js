/* enlaces */
let enlace = function(){
    var v = 7;
    let l = 10;
    if(true){
        console.log(v); //imprime 7 por el enlace dinamico
        var v = 2;
        let l = 1;
        console.log(l); //imprime 1 por el enlace estatico
        console.log(v); //imprime 2 por el enlace estatico
    }
    console.log(l); //imprime 10 por el enlace dinamico
}

let equal = function(){
    console.log('1' == 1); // true porque tienen mismo valor
    console.log('1' === 1); // false porque no tienen mismo tipo
}

const profile = {
    firstName: '',
    lastName: '',
    setName: function(name){
        let splitName = (n) => {
            nameArray = n.split(' ')
            this.firstName = nameArray[0];
            this.lastName = nameArray[1];
        }
        splitName(name);
    }
}

enlace();
equal();
profile.setName('Mario Guzman');
console.log(profile.firstName);