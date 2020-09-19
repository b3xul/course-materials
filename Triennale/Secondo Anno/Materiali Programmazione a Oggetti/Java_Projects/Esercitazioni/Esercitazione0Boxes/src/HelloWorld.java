public class HelloWorld {

	public static void main(String[] args) {
		try{
			Exceptional eee=new Exceptional();
			eee.metodoInput();
			Exceptional.metodoChain();
		}
		
		catch(NumberFormatException e) {
			//sottoclasse di runtime quindi viene prima
			System.out.println("Ho beccato un errore nel formato del numero!");
		}
		catch(RuntimeException e) {
			System.out.println("Ho beccato un errore di runtime generico!");
		}
		
		catch(Exception e) {
			System.out.println("Is this your exception??");
		}
		
		finally {
		System.out.println("Hello World!");
		}
		
		System.out.println("Ne sono uscito.");

	}
}
