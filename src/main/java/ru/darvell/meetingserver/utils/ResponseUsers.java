package ru.darvell.meetingserver.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.darvell.meetingserver.entitys.User;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Ответ с набором пользователей
 */
public class ResponseUsers implements Response{
	int code = -1;
	String stringData = "";
	ArrayList<User> userSet = null;

	public ResponseUsers(int code) {
		this.code = code;
	}

	@Override
	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public void setStringData(String stringData) {
		this.stringData = stringData;
	}

	@Override
	public String getStringData() {
		return this.stringData;
	}

	public void setUserSet(ArrayList<User> users){
		this.userSet = users;
	}


	@Override
	public String toString() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("response");
			doc.appendChild(rootElement);

			Element code = doc.createElement("resp_code");
			code.appendChild(doc.createTextNode(String.valueOf(this.code)));
			rootElement.appendChild(code);

			Element stringData = doc.createElement("stringData");
			stringData.appendChild(doc.createTextNode(String.valueOf(this.code)));
			rootElement.appendChild(code);

			if ((userSet != null) && (userSet.size() > 0)) {

				Element users = doc.createElement("users");

				for (User user : this.userSet){
					Element userNode = doc.createElement("user");
					userNode.setAttribute("id", String.valueOf(user.getId()));

					Element login = doc.createElement("login");
					login.appendChild(doc.createTextNode(user.getLogin()));
					userNode.appendChild(login);

					Element statusId = doc.createElement("status_id");
					statusId.appendChild(doc.createTextNode(String.valueOf(user.getStatusId())));
					userNode.appendChild(statusId);

					Element statusMess = doc.createElement("status_mess");
					statusMess.appendChild(doc.createTextNode(user.getStatusMess()));
					userNode.appendChild(statusMess);

					users.appendChild(userNode);
				}
				rootElement.appendChild(users);
			}

			TransformerFactory tFact = TransformerFactory.newInstance();
			Transformer trans = tFact.newTransformer();

			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			System.out.println(writer.toString());

			return doc.toString();
		}catch (Exception e){
			return null;
		}
	}
}
