package br.com.jeansemolini.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8081/tasks/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("11/12/2020");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fechar o navegador
        driver.quit();
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("11/12/2020");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fechar o navegador
        driver.quit();
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fechar o navegador
        driver.quit();
    }

    @Test
    public void deveSalvarTarefaComDataPassada() {
        WebDriver driver = acessarAplicacao();
        try {
            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("11/12/2010");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fechar o navegador
        driver.quit();
    }
}
