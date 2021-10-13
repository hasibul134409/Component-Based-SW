package facade;

import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import painting.PaintingMachineLocal;
import printing.PrintingMachineLocal;
import robot.ProductionRobotLocal;

/**
 * Session Bean implementation class PrintingShopProductionFacade
 */
@Stateless
@LocalBean
public class PrintingShopProductionFacade implements PrintingShopProductionFacadeRemote, PrintingShopProductionFacadeLocal {

    private static PaintingMachineLocal paintingMachineLocal;
    private static PrintingMachineLocal printingMachineLocal;
	private static ProductionRobotLocal productionRobotLocal;
    
    public PrintingShopProductionFacade() {
        // TODO Auto-generated constructor stub
    	Properties props = new Properties();
    	props.put("java.naming.factory.initial","org.jboss.as.naming.InitialContextFactory");
        props.put("java.naming.provider.url", "localhost");
        props.put("jboss.naming.client.ejb.context", "true");
	    props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	    InitialContext context;
	    
	    // Create the EJB Application context
	    try {
			context = new InitialContext(props);
		} catch (NamingException e) {
			System.out.println("Context could not be created!");
			e.printStackTrace();
			return;
		}
		
	    // use the Context to look up the CustomerManagement Bean on the server
	    // run test scenario
		try{   
			String name = "java:module/PaintingMachine!painting.PaintingMachineLocal";
	        paintingMachineLocal = (PaintingMachineLocal)context.lookup(name);
	       // testCustomer();
		}catch(Exception e){
			System.out.println("Errors in Painting Management");
			e.printStackTrace();
			return;
		}
		
		try{   
			String name = "java:module/PrintingMachine!printing.PrintingMachineLocal";
	        printingMachineLocal = (PrintingMachineLocal)context.lookup(name);
	       // testCustomer();
		}catch(Exception e){
			System.out.println("Errors in Printing Management");
			e.printStackTrace();
			return;
		}
		
		
		try{   
			String name = "java:module/ProductionRobot!robot.ProductionRobotLocal";
	        productionRobotLocal = (ProductionRobotLocal)context.lookup(name);
	       // testCustomer();
		}catch(Exception e){
			System.out.println("Errors in Printing Management");
			e.printStackTrace();
			return;
		}
    }
    
    public void startProduction() {
    	this.printingMachineLocal.startPrinting();
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.printingMachineLocal.finishPrinting();
    	this.printingMachineLocal.moveProductToRobot("A");
    	this.productionRobotLocal.moveProduct("painntingMacine");
    	this.paintingMachineLocal.startPainting();
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.paintingMachineLocal.finishPainting();
    }
}
