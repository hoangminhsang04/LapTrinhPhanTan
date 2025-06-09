package cal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author Desktop
 */
public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            
            CalculatorInterface calculatorSkeleton = new CalculatorImpl();
            
            registry.rebind("Calculator", calculatorSkeleton);
            
            while(true)           {
                System.out.println("Server dang chay ... ");
                Thread.sleep(5000);
            }                    
        } catch (Exception e) {
        }
    }
}
