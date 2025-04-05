package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshots/" + testName + ".png");

        try {
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot salva: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
        }
    }
}
