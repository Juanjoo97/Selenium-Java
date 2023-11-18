package pages;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Utils;
import java.io.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

public class Actions extends Utils {
    private static final Logger logger = Logger.getLogger(Actions.class);
    String environment = "dev";
    Properties properties = loadPropertiesEnv(environment);
    String appUrl = properties.getProperty("app.url");
    String userField = properties.getProperty("user");
    String passField = properties.getProperty("pass");
    String selecProject = properties.getProperty("project");
    String channelProject = properties.getProperty("channel.project");
    String idDocument = properties.getProperty("id.document");
    String dataDocument = properties.getProperty("data.document");
    String moneyRequest = properties.getProperty("money.request");
    String duesRequest = properties.getProperty("dues.request");
    String email = properties.getProperty("email");
    String cell = properties.getProperty("cell");
    String lastnameOne = properties.getProperty("lastname1");
    String lastnameTwo = properties.getProperty("lastname2");
    String name = properties.getProperty("name");
    String birth = properties.getProperty("birth");
    String company = properties.getProperty("company");
    String income = properties.getProperty("income");
    String actividadlaboral1 = properties.getProperty("actividadlaboral1");

    String prestacion_service = properties.getProperty("prestacion_service");
    String cotiza = properties.getProperty("cotiza");
    String vendedor_vehiculo = properties.getProperty("vendedor");
    String Seguro = properties.getProperty("seguro");

    private final By concesionarios_autorizadas = By.xpath("//a[normalize-space()='concesionarios y marcas autorizadas.']");
    private final By conozca_credito = By.xpath("//a[normalize-space()='Conozca más de este crédito.']");
    private final By boton_close = By.xpath("//div[@class='dav-bottomSheet__scrim']");
    private final By atras = By.xpath("//button[normalize-space()='Atrás']");
    private final By create_user_field = By.xpath("//input[@placeholder='Usuario']");
    private final By create_password_field = By.xpath("//input[@placeholder='Contraseña']");
    private final By create_aceptar_button = By.className("aceptar");
    private final By select_proyecto = By.xpath("(//SELECT[@formcontrolname='aplicacion'])");
    private final By canal_proyecto = By.xpath("(//SELECT[@formcontrolname='canalApp'])");
    private final By id_document = By.xpath("(//input[@formcontrolname='tipo'])");
    private final By document = By.xpath("(//input[@formcontrolname='numero'])");
    private final By imageBeneficios = By.xpath("//img[@alt='listBenefits__imageBenefits']");
    private final By aceptar_button = By.cssSelector("div.footer");
//    private final By aceptar_button = By.xpath("(//div[@class='dav-buttonContainer--vertical']//button[@name='button'][normalize-space()='Continuar'])");
    private final By cedula = By.xpath("(//input[@id='idNumber'])");
    private final By aceptar_buttoninfpersonal = By.xpath("(//button[@id='nextButton'])");
    private final By aceptar_checkinfpersonal = By.xpath("(//div[@class='checkbox__indicator'])");
    private final By dinero_solicitar = By.xpath("(//input[@placeholder='$0,00'])");
    private final By plazo_solicitar = By.xpath("(//input[@placeholder='0'])");
    private final By simulacion_button = By.xpath("(//button[@class='dav-button--primary'])");
    private final By formulario_button = By.xpath("(//div[@class='footer']//dav-button-container//div[@class='dav-buttonContainer--vertical']//dav-button//button[@name='button'][normalize-space()='Continuar'])");
    private final By resultado_simulacion_button = By.xpath("(//button[normalize-space()='Solicitar Crédito'])");
    private final By resultado_simulacion_vermas = By.xpath("(//img[@alt='flecha abajo'])");

