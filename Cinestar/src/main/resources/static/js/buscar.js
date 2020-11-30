const buscar = document.getElementById('buscar-pelicula');



const peliculas = [
	{pelicula: 'Joker' , id: 1},
	{pelicula: 'Maléfica' , id: 2},
	{pelicula: 'Avengers: Endgame' , id: 3},
	{pelicula: 'Glass', id: 4},
	{pelicula: 'Capitana Marvel', id: 5},
	{pelicula: 'Pokemon: Detective Pikachu', id: 6},
	{pelicula: 'Muñeco Diabólico', id: 7},
	{pelicula: 'Dumbo', id: 8},
	{pelicula: 'Feliz dia de tu muerte 2', id: 9},
	{pelicula: 'Alita: Ángel de combate', id: 10}
]


buscar.addEventListener('keyup', (e) => {
	//Quitar propiedad hidden cuando se digita
	comparar(buscar.value)
})





function comparar(pelicula_buscada){
    let tamanio = pelicula_buscada.length
    var peliculas_encontradas = []

    console.log(tamanio)
    
    if(tamanio == 0 ){
    	remover_hidden()
    }
    
    for(const indice in peliculas){
    	let peli_en_lista = peliculas[indice].pelicula
    	let str = peli_en_lista.substring(0, tamanio)
    	if(pelicula_buscada.length <= peli_en_lista.length && pelicula_buscada.length != 0 &&  peli_en_lista.length != 0){
    		if(pelicula_buscada.toLowerCase() == str.toLowerCase()){
    			peliculas_encontradas.push(parseInt(indice) + 1)
    		}
    	}
    }
	console.log(peliculas_encontradas)
	ocultar(peliculas_encontradas)
	
}

function ocultar(peliculas_encontradas){		
	//Ocultar los no coincidentes
	 let lista_limpia = limpiar_lista(peliculas_encontradas)
	 
	 for(let indice in lista_limpia){
		let ocultar_peli = document.getElementById(lista_limpia[indice])
		ocultar_peli.setAttribute("hidden", true)
	}
	 
}



//Retorna todos los elementos no coincidentes con la busqueda
function limpiar_lista(peliculas_encontradas){
	let lista_limpia = []
	
	for(let indice in peliculas){
		lista_limpia.push(peliculas[indice].id)
	}

	for(let ind in peliculas_encontradas){
		let indice = lista_limpia.indexOf(peliculas_encontradas[ind])
		lista_limpia.splice(indice, 1)
	}
	
	return lista_limpia
}


function remover_hidden(){
	for(let indice in peliculas){
		let doc = document.getElementById(peliculas[indice].id)
		//console.log(doc.hasAttribute("hidden"))
		doc.removeAttribute("hidden")
		console.log(doc.hasAttribute("hidden"))
		location.reload(); 
	}
}
