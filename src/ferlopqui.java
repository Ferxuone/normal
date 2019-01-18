import java.util.Scanner;

/** CLASE ferlopqui
*
* Autor: 		Fernando López Quintana
* Fecha: 		2018/12/28
* Versión: 		2.0
* Descripción:	Clase que genera una sopa de letras con 10 palabras introducidas por teclado.
* 
*/

public class ferlopqui {

	/* Nombre: 		boolean validarPalabra (String[] palabras,String palabra)
	 * Entradas:	String[] palabras 	->	Recibe un array que contiene las palabras introducidas. 
	 * 				String palabra		->	Recibe la palabra introducida por el usuario.
	 * Salidas:		boolean				->	Devuelve si la palabra es válida o no para el programa.
	 * Descripción: Método que comprueba los requisitos de admisión de palabras y devuelve si la palabra 
	 * 				introducida es válida para ser almacenada o no. 
	 */
	
	public static boolean validarPalabra (String[] palabras,String palabra) {
		int i;
		
		//Comprobar que solo contiene letras (no contiene numeros)
		for(i=0;i<palabra.length();i++) {
			if((palabra.charAt(i)<'A'||palabra.charAt(i)>'Z')&&(palabra.charAt(i)!='Ñ')) {
				System.out.println("ERROR, la palabra contiene caracteres no permitidos.");
				return false;
			}
		}
		//Comprueba la longitud de la palabra
		if(palabra.length()<3||palabra.length()>15) {
			System.out.println("ERROR, la palabra tiene una longitud no permitida.");
			return false;
		} else {
			//Comprueba que no esté repetida
			for(i=0;i<palabras.length;i++) {
				if(palabra.equals(palabras[i])) {
					System.out.println("ERROR, la palabra está repetida.");
					return false;
				}
			}
		}
		return true;
	}
	
	/* Nombre: 		boolean colocarPalabraAleatorio (char[][] sopaDeLetras, int[][] flags, String palabra)
	 * Entradas:	char[][] sopaDeLetras 	->	Recibe un array bidimensional de char que contiene la sopa de letras. 
	 * 				int[][]  flags			->	Recibe un array bidimensional de enteros que contiene una matriz de 
	 * 											flags que sirven para identificar si la posición está ocupada o no.
	 * 				String   palabra		->	Recibe la palabra a colocar en la sopa de letras.
	 * Salidas:		boolean	 colocada		->	Devuelve si la palabra ha sido colocada o no.
	 * Descripción: Método que comprueba si la palabra recibida se puede colocar en una posición aleatoria y en un sentido 
	 * 				aleatorio introducida es válida para ser almacenada o no. 
	 */
	
