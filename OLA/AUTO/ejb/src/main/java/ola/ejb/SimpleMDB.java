package ola.ejb;

import org.apache.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static java.lang.String.format;

@MessageDriven(name = "simplemdb", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "QUEUE"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class SimpleMDB implements MessageListener {

    private Logger LOG = Logger.getLogger(getClass());

    @Override
    public void onMessage(Message message) {

        String txt = null;
        try {
            txt = ((TextMessage) message).getText();
            LOG.info(format("Receiving message [%s] received. Processing ... ", txt));

            try {
                // Simulate a long-running iteration ...
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LOG.info(format("Message [%s] processed.", txt));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
