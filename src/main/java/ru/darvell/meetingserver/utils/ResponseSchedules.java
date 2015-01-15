package ru.darvell.meetingserver.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.darvell.meetingserver.entitys.Schedule;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Ответ с набором расписаний
 */
public class ResponseSchedules implements Response {
    int code = -1;
    String stringData = "";
    ArrayList<Schedule> scheduleSet = null;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ResponseSchedules(int code) {
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

    public void setScheduleSet(ArrayList<Schedule> scheduleSet) {
        this.scheduleSet = scheduleSet;
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

            if ((scheduleSet != null) && (scheduleSet.size() > 0)) {

                Element schedules = doc.createElement("schedules");

                for (Schedule schedule : this.scheduleSet){
                    Element scheduleNode = doc.createElement("schedule");
                    scheduleNode.setAttribute("id", String.valueOf(schedule.getId()));

                    Element date1 = doc.createElement("date1");
                    date1.appendChild(doc.createTextNode(schedule.getDate1()));
                    scheduleNode.appendChild(date1);

                    Element date2 = doc.createElement("date2");
                    date2.appendChild(doc.createTextNode(schedule.getDate2()));
                    scheduleNode.appendChild(date2);

                    Element statusId = doc.createElement("status_id");
                    statusId.appendChild(doc.createTextNode(String.valueOf(schedule.getStatusId())));
                    scheduleNode.appendChild(statusId);

                    Element statusMess = doc.createElement("status_mess");
                    statusMess.appendChild(doc.createTextNode(schedule.getStatusMess()));
                    scheduleNode.appendChild(statusMess);

                    schedules.appendChild(scheduleNode);
                }
                rootElement.appendChild(schedules);
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
