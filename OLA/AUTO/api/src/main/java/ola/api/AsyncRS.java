package ola.api;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.jms.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/async/start")
public class AsyncRS {

    private Logger LOG = Logger.getLogger(getClass());

    @Resource(mappedName="java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName="java:/jms/queue/ExpiryQueue")
    private javax.jms.Queue queue;

    // The Java method will process HTTP GET requests
    @POST
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(APPLICATION_JSON)
    public Response getClichedMessage() {

        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            TextMessage message = session.createTextMessage();

            for (int i = 0; i < 100; i++) {
                message.setText("This is message " + (i + 1));
                LOG.info("Sending message: " + message.getText());
                messageProducer.send(message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


        return Response.ok().build();
    }
}
