package allegro;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class labirynt {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		/* Utworzenie labiryntu - reczne */
		
		int[][] tab = new int[4][5];
		tab[0][0]=tab[0][1]=tab[0][2]=tab[0][3]=tab[0][4]=1;
		tab[1][0]=1;tab[1][1]=tab[1][2]=tab[1][3]=tab[1][4]=0;
		tab[2][0]=tab[2][2]=tab[2][4]=1;tab[2][1]=tab[2][3]=0;
		tab[3][0]=tab[3][2]=tab[3][3]=tab[3][4]=1;tab[3][1]=0;

		
		ArrayList<block> Cross = new ArrayList<block>();		// Lista skrzyzowan
		ArrayList<block> Path = new ArrayList<block>();			// Lista prawidlowej sciezki
		
		int start1 = 3;											// koordynata nr 1
		int start2 = 1;											// koordynata nr 2
		System.out.println("Koordynaty startowe to: ["+start1+","+start2+"]");
		
		int max1 = tab.length-1;								// Rozmiary labiryntu
		int max2 = tab[0].length-1;
		
		
		for(int i=0;i<=max1;i++){								// Wyswietlenie labiryntu
			for(int j=0;j<=max2;j++)
				System.out.print(tab[i][j]);
			System.out.print("\n");
		}
		
		Path.add(new block(start1, start2));
		
		boolean stop = false;									// glowna petla badania labiryntu
		int counter = 0;										// licznik mozliwych sciezek wyboru z danego punktu
		
		while(!stop){
			
			counter = 0;
			
			/* Badanie czy jesteśmy na skrzyzowaniu czy na prostej drodze */
			if(start1+1 <= max1 && tab[start1+1][start2] == 0)
				counter++;
			if(start2-1 >= 0 && tab[start1][start2-1] == 0)
				counter++;
			if(start1-1 >= 0 && tab[start1-1][start2] == 0)
				counter++;
			if(start2+1 <= max2 && tab[start1][start2+1] == 0)
				counter++;
			
			/* Patrzenie czy wokol aktualnego stanu sciezki są zera, 
			 * jesli tak badamy je po koleji wchodząc w glab
			 */
			
			if(start1+1 <= max1 && tab[start1+1][start2] == 0)
			{
				if(Path.get(Path.size() - 1).down == true)
				{
					Path.get(Path.size() - 1).setDown(false);
					Path.add(new block(start1+1,start2));
					Path.get(Path.size() - 1).setUp(false);
					start1 = start1+1;
				}
			}
			else{
				Path.get(Path.size() - 1).setDown(false);
			}
			if(start2-1 >= 0 && tab[start1][start2-1] == 0)
			{
				if(Path.get(Path.size() - 1).left == true)
				{
					Path.get(Path.size() - 1).setLeft(false);
					Path.add(new block(start1,start2-1));
					Path.get(Path.size() - 1).setRight(false);
					start2 = start2-1;
				}
			}
			else{
				Path.get(Path.size() - 1).setLeft(false);
			}
			if(start1-1 >= 0 && tab[start1-1][start2] == 0)
			{
				if(Path.get(Path.size() - 1).up == true)
				{
					Path.get(Path.size() - 1).setUp(false);
					Path.add(new block(start1-1,start2));
					Path.get(Path.size() - 1).setDown(false);
					start1 = start1-1;
				}
			}
			else{
				Path.get(Path.size() - 1).setUp(false);
			}
			if(start2+1 <= max2 && tab[start1][start2+1] == 0)
			{
				if(Path.get(Path.size() - 1).right == true)
				{
					Path.get(Path.size() - 1).setRight(false);
					Path.add(new block(start1,start2+1));
					Path.get(Path.size() - 1).setLeft(false);
					start2 = start2+1;
				}
			}
			else{
				Path.get(Path.size() - 1).setRight(false);
			}
			
			/* Dodanie punktu labiryntu do listy skrzyzowan .. jezeli takowy jest */
			if(counter >= 3)
			{
				Cross.add(Path.get(Path.size() - 2));	
			}
			
			/* Usuwanie slepiej sciezki az do skrzyzowania z ktorego zeszlismy */
			if(Path.get(Path.size() - 1).right == false &&
				Path.get(Path.size() - 1).left == false &&
				Path.get(Path.size() - 1).up == false &&
				Path.get(Path.size() - 1).down == false &&
				Cross.isEmpty() != true)
				{
					boolean del = true;
					while(del){
						if(Path.get(Path.size() - 1) != Cross.get(Cross.size() - 1))
						{
							Path.remove(Path.size() - 1);
							start1 = Path.get(Path.size() -1).getX();
							start2 = Path.get(Path.size() -1).getY();
						}
						else 
							del=false;
					}
					
					Cross.remove(Cross.size()-1);
				}
			
			
			/* Badanie czy doszlismy do kranca labiryntu, jezeli tak to mamy wyjscie */
			if(Path.get(Path.size() - 1).getX() == max1 || Path.get(Path.size() - 1).getY() == max2 || Path.get(Path.size() - 1).getX() == 0 || Path.get(Path.size() - 1).getY() ==0)
				stop = true;
		}
		System.out.println("Wynik (sciezka):"+ Path);
	}

}
