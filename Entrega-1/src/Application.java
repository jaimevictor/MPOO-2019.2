import Hello.HelloWorld; import java.util.Date; import java.text.SimpleDateFormat;import java.text.DateFormat;
public class Application {

	public static void main(String[] args) {
		HelloWorld helloworld = new HelloWorld();
		HelloWorld helloworld2 = new HelloWorld();
		Date data = new Date();
		DateFormat formatacao = new SimpleDateFormat("HH:mm:ss");
		String dataform = formatacao.format(data);
		
		helloworld.setNome("Jaime Victor");
		System.out.print(dataform + " - ");
		helloworld.imprimir();
		
		Date data2 = new Date();
		String data2form = formatacao.format(data2);
		
		System.out.print(data2form + " - ");
		helloworld2.imprimir();

	}

		
}
