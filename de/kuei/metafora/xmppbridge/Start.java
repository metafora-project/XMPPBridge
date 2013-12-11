package de.kuei.metafora.xmppbridge;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import de.kuei.metafora.xmppbridge.util.MessageConsolePrinter;
import de.kuei.metafora.xmppbridge.xmpp.NameConnectionMapper;
import de.kuei.metafora.xmppbridge.xmpp.ServerConnection;
import de.kuei.metafora.xmppbridge.xmpp.XmppMUC;
import de.kuei.metafora.xmppbridge.xmpp.XmppMUCManager;

public class Start {

	private static final Logger log = Logger.getLogger(Start.class);

	public static void main(String[] args) throws Exception {
		// load log4j configuration
		PropertyConfigurator.configure("log4j.properties");

		// test output
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		log.fatal("fatal");

		MessageConsolePrinter packetPrinter = new MessageConsolePrinter("Testprinter");
		
		NameConnectionMapper.getInstance().createConnection("metafora", "metafora.ku-eichstaett.de", "planningsolocommand", "didPfPSC", "planningToolAtThomasThinkpad");
		
		ServerConnection connection = NameConnectionMapper.getInstance().getConnection("metafora");
		
		connection.addPacketListener(packetPrinter);
		
		connection.login();
		
		XmppMUC muc = XmppMUCManager.getInstance().getMultiUserChat("logger", "planningToolThomasThinkpad", "metafora");
		muc.join(0);
		
		while(true){
			Thread.sleep(1000);
			muc.sendMessage("bin da!");
		}
		
	}

}