	public static boolean colocarPalabraAleatorio (char[][] sopaDeLetras, int[][] flags, String palabra) {
		int sentido = (int) (Math.random()*5+1);
		int f = (int)(Math.random()*14);
		int c = (int)(Math.random()*14);
		int i=0;
		boolean valido = false;
		boolean colocada = false;
		
		switch(sentido) {
		case 1: //HORIZONTAL Izquierda -> Derecha
			//Comprobar que entra en la fila
			if(palabra.length()<=(sopaDeLetras[f].length)-c) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f][c+i]==0)||((flags[f][c+i]==1)&&(sopaDeLetras[f][c+i]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
				
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f][c+i]=palabra.charAt(i);
					flags[f][c+i]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 2: //HORIZONTAL Derecha -> Izquierda
			//Comprobar que entra en la fila
			if(palabra.length()<=c) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f][c-i]==0)||((flags[f][c-i]==1)&&(sopaDeLetras[f][c-i]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
				
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f][c-i]=palabra.charAt(i);
					flags[f][c-i]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 3: //Vertical Arriba -> Abajo
			//Comprobar que entra en la columna
			if(palabra.length()<=(sopaDeLetras.length)-f) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f+i][c]==0)||((flags[f+i][c]==1)&&(sopaDeLetras[f+i][c]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f+i][c]=palabra.charAt(i);
					flags[f+i][c]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 4: //Vertical Abajo -> Arriba
			//Comprobar que entra en la columna
			if(palabra.length()<=f) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f-i][c]==0)||((flags[f-i][c]==1)&&(sopaDeLetras[f-i][c]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
			}else {
				colocada = false;
			}
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f-i][c]=palabra.charAt(i);
					flags[f-i][c]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 5: //Diagonal Izquierda -> Derecha || Arriba -> Abajo
			//Comprobar que entra en la fila y la columna
			if((palabra.length()<=(sopaDeLetras[f].length)-c)&&(palabra.length()<=(sopaDeLetras.length)-f)) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f+i][c+i]==0)||((flags[f+i][c+i]==1)&&(sopaDeLetras[f+i][c+i]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
				
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f+i][c+i]=palabra.charAt(i);
					flags[f+i][c+i]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 6: //Diagonal Izquierda -> Derecha || Abajo -> Arriba
			//Comprobar que entra en la fila y la columna
			if((palabra.length()<=(sopaDeLetras[f].length)-c)&&(palabra.length()<=f)) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f-i][c+i]==0)||((flags[f-i][c+i]==1)&&(sopaDeLetras[f-i][c+i]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
				
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f-i][c+i]=palabra.charAt(i);
					flags[f-i][c+i]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 7: //Diagonal Derecha -> Izquierda || Arriba -> Abajo
			//Comprobar que entra en la fila
			if((palabra.length()<=c)&&(palabra.length()<=(sopaDeLetras.length)-f)) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f+i][c-i]==0)||((flags[f+i][c-i]==1)&&(sopaDeLetras[f+i][c-i]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
				
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f+i][c-i]=palabra.charAt(i);
					flags[f+i][c-i]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		case 8: //Diagonal Derecha -> Izquierda || Abajo -> Arriba
			//Comprobar que entra en la fila
			if((palabra.length()<=c)&&(palabra.length()<=f)) {
				//Comprobar que se puede modificar esos espacios
				do {
					if((flags[f-i][c-i]==0)||((flags[f-i][c-i]==1)&&(sopaDeLetras[f-i][c-i]==palabra.charAt(i)))) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
				
			}else {
				colocada = false;
			}
			
			//Colocar la palabra
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f-i][c-i]=palabra.charAt(i);
					flags[f-i][c-i]=1;
				}
				colocada = true;
			}else {
				colocada = false;
			}
			break;
		default:
			colocada = false;
		}
		return colocada;
	}
	
	/* Nombre: 		void mostrarSopa (char[][] sopaDeLetras, String[] palabras)
	 * Entradas:	char[][] sopaDeLetras 	->	Recibe un array bidimensional de char que contiene la sopa de letras. 
	 * 				String[]   palabras		->	Recibe el vector de las palabras introducidas por el usuario.
	 * Salidas:		void
	 * Descripción: Método que muestra por consola la sopa de letras final y las palabras introducidas. 
	 */
	
	public static void mostrarSopa (char[][] sopaDeLetras, String[] palabras) {
		int i,j;
		
		System.out.println();
		
		for(i=0;i<sopaDeLetras.length;i++) {
			for(j=0;j<sopaDeLetras[0].length;j++) {
				System.out.print(" "+sopaDeLetras[i][j]+" ");
			}
			if(i<10)
				System.out.println("\t\t"+palabras[i]);
			else
				System.out.println();
			
		}
		
		System.out.println();
		
	}
	
	/* Nombre: 		void main (String[] args)
	 * Entradas:	String[] args 	->	Recibe argumentos por línea de comandos. 
	 * Salidas:		void
	 * Descripción: Método que hace una ejecución continua del programa hasta que el usuario desea salir. 
	 */
	
	public static void main(String[] args) {
		char[][] sopaDeLetras = new char[15][15];
		int[][] flags = new int[15][15];
		String[] palabras = new String[10];
		String p1;
		int i,j;
		char temp;
		String salir;
		boolean ok;
		Scanner t = new Scanner(System.in);
		
		do {
			//Instrucciones de uso del programa
			System.out.println("+---------------------------------------------------------+");
			System.out.println("|                  INTRODUCE 10 PALABRAS                  |");
			System.out.println("+---------------------------------------------------------+");
			System.out.println("|  · Puede contener letras desde A hasta Z incluida la Ñ. |");
			System.out.println("|  · Debe tener entre 3 y 15 caracteres.                  |");
			System.out.println("|  · La palabra no puede ser repetida.                    |");
			System.out.println("+---------------------------------------------------------+");
			
			//Crear sopa de letras aleatoria
			for(i=0;i<sopaDeLetras.length;i++) {
				for(j=0;j<sopaDeLetras[0].length;j++) {
					temp = (char)((Math.random()*27)+'A');
					if(temp>'Z')
						sopaDeLetras[i][j] = 'Ñ';
					else
						sopaDeLetras[i][j] = temp;
				}
			}
			
			//Crear matriz de flags
			for(i=0;i<flags.length;i++) {
				for(j=0;j<flags[0].length;j++) {
					flags[i][j] = 0;
				}
			}
			
			//Vector de palabras introducidas
			for(i=0;i<10;i++) {
				do {
					System.out.print("PALABRA "+(i+1)+":");
					p1=t.next();
					p1=p1.toUpperCase();
				}while(validarPalabra(palabras,p1)!=true);
				
				palabras[i]=p1;
			}
			
			//Ordenación del vector de palabras
			String aux; 
	        for(i=1; i<=palabras.length; i++) {  
	            for(j=0; j<palabras.length-i; j++) { 
	                if( palabras[j].length() < palabras[j+1].length() ) { 
	                    aux   = palabras[j]; 
	                    palabras[j]  = palabras[j+1]; 
	                    palabras[j+1]= aux; 
	                }    
	            } 
	        }
			
			//Colocar Palabras una por una
			for(i=0;i<10;i++) {
		        do{
					ok=colocarPalabraAleatorio(sopaDeLetras, flags, palabras[i]);
				}while(ok!=true);
	        }
			
			//Cabecera de la sopa de letras
			System.out.println();
			System.out.println("+---------------------------------------------------------------------+");
			System.out.println("|                           SOPA DE LETRAS                            |");
			System.out.println("+---------------------------------------------------------------------+");
			
			//Mostrar Sopa con las Palabras Colocadas
			mostrarSopa(sopaDeLetras, palabras);
			
			System.out.println();
			do {
				System.out.println("¿Salir (S/N)?");
				salir = t.next();
				salir = salir.toUpperCase();
			}while((salir.charAt(0)!='S')&&(salir.charAt(0)!='N'));
			
		}while(salir.charAt(0)!='S');
		
		//Salida del programa
		System.out.println();
		System.out.println("+---------------------------------------------------------------------+");
		System.out.println("|          GRACIAS POR USAR MI PROGRAMA, AHORA ... !A JUGAR¡          |");
		System.out.println("+---------------------------------------------------------------------+");
		
		t.close();
	}

}
