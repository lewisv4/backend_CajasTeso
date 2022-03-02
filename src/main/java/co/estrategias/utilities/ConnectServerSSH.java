package co.estrategias.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class ConnectServerSSH implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(ConnectServerSSH.class);
//userShh, host, passShh, portBat, ruta

	public static String getCommandSSH(String userShh, String ip, String passShh, int portBat, String comando)
			throws ParseException, IOException {

		String linea = new String();

		try {
			JSch jsch = new JSch();
			Session session;

			session = jsch.getSession(userShh, ip, portBat);
			UserInfo ui = new SUserInfo(passShh, null);
			session.setUserInfo(ui);
			session.setPassword(passShh);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setTimeout(5000);
			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(comando);
		
			channel.connect();
			

			logger.info("validar la ejecucion del ssh "+comando);
			System.out.println("COMANDO: " + comando);

			StringBuilder outputBuffer = new StringBuilder();
			InputStream commandOutput = channel.getInputStream();
			int readByte = commandOutput.read();

			while (readByte != 0xffffffff) {
				outputBuffer.append((char) readByte);
				readByte = commandOutput.read();
			}

			linea = outputBuffer.toString();

			System.out.println(linea);

			channel.disconnect();
			session.disconnect();

		} catch (JSchException e) {
			e.printStackTrace();
			return new String();
		}

		return linea;
	}
}
