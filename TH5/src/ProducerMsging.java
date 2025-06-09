import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerMsging {

    // Địa chỉ của cái ActiveMQ với công mặc định
	private static String url = "failover://tcp://172.20.10.4:61616";

    // Tên topic
    private static String subject = "cc";

    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        
        // Messages are sent and received using a Session
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);

        try {
            int msgTemp = 0;
            while (msgTemp < 5) {
                msgTemp++;
                String msg = "Chao Nam" + String.valueOf(msgTemp);
                TextMessage message = session.createTextMessage(msg);
                producer.send(message);
                System.out.println("Sang gui tin nhan " + msg + " has been sent.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}