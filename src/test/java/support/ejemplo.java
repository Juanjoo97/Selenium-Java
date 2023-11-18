package support;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ejemplo {

    public static void main(String[] args) {
        String jsonLista = "[{\"eventCategory\":\"APP - Zona Pública - Crédito Vehículo con Prenda\",\"eventAction\":\"Click en Atrás\",\"eventLabel\":\"Aliado: No aplica - Beneficios y Características - Atrás\",\"eventvalue\":\"\",\"event\":\"eventClick\",\"gtm.uniqueEventId\":16},{\"eventCategory\":\"APP - Zona Pública - Crédito Vehículo con Prenda\",\"eventAction\":\"Click en Enlace Aquí Listado de concesionarios y marcas autorizadas\",\"eventLabel\":\"Aliado: No aplica - Beneficios y características - Atrás\",\"eventvalue\":\"\",\"event\":\"eventClick\",\"gtm.uniqueEventId\":20},{\"eventCategory\":\"APP - Zona Pública - Crédito Vehículo con Prenda\",\"eventAction\":\"Click en Enlace Video\",\"eventLabel\":\"Aliado: No aplica - Beneficios y Características - Conozca más de este crédito - Enlace Video\",\"eventvalue\":\"\",\"event\":\"eventClick\",\"gtm.uniqueEventId\":24}]";

        try {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();



            // Crear un ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Parsear la lista JSON en un array de JsonNode
            JsonNode[] jsonNodes = objectMapper.readValue(jsonLista, JsonNode[].class);

            // Iterar a través de los objetos JSON individuales
            for (JsonNode jsonNode : jsonNodes) {
                // Acceder a los valores dentro de cada objeto JSON
                String eventCategory = jsonNode.get("eventCategory").asText();
                String eventAction = jsonNode.get("eventAction").asText();
                String eventLabel = jsonNode.get("eventLabel").asText();
                String event = jsonNode.get("event").asText();
                int gtmUniqueEventId = jsonNode.get("gtm.uniqueEventId").asInt();

//                String codigo = "publiccc class MiClase {\n    public static void main(String[] args) {\n        System.out.println(\"Hola, mundo!\");\n    }\n}";
                run.setText("eventCategory: " + eventCategory);
                run.addBreak();
                run.setText("eventAction: " + eventAction);
                run.addBreak();
                run.setText("eventLabel: " + eventLabel);
                run.addBreak();
                run.setText("event: " + event);
                run.addBreak();
                run.addBreak();
                FileOutputStream out = new FileOutputStream(new File("codigo.doc"));
                document.write(out);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