    private final By correo_autenticacion = By.xpath("(//input[@id='correo'])");
    private final By celular_autenticacion = By.xpath("(//input[@id='celular'])");
    private final By boton_autenticacion = By.xpath("(//mbaas-button-continue[@class='ng-star-inserted'])");
    private final By l = By.xpath("//strong[normalize-space()='L']");
    private final By otp = By.xpath("//p[contains(text(),'otp')]");
    private final By inputotp = By.xpath("//input[@id='inputotp']");
    private final By primerapellido = By.xpath("//input[@id='primerApellido']");
    private final By segundoapellido = By.xpath("//input[@id='segundoApellido']");
    private final By nobrecompleto = By.xpath("//input[@id='nombreCompleto']");
    private final By fechanacimiento = By.xpath("//input[@name='davDateTextField']");
    private final By actividadlaboral = By.xpath("//p[normalize-space()='Actividad']");
    private final By actividadlaboral11 = By.xpath(actividadlaboral1);
    private final By actividadeconomica1 = By.xpath("//input[@placeholder='Actividad']");
    private final By actividadeconomica11 = By.xpath("//li[normalize-space()='Actividades de arquitectura - 07111']");
    private final By empresa = By.xpath("//input[@id='empresa']");
    private final By contrato = By.xpath("//div[@id='tipoContrato']");
    private final By prestacion = By.xpath(prestacion_service);
    private final By ingresomensual = By.xpath("//input[@placeholder='$0,00']");
    private final By regimen = By.xpath("//div[@id='regimenSalud']");
    private final By cotiza1 = By.xpath(cotiza);
    private final By vendedor = By.xpath("//div[@id='vendedor']");
    private final By vendedor_vehiculo1 = By.xpath(vendedor_vehiculo);
    private final By pp_button = By.xpath(Seguro);
    private final By tyc_button = By.xpath("//strong[normalize-space()='Conocer términos y condiciones']");
    private final By tyc_container = By.xpath("//div[@class='dav-contTyC--internalContent']");


    private final By evaluacion_button = By.xpath("//button[normalize-space()='Solicitar Crédito']");


    WebDriver driver;


    public Actions(WebDriver driver) {
        this.driver = driver;
    }

