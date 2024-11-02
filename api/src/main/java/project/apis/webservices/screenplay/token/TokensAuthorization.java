package project.apis.webservices.screenplay.token;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Properties;

public class TokensAuthorization {

    public static HashMap<String, String> getToken(String portal) throws IOException {

        String url_base = null;
        String user = null;
        String password = null;
        HashMap<String, String> token = new HashMap<String, String>();

        Properties prop = getPropertyWhitFileLoaded();

        switch (portal) {
            case "P1":
                url_base = "https://p1-tst.webservices.com";
                user = "user";
                password = "password";
                break;
            case "P2":
                url_base = "https://p2-tst.webservices.com";
                user = "user";
                password = "123456";
                break;
        }

        if (isTokenExpired(prop)) {
            WebDriver driver = getDriver();
            try {
                driver.get(url_base);

                driver.manage().window().setSize(new Dimension(1920, 1080));
                WebDriverWait wait = new WebDriverWait(driver, 20);

                // Esto solo aplica para la obtencion del token Authorization y AuthorizationInfo para PCL
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("XXXXXXX")));
                driver.findElement(By.xpath("XXXXXXX")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("user")));
                driver.findElement(By.xpath("user")).sendKeys(user);
                driver.findElement(By.xpath("password")).sendKeys(password);
                driver.findElement(By.xpath("XXXXXXX")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("XXXXXXX")));
                driver.navigate().to(url_base + "/tokeninfo");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
                prop.setProperty("authorizationInfoToken", driver.findElement(By.xpath("/html/body/pre")).getText());
                driver.navigate().to(url_base + "/token");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/pre")));
                String tokenAux = driver.findElement(By.xpath("/html/body/pre")).getText().split("\":\"")[1];
                tokenAux = tokenAux.substring(0, tokenAux.length() - 2);
                prop.setProperty("authorizationToken", "Bearer " + tokenAux);

                prop.setProperty("tokenTime", String.valueOf(getCurrentDateInMilliseconds()));

                prop.store(new FileOutputStream("src/test/resources/token/AuthorizationToken.properties"), null);

            } catch (Exception e) {
                driver.quit();
                System.out.println("Ocurrio un error: " + e);
            }
            driver.quit();
        }

        token.put("authorizationInfo", prop.getProperty("authorizationInfoToken"));
        token.put("authorization", prop.getProperty("authorizationToken"));
        return token;

    }

    private static WebDriver getDriver() {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        try {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            options.setAcceptInsecureCerts(false);
            options.setHeadless(true);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(options);
        } catch (Exception e) {
            throw new TokensAuthorization.WebDriverError("Error al instanciar el driver para la solicitud del token");
        }
    }

    private static class WebDriverError extends RuntimeException {
        WebDriverError(String msj) {
            super(msj);
        }
    }

    private static boolean isTokenExpired(Properties prop) {
        long diffMilliseconds = getCurrentDateInMilliseconds() - Long.parseLong(prop.getProperty("tokenTime"));
        long diffHours = diffMilliseconds / (60 * 60 * 1000);
        return diffHours > 20;
    }

    private static long getCurrentDateInMilliseconds() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Argentina/Buenos_Aires"));
        return zdt.toInstant().toEpochMilli();
    }

    private static Properties getPropertyWhitFileLoaded() throws IOException {
        FileInputStream archivoProperties = null;
        Logger logger = LoggerFactory.getLogger(TokensAuthorization.class);
        Properties prop = new Properties();

        try {
            archivoProperties = new FileInputStream("src/test/resources/token/AuthorizationToken.properties");
        } catch (FileNotFoundException e) {
            logger.error("No se encontró el archivo .properties");
        }

        prop.load(archivoProperties);

        return prop;
    }
}
