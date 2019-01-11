import java.util.Scanner;

public class juego {

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
	
	public static boolean colocarPalabraAleatorio (char[][] sopaDeLetras, int[][] flags, String palabra) {
		int tipo;
		int f,c;
		int i=0;
		boolean valido=false;
		
		tipo = (int) (Math.random()*2+1);
		System.out.println(tipo);
		
		if(tipo==1) {  //Horizontal
			f=(int)(Math.random()*15);
			c=(int)(Math.random()*15);
			System.out.println("HORIZONTAL FILA: "+f+" COLUMNA: "+c);
			//Comprobar que entra en la fila
			if(palabra.length()<=(sopaDeLetras[f].length)-c) {
				//Comprobar que se puede modificar esos espacios
				do {
					if(flags[f][c+i]==0) {
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
			}else {
				return false;
			}
			
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f][c+i]=palabra.charAt(i);
					flags[f][c+i]=1;
				}
				return true;
			}else {
				return false;
			}
		}else { //Vertical
			f=(int)(Math.random()*15);
			c=(int)(Math.random()*15);
			System.out.println("VERTICAL FILA: "+(f+1)+" COLUMNA: "+(c+1));
			//Comprobar que entra en la fila
			if(palabra.length()<=(sopaDeLetras.length)-f) {
				//Comprobar que se puede modificar esos espacios
				do {
					if(flags[f+i][c]==0) {	//Modificar que pueda ser ==1 y entonces ver si es =.charAt(i)
						valido = true;
					} else {
						valido = false;
					}
					i++;
				}while((valido!=false)&&(i<(palabra.length())));
			}else {
				return false;
			}
			if(valido==true) {
				for(i=0;i<palabra.length();i++) {
					sopaDeLetras[f+i][c]=palabra.charAt(i);
					flags[f+i][c]=1;
				}
				return true;
			}else {
				return false;
			}
		}
	}
	
	public static void mostrarSopa (char[][] sopaDeLetras) {
		int i,j;
		
		System.out.println();
		
		for(i=0;i<sopaDeLetras.length;i++) {
			for(j=0;j<sopaDeLetras[0].length;j++) {
				System.out.print(" "+sopaDeLetras[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		char[][] sopaDeLetras = new char[15][15];
		int[][] flags = new int[15][15];
		String[] palabras = new String[10];
		String p1;
		int i,j;
		boolean ok;
		Scanner t = new Scanner(System.in);
		
		System.out.println("+-------------------------------------------+");
		System.out.println("|              SOPA DE LETRAS               |");
		System.out.println("+-------------------------------------------+");
		
		//Crear sopa de letras aleatoria
		for(i=0;i<sopaDeLetras.length;i++) {
			for(j=0;j<sopaDeLetras[0].length;j++) {
				sopaDeLetras[i][j] = (char)(Math.random()*26+'A');
			}
		}
		
		//Crear matriz de flags
		for(i=0;i<flags.length;i++) {
			for(j=0;j<flags[0].length;j++) {
				flags[i][j] = 0;
			}
		}
		
		mostrarSopa(sopaDeLetras);
		
		//Vector de palabras introducidas
		for(i=0;i<10;i++) {
			do {
				System.out.println("PALABRA "+(i+1)+":");
				p1=t.next();
				p1=p1.toUpperCase();
			}while(validarPalabra(palabras,p1)!=true);
			
			palabras[i]=p1;
			do{
				ok=colocarPalabraAleatorio(sopaDeLetras, flags, p1);
			}while(ok!=true);
			mostrarSopa(sopaDeLetras);
		}
		for(i=0;i<10;i++) {
			System.out.println(palabras[i]);
		}
		t.close();
	}

}