    private static Properties loadPropertiesEnv(String environment) {
        Properties properties = new Properties();
        String fileName = "src/test/resources/config-" + environment + ".properties";
        try (FileInputStream input = new FileInputStream(fileName)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return properties;
    }


    public void foto(WebDriver webdriver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public void acessarTela() {
        driver.get(appUrl);
        esperarElemento(create_user_field, 15);
        esperarElemento(create_password_field, 15);
        esperarElemento(create_aceptar_button, 15);
    }

    public void acessarCampo() {
        driver.findElement(create_user_field).sendKeys(userField);
        driver.findElement(create_password_field).sendKeys(passField);
    }

    public void actividadLaboral(By actividadlaboral11) {
        if ("//p[normalize-space()='Empleado/a']".equals(actividadlaboral1)) {
            esperarElemento(this.actividadlaboral11, 15);
            driver.findElement(this.actividadlaboral11).click();
            esperarElemento(empresa, 15);
            driver.findElement(empresa).sendKeys(company);
            esperarElemento(contrato, 15);
            driver.findElement(contrato).click();
            if ("//p[normalize-space()='Prestación de servicios']".equals(prestacion_service)) {
                esperarElemento(prestacion, 15);
                driver.findElement(prestacion).click();
            }
            if ("//p[normalize-space()='Término fijo']".equals(prestacion_service)) {
                esperarElemento(prestacion, 15);
                driver.findElement(prestacion).click();
            }
            if ("//p[normalize-space()='Término indefinido']".equals(prestacion_service)) {
                esperarElemento(prestacion, 15);
                driver.findElement(prestacion).click();
            }
        } else if ("//p[normalize-space()='Independiente']".equals(actividadlaboral1)) {
            esperarElemento(this.actividadlaboral11, 15);
            driver.findElement(this.actividadlaboral11).click();
            esperarElemento(actividadeconomica1, 15);
            driver.findElement(actividadeconomica1).sendKeys("tec");
            esperarElemento(actividadeconomica11, 15);
            driver.findElement(actividadeconomica11).click();
        } else {
            esperarElemento(this.actividadlaboral11, 15);
            driver.findElement(this.actividadlaboral11).click();
        }
    }

    public void clickBotonlogin() {
        driver.findElement(create_aceptar_button).click();
    }


    public void clickSelect() {
        esperarElemento(select_proyecto, 10);
        esperarElemento(canal_proyecto, 10);
        Select selectproyecto = new Select(driver.findElement(select_proyecto));
        selectproyecto.selectByVisibleText(selecProject);
        Select selectcanal = new Select(driver.findElement(canal_proyecto));
        selectcanal.selectByVisibleText(channelProject);
        esperarElemento(id_document, 5);
        esperarElemento(document, 5);
        driver.findElement(id_document).sendKeys(idDocument);
        driver.findElement(document).sendKeys(dataDocument);

    }

    public String cerrarVentana() throws InterruptedException {
        String mainTab = driver.getWindowHandle();
        String newTab = "";
        clickBotonlogin();
        Thread.sleep(2000);
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (!actual.equalsIgnoreCase(mainTab)) {
                driver.switchTo().window(actual);
                newTab = actual;
            }
        }
        driver.switchTo().window(mainTab);
        driver.close();
        driver.switchTo().window(newTab);
        return newTab;
    }

    public void clickBotonProyecto() throws Exception {
//        esperarElemento(aceptar_button, 5);
        WebElement aceptarButton = driver.findElement(By.cssSelector("div.footer"));
        aceptarButton.click();
        esperarElemento(imageBeneficios, 10);
        foto(driver, "Screenshot/Pantalla_Beneficio.png");
//        driver.findElement(aceptar_button).click();
    }

    public void informacionPersonal() throws Exception {
        esperarElemento(cedula, 10);
        foto(driver, "Screenshot/Pantalla_Informacion1.png");
        esperarElemento(cedula, 10);
        driver.findElement(cedula).sendKeys(dataDocument);
    }

    public void clickBotonInfoPersonal() throws Exception {
        esperarElemento(aceptar_buttoninfpersonal, 20);
        foto(driver, "Screenshot/Pantalla_Informacion2.png");
        driver.findElement(aceptar_buttoninfpersonal).click();
    }

    public void clickCheckInfoPersonal() throws Exception {
        esperarElemento(aceptar_checkinfpersonal, 20);
        foto(driver, "Screenshot/Pantalla_Informacion3.png");
        driver.findElement(aceptar_checkinfpersonal).click();
        esperarElemento(aceptar_buttoninfpersonal, 20);
        foto(driver, "Screenshot/Pantalla_Informacion4.png");
        driver.findElement(aceptar_buttoninfpersonal).click();
    }

    public void simulacion() throws Exception {
        esperarElemento(dinero_solicitar, 15);
        foto(driver, "Screenshot/Pantalla_Simulacion.png");
        driver.findElement(dinero_solicitar).sendKeys(moneyRequest);
        esperarElemento(plazo_solicitar, 15);
        foto(driver, "Screenshot/Pantalla_Simulacion1.png");
        driver.findElement(plazo_solicitar).sendKeys(duesRequest);
        foto(driver, "Screenshot/Pantalla_Simulacion2.png");
        driver.findElement(simulacion_button).click();
    }

    public void resultadoSimulacion() throws Exception {
        esperarElemento(resultado_simulacion_button, 15);
        foto(driver, "Screenshot/Pantalla_Resultado.png");
//        Thread.sleep(10000000);
        driver.findElement(resultado_simulacion_button).click();
//        guardarArchivoGtm(40);
//        Thread.sleep(10000000);

    }

    public void autenticacionLiviana() {
        esperarElemento(correo_autenticacion, 15);
        driver.findElement(correo_autenticacion).sendKeys(email);
        esperarElemento(celular_autenticacion, 15);
        driver.findElement(celular_autenticacion).sendKeys(cell);
        esperarElemento(boton_autenticacion, 20);
        driver.findElement(boton_autenticacion).click();
        esperarElemento(l, 15);
        driver.findElement(l).click();
        WebElement otpElement = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(otp));
        String innerHTMLValue = otpElement.getAttribute("innerHTML");
        String otpnumber = innerHTMLValue.substring(13, 21);
        esperarElemento(inputotp, 15);
        driver.findElement(inputotp).sendKeys(otpnumber);
        esperarElemento(boton_autenticacion, 20);
        driver.findElement(boton_autenticacion).click();
    }

