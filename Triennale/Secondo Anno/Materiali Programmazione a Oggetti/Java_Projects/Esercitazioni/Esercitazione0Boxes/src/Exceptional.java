import java.util.Scanner;

class Exceptional{
	//attributi
	private int a=0;
	private String s;
	private float b=1.0F;
	//metodi
	public void metodoInput() throws Exception {	//E' IL METODO CHE E' IN GRADO DI LANCIARE L'ECCEZIONE, NON LA CLASSE!
		Scanner reader=new Scanner(System.in);
		this.a=reader.nextInt();
		this.s=reader.next();
		this.b=Float.parseFloat(s);
		reader.close();
		if(b<1.0F) throw new Exception("flo(w)at basso");
		System.out.println(a);
		System.out.println(b);
		return;
	}
	
	public static void metodoChain() throws Exception{
		try {
			String s2="Casa";
			int c=Integer.parseInt(s2);
		}
		catch(NumberFormatException e){
			throw new Exception("Problema Chain");
		}
		finally {
			System.out.println("Finalmente!");
		}
			System.out.println("Io non vengo stampato!");
	}

}
