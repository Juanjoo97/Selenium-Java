package support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.RunCucumberTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static steps.Hook.driver;

public class Utils extends RunCucumberTest {

    public void esperarElemento(By element, int tempo) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

    }

    public void elementoLocalizado(By element, int tempo) {

        WebDriverWait wait = new WebDriverWait(driver, tempo);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }


    public Object dataLayerfiltrado(String numero) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return JSON.stringify(dataLayer.filter(function(event) { return event.event === '"+numero +"'; }))";
        Object dataLayerItem = js.executeScript(script);
        return  dataLayerItem;
    }
    private XWPFDocument document; // Variable para mantener el documento abierto
    public void guardarArchivoGtmfiltrado(String numero) {
        String filePath = "dataLayer.txt";
        File fileToDelete = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            Object dataLayerItem = dataLayerfiltrado(numero);
            // Crear un documento Word o cargar uno existente si existe
            XWPFDocument document;
            File archivoDocx = new File("codigo.docx");
            if (archivoDocx.exists()) {
                document = new XWPFDocument(new FileInputStream(archivoDocx));
            } else {
                document = new XWPFDocument();
            }
            XWPFParagraph paragraph = document.createParagraph();
            // Crear un ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            // Parsear la lista JSON en un array de JsonNode
            List<JsonNode> jsonNodes = objectMapper.readValue(dataLayerItem.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class));
            // Iterar a través de los objetos JSON individuales
            for (JsonNode jsonNode : jsonNodes) {
                // Acceder a los valores dentro de cada objeto JSON
                String eventCategory = jsonNode.get("eventCategory").asText();
                String eventAction = jsonNode.get("eventAction").asText();
                String eventLabel = jsonNode.get("eventLabel").asText();
                String event = jsonNode.get("event").asText();
                XWPFRun run = paragraph.createRun();
                run.setText("eventCategory: " + eventCategory);
                run.addBreak();
                run.setText("eventAction: " + eventAction);
                run.addBreak();
                run.setText("eventLabel: " + eventLabel);
                run.addBreak();
                run.setText("event: " + event);
                run.addBreak();
                run.addBreak();

                writer.println("eventCategory: " + eventCategory);
                writer.println("eventAction: " +eventAction );
                writer.println("eventLabel: " + eventLabel );
                writer.println("event: "+ event );
                writer.println("\n");

            }
            FileOutputStream out = new FileOutputStream(new File("codigo.doc"));
            document.write(out);
            out.close();
            System.out.println("El archivo 'documento.docx' se ha creado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