    public void formulario() throws Exception {
//        Thread.sleep(10000000);
        esperarElemento(primerapellido, 15);
        driver.findElement(primerapellido).sendKeys(lastnameOne);
        esperarElemento(segundoapellido, 15);
        driver.findElement(segundoapellido).sendKeys(lastnameTwo);
        esperarElemento(nobrecompleto, 15);
        driver.findElement(nobrecompleto).sendKeys(name);
        esperarElemento(fechanacimiento, 15);
        driver.findElement(fechanacimiento).sendKeys(birth);
        esperarElemento(actividadlaboral, 15);
        driver.findElement(actividadlaboral).click();
        actividadLaboral(actividadlaboral11);
        foto(driver, "Screenshot/Pantalla_Formulario1.png");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, window.innerHeight);");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        esperarElemento(ingresomensual, 15);
        driver.findElement(ingresomensual).sendKeys(income);
    }

    public void formulario2() throws Exception {
        esperarElemento(regimen, 15);
        driver.findElement(regimen).click();
        WebElement Cotiza = driver.findElement(cotiza1);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Cotiza);
        Thread.sleep(200);
        esperarElemento(cotiza1, 15);
        driver.findElement(cotiza1).click();
        JavascriptExecutor jss = (JavascriptExecutor) driver;
        WebElement Vendedor = driver.findElement(vendedor);
        jss.executeScript("arguments[0].scrollIntoView();", Vendedor);
        esperarElemento(vendedor, 15);
        driver.findElement(vendedor).click();
        JavascriptExecutor jsss = (JavascriptExecutor) driver;
        WebElement Concensionario = driver.findElement(vendedor_vehiculo1);
        jsss.executeScript("arguments[0].scrollIntoView();", Concensionario);
        Thread.sleep(200);
        esperarElemento(vendedor_vehiculo1, 15);
        driver.findElement(vendedor_vehiculo1).click();
        JavascriptExecutor jssss = (JavascriptExecutor) driver;
        jssss.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(200);
        foto(driver, "Screenshot/Pantalla_Formulario2.png");
        esperarElemento(formulario_button, 15);
        driver.findElement(formulario_button).click();
    }

    public void pp() throws Exception {
        esperarElemento(pp_button, 15);
        foto(driver, "Screenshot/Pantalla_Seguro.png");
        Thread.sleep(10000000);
        driver.findElement(pp_button).click();
    }

    public void evaluacion() throws Exception {
        esperarElemento(evaluacion_button, 15);
        foto(driver, "Screenshot/Pantalla_Evaluacion.png");
        driver.findElement(evaluacion_button).click();
    }










    public void clickBotonProyectoGTM() {
        esperarElemento(aceptar_button, 10);
        esperarElemento(atras, 10);
        esperarElemento(imageBeneficios, 10);
        esperarElemento(concesionarios_autorizadas, 10);
        esperarElemento(conozca_credito, 10);
        driver.findElement(atras).click();
        driver.findElement(concesionarios_autorizadas).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.findElement(conozca_credito).click();
        esperarElemento(boton_close, 10);
        driver.findElement(boton_close).click();
        driver.findElement(aceptar_button).click();

    }

    public void informacionPersonalGTM()  {
        esperarElemento(cedula, 10);
        esperarElemento(cedula, 10);;
        driver.findElement(cedula).sendKeys(dataDocument);
    }

    public void clickBotonInfoPersonalGTM()  {
        esperarElemento(aceptar_buttoninfpersonal, 20);
        driver.findElement(aceptar_buttoninfpersonal).click();
    }

    public void clickCheckInfoPersonalGTM()  {
        esperarElemento(aceptar_checkinfpersonal, 20);
        driver.findElement(aceptar_checkinfpersonal).click();
        esperarElemento(aceptar_buttoninfpersonal, 20);
        driver.findElement(aceptar_buttoninfpersonal).click();
    }

    public void simulacionGTM() {
        esperarElemento(dinero_solicitar, 15);
        driver.findElement(dinero_solicitar).sendKeys(moneyRequest);
        esperarElemento(plazo_solicitar, 15);
        driver.findElement(plazo_solicitar).sendKeys(duesRequest);
        driver.findElement(simulacion_button).click();
    }
    public void simulacionrepeticionGTM() {
        esperarElemento(dinero_solicitar, 20);
        driver.findElement(dinero_solicitar).sendKeys(moneyRequest);
        esperarElemento(plazo_solicitar, 20);
        driver.findElement(plazo_solicitar).sendKeys(duesRequest);
        driver.findElement(simulacion_button).click();
    }

    public void resultadoSimulacionGTM()  {
        esperarElemento(resultado_simulacion_button, 20);
        esperarElemento(resultado_simulacion_vermas, 20);
        driver.findElement(resultado_simulacion_vermas).click();
        driver.findElement(resultado_simulacion_button).click();
        guardarArchivoGtmfiltrado("pageview");
        guardarArchivoGtmfiltrado("eventClick");
    }

    public void autenticacionLivianaGTM() {
        esperarElemento(correo_autenticacion, 15);
        driver.findElement(correo_autenticacion).sendKeys(email);
        esperarElemento(celular_autenticacion, 15);
        driver.findElement(celular_autenticacion).sendKeys(cell);
        esperarElemento(boton_autenticacion, 20);
        driver.findElement(boton_autenticacion).click();
        driver.findElement(l).click();
        WebElement otpElement = new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(otp));
        String innerHTMLValue = otpElement.getAttribute("innerHTML");
        String otpnumber = innerHTMLValue.substring(13, 21);
        esperarElemento(inputotp, 15);
        driver.findElement(inputotp).sendKeys(otpnumber);
        esperarElemento(boton_autenticacion, 20);
        driver.findElement(boton_autenticacion).click();
    }

    public void formularioGTM()  {
        esperarElemento(primerapellido, 15);
        driver.findElement(primerapellido).sendKeys(lastnameOne);
        esperarElemento(segundoapellido, 15);
        driver.findElement(segundoapellido).sendKeys(lastnameTwo);
        esperarElemento(nobrecompleto, 15);
        driver.findElement(nobrecompleto).sendKeys(name);
        esperarElemento(fechanacimiento, 15);
        driver.findElement(fechanacimiento).sendKeys(birth);
        esperarElemento(actividadlaboral, 15);
        driver.findElement(actividadlaboral).click();
        actividadLaboral(actividadlaboral11);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, window.innerHeight);");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        esperarElemento(ingresomensual, 15);
        driver.findElement(ingresomensual).sendKeys(income);
    }

    public void formulario2GTM() throws Exception {
        esperarElemento(regimen, 15);
        driver.findElement(regimen).click();
        WebElement Cotiza = driver.findElement(cotiza1);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Cotiza);
        Thread.sleep(200);
        esperarElemento(cotiza1, 15);
        driver.findElement(cotiza1).click();
        JavascriptExecutor jss = (JavascriptExecutor) driver;
        WebElement Vendedor = driver.findElement(vendedor);
        jss.executeScript("arguments[0].scrollIntoView();", Vendedor);
        esperarElemento(vendedor, 15);
        driver.findElement(vendedor).click();
        JavascriptExecutor jsss = (JavascriptExecutor) driver;
        WebElement Concensionario = driver.findElement(vendedor_vehiculo1);
        jsss.executeScript("arguments[0].scrollIntoView();", Concensionario);
        Thread.sleep(200);
        esperarElemento(vendedor_vehiculo1, 15);
        driver.findElement(vendedor_vehiculo1).click();
        JavascriptExecutor jssss = (JavascriptExecutor) driver;
        jssss.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(200);
        esperarElemento(formulario_button, 15);
        driver.findElement(formulario_button).click();
    }

    public void ppGTM() throws InterruptedException {
        esperarElemento(tyc_button, 15);
        driver.findElement(tyc_button).click();
        esperarElemento(tyc_container, 15);
        driver.findElement(tyc_container).click();
        JavascriptExecutor jsssss = (JavascriptExecutor) driver;
        jsssss.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(200);
        esperarElemento(pp_button, 15);
        driver.findElement(pp_button).click();

    }

    public void evaluacionGTM() throws Exception {
        Thread.sleep(900000000);
        esperarElemento(evaluacion_button, 15);
        driver.findElement(evaluacion_button).click();
    }
    public void preaprobadoGTM() throws Exception {
        Thread.sleep(9000);
        guardarArchivoGtmfiltrado("pageview");
        guardarArchivoGtmfiltrado("eventClick");
    }

}
